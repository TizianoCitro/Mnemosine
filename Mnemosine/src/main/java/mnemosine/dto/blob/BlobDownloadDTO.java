package mnemosine.dto.blob;

public class BlobDownloadDTO {
    public BlobDownloadDTO(boolean isDownloaded, String downloadedBlobName) {
        this.isDownloaded = isDownloaded;
        this.downloadedBlobName = downloadedBlobName;
    }

    public BlobDownloadDTO() {
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public String getDownloadedBlobName() {
        return downloadedBlobName;
    }

    public void setDownloadedBlobName(String downloadedBlobName) {
        this.downloadedBlobName = downloadedBlobName;
    }

    private boolean isDownloaded;
    private String downloadedBlobName;
}
