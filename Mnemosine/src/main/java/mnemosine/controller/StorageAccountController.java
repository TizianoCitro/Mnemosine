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
import mnemosine.dto.group.ResourceGroupInfoDTO;
import mnemosine.service.account.StorageAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/account")
public class StorageAccountController {

    Logger logger = Logger.getLogger(StorageAccountController.class.getName());

    @PostMapping("/create")
    public MnemosineDTO<StorageAccountCreateDTO> create(@RequestBody StorageAccountCreateRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'account/create'"
                + " per creare l'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return storageAccountService.create(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<StorageAccountCreateDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<StorageAccountDeleteDTO> delete(@RequestBody StorageAccountDeleteRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'account/delete'"
                + " per eliminare l'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return storageAccountService.delete(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<StorageAccountDeleteDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/all-accounts")
    public MnemosineDTO<StorageAccountListDTO> allAccounts(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId) {
        logger.info("Sono nel servizio 'account/all-accounts' per tutti gli account");

        try {
            return storageAccountService.listAccounts(
                    new StorageAccountListRequestDTO(clientId, tenantId, secret, subscriptionId, ""));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<StorageAccountListDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/accounts-by-group")
    public MnemosineDTO<StorageAccountListDTO> accountsByGroup(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName) {
        logger.info("Sono nel servizio 'account/accounts-by-group' per gli account nel gruppo: " + groupName);

        try {
            return storageAccountService.listAccountsByResourceGroup(
                    new StorageAccountListRequestDTO(clientId, tenantId, secret, subscriptionId, groupName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<StorageAccountListDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/info")
    public MnemosineDTO<StorageAccountInfoDTO> info(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName) {
        logger.info("Sono nel servizio 'account/info'"
                + " per info sull'accopunt: " + accountName
                + " nel gruppo: " + groupName);

        try {
            return storageAccountService.accountInfo(
                    new StorageAccountInfoRequestDTO(clientId, tenantId, secret, subscriptionId, groupName, accountName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<StorageAccountInfoDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @Autowired
    private StorageAccountService storageAccountService;
}
