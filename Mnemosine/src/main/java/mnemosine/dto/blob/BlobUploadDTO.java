package mnemosine.dto.blob;

public class BlobUploadDTO {
    public BlobUploadDTO(boolean isUploaded) {
        this.isUploaded = isUploaded;
    }

    public BlobUploadDTO() {
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    private boolean isUploaded;
}
