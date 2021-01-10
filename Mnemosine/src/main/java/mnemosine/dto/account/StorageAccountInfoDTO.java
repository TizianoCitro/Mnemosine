package mnemosine.dto.account;

public class StorageAccountInfoDTO {
    public StorageAccountInfoDTO(String accountId, String accountName, String groupName, String creationDate, String region) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.groupName = groupName;
        this.creationDate = creationDate;
        this.region = region;
    }

    public StorageAccountInfoDTO() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String accountId;
    private String accountName;
    private String groupName;
    private String creationDate;
    private String region;
}
