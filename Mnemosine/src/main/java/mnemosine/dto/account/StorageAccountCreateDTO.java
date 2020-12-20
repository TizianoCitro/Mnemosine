package mnemosine.dto.account;

public class StorageAccountCreateDTO {
    public StorageAccountCreateDTO(String accountId, String accountName, String groupName, String region) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.groupName = groupName;
        this.region = region;
    }

    public StorageAccountCreateDTO() {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String accountId;
    private String accountName;
    private String groupName;
    private String region;
}
