package mnemosine.controller;

import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.blob.BlobUploadDTO;
import mnemosine.dto.container.ContainerCreateDTO;
import mnemosine.dto.container.ContainerCreateRequestDTO;
import mnemosine.dto.container.ContainerDeleteDTO;
import mnemosine.dto.container.ContainerDeleteRequestDTO;
import mnemosine.dto.container.ContainerInfoDTO;
import mnemosine.dto.container.ContainerInfoRequestDTO;
import mnemosine.dto.container.ContainerListDTO;
import mnemosine.dto.container.ContainerListRequestDTO;
import mnemosine.service.container.ContainerService;
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
@RequestMapping(value = "/container")
public class ContainerController {

    Logger logger = Logger.getLogger(ContainerController.class.getName());

    @PostMapping("/create")
    public MnemosineDTO<ContainerCreateDTO> create(@RequestBody ContainerCreateRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'container/create'"
                + " per creare il container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return containerService.create(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<ContainerCreateDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<ContainerDeleteDTO> delete(@RequestBody ContainerDeleteRequestDTO requestDTO) {
        logger.info("Sono nel servizio 'container/delete'"
                + " per cancellare il container: " + requestDTO.getContainerName()
                + " nell'account: " + requestDTO.getAccountName()
                + " nel gruppo: " + requestDTO.getGroupName());

        try {
            return containerService.delete(requestDTO);
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<ContainerDeleteDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/containers-by-account")
    public MnemosineDTO<ContainerListDTO> containersByAccount(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName) {
        logger.info("Sono nel servizio 'container/containers-by-account' per i container nell'account: " + accountName);

        try {
            return containerService.listContainersByStorageAccount(
                    new ContainerListRequestDTO(clientId, tenantId, secret, subscriptionId, groupName, accountName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<ContainerListDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @GetMapping("/info")
    public MnemosineDTO<ContainerInfoDTO> info(
            @RequestParam("client_id") String clientId,
            @RequestParam("tenant_id") String tenantId,
            @RequestParam("secret") String secret,
            @RequestParam("subscription_id") String subscriptionId,
            @RequestParam("group_name") String groupName,
            @RequestParam("account_name") String accountName,
            @RequestParam("container_name") String containerName) {
        logger.info("Sono nel servizio 'container/info'"
                + " per info sul container: " + containerName
                + " nell'account: " + accountName
                + " nel gruppo: " + groupName);

        try {
            return containerService.info(
                    new ContainerInfoRequestDTO(clientId, tenantId, secret, subscriptionId, groupName, accountName, containerName));
        } catch (Exception e) {
            e.printStackTrace();

            return new MnemosineDTO<ContainerInfoDTO>().error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);
        }
    }

    @Autowired
    private ContainerService containerService;
}
