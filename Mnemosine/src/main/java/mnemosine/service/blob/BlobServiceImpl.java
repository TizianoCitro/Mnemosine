package mnemosine.service.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlobProperties;
import com.azure.storage.blob.models.UserDelegationKey;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.storage.StorageAccount;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.*;
import mnemosine.service.account.StorageAccountService;
import mnemosine.service.container.ContainerService;
import mnemosine.util.MnemosineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;

@Service
public class BlobServiceImpl implements BlobService {

    @Override
    public MnemosineDTO<BlobUploadDTO> uploadFromFilePath(BlobUploadFromPathRequestDTO requestDTO) throws FileNotFoundException {
        // Build the request
        MnemosineDTO<BlobUploadDTO> blobUploadDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the file from path
        File file = new File(requestDTO.getBlobPath());

        // Build Blob Client
        BlobClient blobClient = blobContainerClient.getBlobClient(file.getName());

        // Upload the file
        blobClient.upload(new FileInputStream(file), file.length());

        return blobUploadDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new BlobUploadDTO(true));
    }

    @Override
    public MnemosineDTO<BlobDeleteDTO> delete(BlobDeleteRequestDTO requestDTO) {
        // Build the request
        MnemosineDTO<BlobDeleteDTO> blobDeleteDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the BLOB
        BlobClient blobClient = blobContainerClient.getBlobClient(requestDTO.getBlobName());
        if (!blobClient.exists())
            return blobDeleteDTO.error(MnemosineDTO.CODE, "Impossibile trovare il BLOB da eliminare")
                    .setData(new BlobDeleteDTO(requestDTO.getBlobName()));

        // Delete the BLOB
        blobClient.delete();

        return blobDeleteDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new BlobDeleteDTO(requestDTO.getBlobName()));
    }

    @Override
    public MnemosineDTO<BlobDownloadDTO> downloadToFile(BlobDownloadToFileRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<BlobDownloadDTO> blobDownloadDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the BLOB
        BlobClient blobClient = blobContainerClient.getBlobClient(requestDTO.getBlobName());
        if (!blobClient.exists())
            return blobDownloadDTO.error(MnemosineDTO.CODE, "Impossibile trovare il BLOB da scaricare")
                    .setData(new BlobDownloadDTO(false, requestDTO.getBlobName()));

        // Download the BLOB to a file
        blobClient.downloadToFile(requestDTO.getDownloadPath(), true);

        return blobDownloadDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new BlobDownloadDTO(true, requestDTO.getBlobName()));
    }

    @Override
    public MnemosineDTO<BlobListDTO> listBlobs(BlobListRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<BlobListDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build response's data
        BlobListDTO blobListDTO = new BlobListDTO();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // List all BLOBs
        for (BlobItem blobItem: blobContainerClient.listBlobs()) {
            BlobClient blobClient = blobContainerClient.getBlobClient(blobItem.getName());

            blobListDTO.addBlob(buildBlobInfo(blobClient, blobClient.getProperties()));
        }

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(blobListDTO);
    }

    @Override
    public MnemosineDTO<BlobInfoDTO> info(BlobInfoRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<BlobInfoDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the BLOB
        BlobClient blobClient = blobContainerClient.getBlobClient(requestDTO.getBlobName());
        if (!blobClient.exists())
            return mnemosineDTO.error(MnemosineDTO.CODE, "Impossibile trovare il BLOB cercato");

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(buildBlobInfo(blobClient, blobClient.getProperties()));
    }

    @Override
    public MnemosineDTO<BlobInfoDTO> rename(BlobRenameRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<BlobInfoDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the BLOB with the old name
        BlobClient oldBlobClient = blobContainerClient.getBlobClient(requestDTO.getBlobOldName());
        if (!oldBlobClient.exists())
            return mnemosineDTO.error(MnemosineDTO.CODE, "Impossibile trovare il BLOB da rinominare");

        // Get the BLOB with the new name
        BlobClient newBlobClient = blobContainerClient.getBlobClient(requestDTO.getBlobNewName());

        // Rename the BLOB
        if (!renameBlob(oldBlobClient, newBlobClient))
            return mnemosineDTO.error(MnemosineDTO.CODE, "Errore nella ridenomiazione del BLOB");

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(buildBlobInfo(newBlobClient, newBlobClient.getProperties()));
    }

    @Override
    public MnemosineDTO<BlobInfoDTO> copy(BlobCopyRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<BlobInfoDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the container
        BlobContainerClient blobContainerClient = containerService.getContainer(
                azure,
                requestDTO.getGroupName(),
                requestDTO.getAccountName(),
                requestDTO.getContainerName());

        // Get the BLOB with the old name
        BlobClient oldBlobClient = blobContainerClient.getBlobClient(requestDTO.getBlobName());
        if (!oldBlobClient.exists())
            return mnemosineDTO.error(MnemosineDTO.CODE, "Impossibile trovare il BLOB da copiare");

        BlobClient newBlobClient = buildCopyBlobClient(oldBlobClient, blobContainerClient);

        copyBlob(oldBlobClient, newBlobClient);
        clearCopy(oldBlobClient);

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(buildBlobInfo(newBlobClient, newBlobClient.getProperties()));
    }

    private boolean renameBlob(BlobClient oldBlobClient, BlobClient newBlobClient) {
        // Copy the BLOB with the old name into one with the new name
        copyBlob(oldBlobClient, newBlobClient);

        // Delete the old BLOB
        oldBlobClient.delete();

        // Check if the BLOB was correctly deleted
        if (oldBlobClient.exists()) {

            // Else delete the new one
            if (newBlobClient.exists())
                newBlobClient.delete();

            return false;
        }

        clearCopy(oldBlobClient);

        return true;
    }

    private void copyBlob(BlobClient oldBlobClient, BlobClient newBlobClient) {
        // Save the file for a moment
        oldBlobClient.downloadToFile(MNEMOSINE_TEMP_PATH + oldBlobClient.getBlobName(), true);

        // Upload the new file
        newBlobClient.uploadFromFile(MNEMOSINE_TEMP_PATH + oldBlobClient.getBlobName());
    }

    private void clearCopy(BlobClient blobClient) {
        // Delete temp file
        File fileToDelete = new File(MNEMOSINE_TEMP_PATH + blobClient.getBlobName());
        fileToDelete.delete();
    }

    private BlobClient buildCopyBlobClient(BlobClient blobClient, BlobContainerClient blobContainerClient) {
        BlobClient copyBlobClient = null;
        boolean isNameAvailable = false;
        int suffix = 1;

        String type = blobClient.getBlobName().substring(blobClient.getBlobName().lastIndexOf("."));

        // Find an availability name
        while (!isNameAvailable) {
            String copyName = blobClient.getBlobName();
            if (copyName.contains("."))
                copyName = copyName.substring(0, copyName.lastIndexOf(".")) + suffix;

            // Get the BLOB with the new name
            copyBlobClient = blobContainerClient.getBlobClient(copyName + type);
            if (!copyBlobClient.exists())
                isNameAvailable = true;

            suffix++;
        }

        return copyBlobClient;
    }

    private BlobInfoDTO buildBlobInfo(BlobClient blobClient, BlobProperties properties) {
        return new BlobInfoDTO(
                blobClient.getBlobName(),
                blobClient.getContainerName(),
                blobClient.getAccountName(),
                blobClient.getBlobUrl(),
                properties.getCreationTime().toString(),
                properties.getLastModified().toString(),
                properties.getBlobSize() + "",
                properties.getBlobType().name());
    }

    @Autowired
    private ContainerService containerService;

    private static final String MNEMOSINE_TEMP_PATH = "./src/main/resources/temp/";
}