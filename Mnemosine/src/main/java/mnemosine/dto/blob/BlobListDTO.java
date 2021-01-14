package mnemosine.dto.blob;

import java.util.ArrayList;

public class BlobListDTO {
    public BlobListDTO(ArrayList<BlobInfoDTO> blobs) {
        this.blobs = blobs;
    }

    public BlobListDTO() {
        blobs = new ArrayList<>();
    }

    public void addBlob(BlobInfoDTO blob) {
        blobs.add(blob);
    }

    public ArrayList<BlobInfoDTO> getBlobs() {
        return blobs;
    }

    public void setBlobs(ArrayList<BlobInfoDTO> blobs) {
        this.blobs = blobs;
    }

    private ArrayList<BlobInfoDTO> blobs;
}
