package mnemosine.dto.account;

public class StorageAccountDeleteDTO {
    public StorageAccountDeleteDTO(String deletedAccount) {
        this.deletedAccount = deletedAccount;
    }

    public StorageAccountDeleteDTO() {
    }

    public String getDeletedAccount() {
        return deletedAccount;
    }

    public void setDeletedAccount(String deletedAccount) {
        this.deletedAccount = deletedAccount;
    }

    private String deletedAccount;
}
