package mnemosine.dto.blob;

public class BlobInfoDTO {
    public BlobInfoDTO(String blobName, String containerName, String accountName, String url, String creationDate, String lastModifiedDate, String size, String type) {
        this.blobName = blobName;
        this.containerName = containerName;
        this.accountName = accountName;
        this.url = url;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.size = size;
        this.type = type;
    }

    public BlobInfoDTO() {
    }

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String blobName;
    private String containerName;
    private String accountName;
    private String url;
    private String creationDate;
    private String lastModifiedDate;
    private String size;
    private String type;
}
