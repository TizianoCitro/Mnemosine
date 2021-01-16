package mnemosine.service.blob;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public interface BlobService {
    public MnemosineDTO<BlobUploadDTO> uploadFromFilePath(BlobUploadFromPathRequestDTO requestDTO) throws FileNotFoundException;
    public MnemosineDTO<BlobDeleteDTO> delete(BlobDeleteRequestDTO requestDTO);
    public MnemosineDTO<BlobDownloadDTO> downloadToFile(BlobDownloadToFileRequestDTO requestDTO);
    public MnemosineDTO<BlobListDTO> listBlobs(BlobListRequestDTO requestDTO);
    public MnemosineDTO<BlobInfoDTO> info(BlobInfoRequestDTO requestDTO);
    public MnemosineDTO<BlobInfoDTO> rename(BlobRenameRequestDTO requestDTO);
    public MnemosineDTO<BlobInfoDTO> copy(BlobCopyRequestDTO requestDTO);

    public ByteArrayOutputStream download(BlobDownloadRequestDTO requestDTO);
}