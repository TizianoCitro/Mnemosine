package mnemosine.dto.blob;

public class BlobDeleteDTO {
    public BlobDeleteDTO(String deletedBlob) {
        this.deletedBlob = deletedBlob;
    }

    public BlobDeleteDTO() {
    }

    public String getDeletedBlob() {
        return deletedBlob;
    }

    public void setDeletedBlob(String deletedBlob) {
        this.deletedBlob = deletedBlob;
    }

    private String deletedBlob;
}
