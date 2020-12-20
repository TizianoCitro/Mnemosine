package mnemosine.service.account;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.ResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.storage.StorageAccount;
import com.microsoft.azure.management.storage.StorageAccountSkuType;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.account.StorageAccountCreateDTO;
import mnemosine.dto.account.StorageAccountCreateRequestDTO;
import mnemosine.dto.account.StorageAccountDTO;
import mnemosine.dto.account.StorageAccountDeleteDTO;
import mnemosine.dto.account.StorageAccountDeleteRequestDTO;
import mnemosine.dto.account.StorageAccountInfoDTO;
import mnemosine.dto.account.StorageAccountInfoRequestDTO;
import mnemosine.dto.account.StorageAccountListDTO;
import mnemosine.dto.account.StorageAccountListRequestDTO;
import mnemosine.dto.group.ResourceGroupCreateDTO;
import mnemosine.service.group.ResourceGroupService;
import mnemosine.util.MnemosineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageAccountServiceImpl implements StorageAccountService {

    @Override
    public MnemosineDTO<StorageAccountCreateDTO> create(StorageAccountCreateRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<StorageAccountCreateDTO> storageAccountCreateDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Resource Group creation
        ResourceGroup resourceGroup = resourceGroupService.getResourceGroup(azure, requestDTO.getGroupName());

        // Storage Account creation
        StorageAccount storageAccount = azure.storageAccounts()
                .define(requestDTO.getAccountName())
                .withRegion(Region.EUROPE_WEST)
                .withExistingResourceGroup(resourceGroup)
                .withSku(StorageAccountSkuType.STANDARD_LRS)
                .create();

        return storageAccountCreateDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new StorageAccountCreateDTO(
                        storageAccount.id(),
                        storageAccount.name(),
                        storageAccount.resourceGroupName(),
                        storageAccount.regionName()));
    }

    @Override
    public MnemosineDTO<StorageAccountDeleteDTO> delete(StorageAccountDeleteRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<StorageAccountDeleteDTO> storageAccountDeleteDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Delete Storage Account
        azure.storageAccounts().deleteByResourceGroup(requestDTO.getGroupName(), requestDTO.getAccountName());

        //Check that the account was deleted
        StorageAccount storageAccount = azure.storageAccounts().getByResourceGroup(
                requestDTO.getGroupName(),
                requestDTO.getAccountName());
        if (storageAccount != null)
            return storageAccountDeleteDTO.error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);

        return storageAccountDeleteDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new StorageAccountDeleteDTO(requestDTO.getAccountName()));
    }

    @Override
    public MnemosineDTO<StorageAccountListDTO> listAccounts(StorageAccountListRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<StorageAccountListDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build response's data
        StorageAccountListDTO storageAccountListDTO = new StorageAccountListDTO();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Get all Storage Accounts in a Resource Group
        for (StorageAccount storageAccount: azure.storageAccounts().list())
            storageAccountListDTO.addAccount(new StorageAccountDTO(
                    storageAccount.id(),
                    storageAccount.name(),
                    storageAccount.resourceGroupName(),
                    storageAccount.regionName()));

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(storageAccountListDTO);
    }

    @Override
    public MnemosineDTO<StorageAccountListDTO> listAccountsByResourceGroup(StorageAccountListRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<StorageAccountListDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build response's data
        StorageAccountListDTO storageAccountListDTO = new StorageAccountListDTO();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Get all Storage Accounts in a Resource Group
        for (StorageAccount storageAccount: azure.storageAccounts().listByResourceGroup(requestDTO.getGroupName()))
            storageAccountListDTO.addAccount(new StorageAccountDTO(
                    storageAccount.id(),
                    storageAccount.name(),
                    storageAccount.resourceGroupName(),
                    storageAccount.regionName()));

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(storageAccountListDTO);
    }

    @Override
    public MnemosineDTO<StorageAccountInfoDTO> accountInfo(StorageAccountInfoRequestDTO requestDTO) {
        return null;
    }

    @Autowired
    private ResourceGroupService resourceGroupService;
}
