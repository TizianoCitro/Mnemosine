package mnemosine.service.blob;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.BlobUploadDTO;
import mnemosine.dto.blob.BlobUploadFromPathRequestDTO;

import java.io.FileNotFoundException;

public interface BlobService {
    public MnemosineDTO<BlobUploadDTO> uploadFromFilePath(BlobUploadFromPathRequestDTO requestDTO) throws FileNotFoundException;
}