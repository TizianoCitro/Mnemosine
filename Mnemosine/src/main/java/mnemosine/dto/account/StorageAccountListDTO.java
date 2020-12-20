package mnemosine.dto.account;

import java.util.ArrayList;

public class StorageAccountListDTO {
    public StorageAccountListDTO(ArrayList<StorageAccountDTO> accounts) {
        this.accounts = accounts;
    }

    public StorageAccountListDTO() {
        accounts = new ArrayList<>();
    }

    public void addAccount(StorageAccountDTO account) {
        accounts.add(account);
    }

    public ArrayList<StorageAccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<StorageAccountDTO> accounts) {
        this.accounts = accounts;
    }

    private ArrayList<StorageAccountDTO> accounts;
}
