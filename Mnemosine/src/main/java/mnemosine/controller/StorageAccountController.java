package mnemosine.controller;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.account.StorageAccountCreateDTO;
import mnemosine.dto.account.StorageAccountCreateRequestDTO;
import mnemosine.dto.account.StorageAccountDeleteDTO;
import mnemosine.dto.account.StorageAccountDeleteRequestDTO;
import mnemosine.dto.account.StorageAccountInfoDTO;
import mnemosine.dto.account.StorageAccountInfoRequestDTO;
import mnemosine.dto.account.StorageAccountListDTO;
import mnemosine.dto.account.StorageAccountListRequestDTO;
import mnemosine.service.account.StorageAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class StorageAccountController {

    @PostMapping("/create")
    public MnemosineDTO<StorageAccountCreateDTO> create(@RequestBody StorageAccountCreateRequestDTO requestDTO) {
        return storageAccountService.create(requestDTO);
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<StorageAccountDeleteDTO> delete(@RequestBody StorageAccountDeleteRequestDTO requestDTO) {
        return storageAccountService.delete(requestDTO);
    }

    @GetMapping("/all-accounts")
    public MnemosineDTO<StorageAccountListDTO> allAccounts(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId) {
        return storageAccountService.listAccounts(
                new StorageAccountListRequestDTO(clientId, tenantId, secret, subscriptionId, ""));
    }

    @GetMapping("/accounts-by-group")
    public MnemosineDTO<StorageAccountListDTO> accountsByGroup(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName) {
        return storageAccountService.listAccountsByResourceGroup(
                new StorageAccountListRequestDTO(clientId, tenantId, secret, subscriptionId, groupName));
    }

    @GetMapping("/info")
    public MnemosineDTO<StorageAccountInfoDTO> info(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName) {
        return storageAccountService.accountInfo(
                new StorageAccountInfoRequestDTO(clientId, tenantId, secret, subscriptionId, groupName, accountName));
    }

    @Autowired
    private StorageAccountService storageAccountService;
}
