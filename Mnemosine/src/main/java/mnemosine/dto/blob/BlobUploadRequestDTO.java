package mnemosine.dto.blob;

public class BlobUploadRequestDTO {
    public BlobUploadRequestDTO(String clientId, String tenantId, String secret, String subscriptionId, String groupName, String accountName, String containerName, String fileName, byte[] fileBytes) {
        this.clientId = clientId;
        this.tenantId = tenantId;
        this.secret = secret;
        this.subscriptionId = subscriptionId;
        this.groupName = groupName;
        this.accountName = accountName;
        this.containerName = containerName;
        this.fileName = fileName;
        this.fileBytes = fileBytes;
    }

    public BlobUploadRequestDTO() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    private String clientId;
    private String tenantId;
    private String secret;
    private String subscriptionId;
    private String groupName;
    private String accountName;
    private String containerName;
    private String fileName;
    private byte[] fileBytes;
}
