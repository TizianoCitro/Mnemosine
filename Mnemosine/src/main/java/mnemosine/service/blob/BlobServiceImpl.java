package mnemosine.service.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.microsoft.azure.management.Azure;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.BlobUploadDTO;
import mnemosine.dto.blob.BlobUploadFromPathRequestDTO;
import mnemosine.service.container.ContainerService;
import mnemosine.util.MnemosineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class BlobServiceImpl implements BlobService {

    @Override
    public MnemosineDTO<BlobUploadDTO> uploadFromFilePath(BlobUploadFromPathRequestDTO requestDTO) throws FileNotFoundException {
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
        File file = new File(requestDTO.getFilePath());

        // Build Blob Client
        BlobClient blobClient = blobContainerClient.getBlobClient(file.getName());

        // Upload the file
        blobClient.upload(new FileInputStream(file), file.length());

        return blobUploadDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new BlobUploadDTO());
    }

    @Autowired
    private ContainerService containerService;
}
