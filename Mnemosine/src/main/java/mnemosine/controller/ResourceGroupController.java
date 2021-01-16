package mnemosine.controller;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.group.ResourceGroupCreateDTO;
import mnemosine.dto.group.ResourceGroupCreateRequestDTO;
import mnemosine.dto.group.ResourceGroupDeleteDTO;
import mnemosine.dto.group.ResourceGroupDeleteRequestDTO;
import mnemosine.dto.group.ResourceGroupInfoDTO;
import mnemosine.dto.group.ResourceGroupInfoRequestDTO;
import mnemosine.dto.group.ResourceGroupListDTO;
import mnemosine.dto.group.ResourceGroupListRequestDTO;
import mnemosine.service.group.ResourceGroupService;
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
@RequestMapping(value = "/group")
public class ResourceGroupController {

    Logger logger = Logger.getLogger(ResourceGroupController.class.getName());

    @PostMapping("/create")
    public MnemosineDTO<ResourceGroupCreateDTO> create(@RequestBody ResourceGroupCreateRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'group/create' per creare il gruppo: " + requestDTO.getGroupName());

        return resourceGroupService.create(requestDTO);
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<ResourceGroupDeleteDTO> delete(@RequestBody ResourceGroupDeleteRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'group/delete' per eliminare il gruppo: " + requestDTO.getGroupName());

        return resourceGroupService.delete(requestDTO);
    }

    @GetMapping("/groups")
    public MnemosineDTO<ResourceGroupListDTO> groups(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId) {
        logger.info("Sono nel servizio 'group/groups'");

        return resourceGroupService.listGroups(
                new ResourceGroupListRequestDTO(clientId, tenantId, secret, subscriptionId));
    }

    @GetMapping("/info")
    public MnemosineDTO<ResourceGroupInfoDTO> info(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName) {
        logger.info("Sono nel servizio 'group/info' per info sul gruppo: " + groupName);

        return resourceGroupService.groupInfo(
                new ResourceGroupInfoRequestDTO(clientId, tenantId, secret, subscriptionId, groupName));
    }

    @Autowired
    private ResourceGroupService resourceGroupService;
}
