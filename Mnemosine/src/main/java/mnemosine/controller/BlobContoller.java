package mnemosine.controller;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.*;
import mnemosine.service.blob.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/blob")
public class BlobContoller {

    Logger logger = Logger.getLogger(BlobContoller.class.getName());

    @PostMapping("/upload")
    public MnemosineDTO<BlobUploadDTO> upload(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName,
            @RequestParam("file_to_upload") MultipartFile file) {
        logger.info("Sono nel servizio 'blob/upload'"
                + " per caricare il BLOB: " + file.getOriginalFilename() + " o anche " + file.getName()
                + " nel container: " + containerName
                + " nell'account: " + accountName
                + " nel gruppo: " + groupName);

        try {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();

            return blobService.upload(
                    new BlobUploadRequestDTO(clientId, tenantId, secret, subscriptionId,
                            groupName, accountName, containerName, fileName, fileBytes));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobUploadDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE)
                    .setData(new BlobUploadDTO(false));
        }
    }

    @PostMapping("/upload-from-path")
    public MnemosineDTO<BlobUploadDTO> uploadFromPath(@RequestBody BlobUploadFromPathRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'blob/upload-from-path'"
                + " per creare il BLOB: " + requestDTO.getBlobPath()
                + " nel container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return blobService.uploadFromFilePath(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobUploadDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE)
                    .setData(new BlobUploadDTO(false));
        }
    }

    @PostMapping("/copy")
    public MnemosineDTO<BlobInfoDTO> copy(@RequestBody BlobCopyRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'blob/delete'"
                + " per copiare il BLOB: " + requestDTO.getBlobName()
                + " nel container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return blobService.copy(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobInfoDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<BlobDeleteDTO> delete(@RequestBody BlobDeleteRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'blob/delete'"
                + " per eliminare il BLOB: " + requestDTO.getBlobName()
                + " nel container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return blobService.delete(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobDeleteDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @PutMapping("/rename")
    public MnemosineDTO<BlobInfoDTO> rename(@RequestBody BlobRenameRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'blob/delete'"
                + " per rinominare il BLOB: " + requestDTO.getBlobOldName() + " in " + requestDTO.getBlobNewName()
                + " nel container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return blobService.rename(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobInfoDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/download-to-file")
    public MnemosineDTO<BlobDownloadDTO> downloadToFile(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName,
            @RequestParam("blob_name") String blobName,
            @RequestParam("download_path") String downloadPath) {
        try {
            return blobService.downloadToFile(
                    new BlobDownloadToFileRequestDTO(clientId, tenantId, secret, subscriptionId,
                            groupName, accountName, containerName, blobName, downloadPath));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobDownloadDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/download")
    public HttpEntity<byte[]> download(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName,
            @RequestParam("blob_name") String blobName) {
        logger.info("Sono nel servizio 'blob/download'"
                + " per scaricare il BLOB: " + blobName
                + " nel container: " + containerName
                + " nell'account: " + accountName
                + " nel gruppo: " + groupName);

        // Get the file to download
        ByteArrayOutputStream byteArrayOutputStream = blobService.download(
                new BlobDownloadRequestDTO(clientId, tenantId, secret, subscriptionId, groupName, accountName, containerName, blobName));

        byte[] bytes = byteArrayOutputStream.toByteArray();

        // Set headers for download
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + blobName.replace(" ", "_"));
        header.setContentLength(bytes.length);

        return new HttpEntity<>(bytes, header);
    }

    @GetMapping("/blobs")
    public MnemosineDTO<BlobListDTO> blobs(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName) {
        logger.info("Sono nel servizio 'blob/blobs' per i BLOB nel container: " + containerName);

        try {
            return blobService.listBlobs(new BlobListRequestDTO(clientId, tenantId, secret, subscriptionId,
                    groupName, accountName, containerName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobListDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/info")
    public MnemosineDTO<BlobInfoDTO> info(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName,
            @RequestParam("blob_name") String blobName) {
        logger.info("Sono nel servizio 'blob/info'"
                + " per info sul BLOB: " + blobName
                + " nel container: " + containerName
                + " nell'account: " + accountName
                + " nel gruppo: " + groupName);

        try {
            return blobService.info(
                    new BlobInfoRequestDTO(clientId, tenantId, secret, subscriptionId,
                            groupName, accountName, containerName, blobName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<BlobInfoDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @Autowired
    private BlobService blobService;
}
