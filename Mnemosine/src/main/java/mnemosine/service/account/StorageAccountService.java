package mnemosine.service.account;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.storage.StorageAccount;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.account.StorageAccountCreateDTO;
import mnemosine.dto.account.StorageAccountCreateRequestDTO;
import mnemosine.dto.account.StorageAccountDeleteDTO;
import mnemosine.dto.account.StorageAccountDeleteRequestDTO;
import mnemosine.dto.account.StorageAccountInfoDTO;
import mnemosine.dto.account.StorageAccountInfoRequestDTO;
import mnemosine.dto.account.StorageAccountListDTO;
import mnemosine.dto.account.StorageAccountListRequestDTO;

public interface StorageAccountService {
    public MnemosineDTO<StorageAccountCreateDTO> create(StorageAccountCreateRequestDTO requestDTO);
    public MnemosineDTO<StorageAccountDeleteDTO> delete(StorageAccountDeleteRequestDTO requestDTO);
    public MnemosineDTO<StorageAccountListDTO> listAccounts(StorageAccountListRequestDTO requestDTO);
    public MnemosineDTO<StorageAccountListDTO> listAccountsByResourceGroup(StorageAccountListRequestDTO requestDTO);
    public MnemosineDTO<StorageAccountInfoDTO> accountInfo(StorageAccountInfoRequestDTO requestDTO);

    public StorageAccount getStorageAccount(Azure azure, String groupName, String accountName);
    public String buildConnectionString(StorageAccount storageAccount);
}
