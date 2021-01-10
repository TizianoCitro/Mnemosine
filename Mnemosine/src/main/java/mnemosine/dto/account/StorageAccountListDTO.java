package mnemosine.dto.account;

import java.util.ArrayList;

public class StorageAccountListDTO {
    public StorageAccountListDTO(ArrayList<StorageAccountInfoDTO> accounts) {
        this.accounts = accounts;
    }

    public StorageAccountListDTO() {
        accounts = new ArrayList<>();
    }

    public void addAccount(StorageAccountInfoDTO account) {
        accounts.add(account);
    }

    public ArrayList<StorageAccountInfoDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<StorageAccountInfoDTO> accounts) {
        this.accounts = accounts;
    }

    private ArrayList<StorageAccountInfoDTO> accounts;
}
