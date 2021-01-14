package mnemosine.controller;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.*;
import mnemosine.service.blob.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blob")
public class BlobContoller {

    @PostMapping("/upload-from-path")
    public MnemosineDTO<BlobUploadDTO> uploadFromPath(@RequestBody BlobUploadFromPathRequestDTO requestDTO) {
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
        return blobService.copy(requestDTO);
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<BlobDeleteDTO> delete(@RequestBody BlobDeleteRequestDTO requestDTO) {
        return blobService.delete(requestDTO);
    }

    @PutMapping("/rename")
    public MnemosineDTO<BlobInfoDTO> rename(@RequestBody BlobRenameRequestDTO requestDTO) {
        return blobService.rename(requestDTO);
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
        return blobService.downloadToFile(
                new BlobDownloadToFileRequestDTO(clientId, tenantId, secret, subscriptionId,
                        groupName, accountName, containerName, blobName, downloadPath));
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
        return blobService.listBlobs(new BlobListRequestDTO(clientId, tenantId, secret, subscriptionId,
                groupName, accountName, containerName));
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
        return blobService.info(
                new BlobInfoRequestDTO(clientId, tenantId, secret, subscriptionId,
                        groupName, accountName, containerName, blobName));
    }

    @Autowired
    private BlobService blobService;
}
