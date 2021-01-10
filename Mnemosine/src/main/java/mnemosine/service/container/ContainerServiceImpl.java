package mnemosine.service.container;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobContainerItem;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.storage.StorageAccount;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.container.ContainerCreateDTO;
import mnemosine.dto.container.ContainerCreateRequestDTO;
import mnemosine.dto.container.ContainerDeleteDTO;
import mnemosine.dto.container.ContainerDeleteRequestDTO;
import mnemosine.dto.container.ContainerInfoDTO;
import mnemosine.dto.container.ContainerInfoRequestDTO;
import mnemosine.dto.container.ContainerListDTO;
import mnemosine.dto.container.ContainerListRequestDTO;
import mnemosine.service.account.StorageAccountService;
import mnemosine.util.MnemosineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerServiceImpl implements ContainerService {
    @Override
    public MnemosineDTO<ContainerCreateDTO> create(ContainerCreateRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ContainerCreateDTO> containerCreateDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the service
        BlobServiceClient blobServiceClient =
                buildServiceClient(azure, requestDTO.getGroupName(), requestDTO.getAccountName());

        // Build the container
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(requestDTO.getContainerName());

        // Create the container if it does not exist
        if (!blobContainerClient.exists())
            blobContainerClient.create();

        return containerCreateDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ContainerCreateDTO(
                        blobContainerClient.getBlobContainerName(),
                        blobContainerClient.getAccountName(),
                        blobContainerClient.getProperties().getLastModified().toString(),
                        blobContainerClient.getBlobContainerUrl()));
    }

    @Override
    public MnemosineDTO<ContainerDeleteDTO> delete(ContainerDeleteRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ContainerDeleteDTO> containerDeleteDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the service
        BlobServiceClient blobServiceClient =
                buildServiceClient(azure, requestDTO.getGroupName(), requestDTO.getAccountName());

        // Build the container
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(requestDTO.getContainerName());

        // Before trying to delete it, check if it exists
        if (!blobContainerClient.exists())
            return containerDeleteDTO.error(MnemosineDTO.CODE, "Il container da eliminare non esiste");

        // Delete the container if it exists
        blobContainerClient.delete();

        return containerDeleteDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ContainerDeleteDTO(requestDTO.getContainerName()));
    }

    @Override
    public MnemosineDTO<ContainerListDTO> listContainersByStorageAccount(ContainerListRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ContainerListDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build response's data
        ContainerListDTO containerListDTO = new ContainerListDTO();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the service
        BlobServiceClient blobServiceClient =
                buildServiceClient(azure, requestDTO.getGroupName(), requestDTO.getAccountName());

        // Listing all containers in the storage account
        for (BlobContainerItem blobContainerItem: blobServiceClient.listBlobContainers())
            containerListDTO.addContainer(new ContainerInfoDTO(
                    blobContainerItem.getName(),
                    requestDTO.getAccountName(),
                    blobContainerItem.getProperties().getLastModified().toString()));

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(containerListDTO);
    }

    @Override
    public MnemosineDTO<ContainerInfoDTO> info(ContainerInfoRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ContainerInfoDTO> containerInfoDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Build the service
        BlobServiceClient blobServiceClient =
                buildServiceClient(azure, requestDTO.getGroupName(), requestDTO.getAccountName());

        // Build the container
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(requestDTO.getContainerName());

        // Check if the container exists
        if (!blobContainerClient.exists())
            return containerInfoDTO.error(MnemosineDTO.CODE, "Impossibile trovare il container");

        return containerInfoDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ContainerInfoDTO(
                        blobContainerClient.getBlobContainerName(),
                        blobContainerClient.getAccountName(),
                        blobContainerClient.getProperties().getLastModified().toString()));
    }

    @Override
    public BlobContainerClient getContainer(Azure azure, String groupName, String accountName, String containerName) {
        // Build the service
        BlobServiceClient blobServiceClient = buildServiceClient(azure, groupName, accountName);

        // Build the container
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);

        // Create the container if it does not exist
        if (!blobContainerClient.exists())
            blobContainerClient.create();

        return blobContainerClient;
    }

    private BlobServiceClient buildServiceClient(Azure azure, String groupName, String accountName) {
        StorageAccount storageAccount = storageAccountService.getStorageAccount(azure, groupName, accountName);

        // Build the service
        return new BlobServiceClientBuilder()
                .connectionString(storageAccountService.buildConnectionString(storageAccount))
                .buildClient();
    }

    @Autowired
    private StorageAccountService storageAccountService;
}
