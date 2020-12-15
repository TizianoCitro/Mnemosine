package mnemosine.service;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.ResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.group.ResourceGroupCreateDTO;
import mnemosine.dto.group.ResourceGroupCreateRequestDTO;
import mnemosine.dto.group.ResourceGroupDeleteDTO;
import mnemosine.dto.group.ResourceGroupDeleteRequestDTO;
import mnemosine.dto.group.ResourceGroupInfoDTO;
import mnemosine.dto.group.ResourceGroupInfoRequestDTO;
import mnemosine.dto.group.ResourceGroupListDTO;
import mnemosine.dto.group.ResourceGroupListRequestDTO;
import mnemosine.util.MnemosineUtil;
import org.springframework.stereotype.Service;

@Service
public class ResourceGroupServiceImpl implements ResourceGroupService {
    @Override
    public MnemosineDTO<ResourceGroupCreateDTO> create(ResourceGroupCreateRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ResourceGroupCreateDTO> resourceGroupCreateDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Resource Group creation
        ResourceGroup resourceGroup = azure.resourceGroups()
                .define(requestDTO.getGroupName())
                .withRegion(Region.EUROPE_WEST)
                .create();

        return resourceGroupCreateDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ResourceGroupCreateDTO(resourceGroup.id(), resourceGroup.name(), resourceGroup.regionName()));
    }

    @Override
    public MnemosineDTO<ResourceGroupDeleteDTO> delete(ResourceGroupDeleteRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ResourceGroupDeleteDTO> resourceGroupDeleteDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Delete the resource group
        azure.resourceGroups().deleteByName(requestDTO.getGroupName());

        // Check the resource group has been deleted
        ResourceGroup resourceGroupByName = azure.resourceGroups().getByName(requestDTO.getGroupName());
        if (resourceGroupByName != null)
            return resourceGroupDeleteDTO.error(MnemosineDTO.CODE, MnemosineDTO.FAIL_MESSAGE);

        return resourceGroupDeleteDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ResourceGroupDeleteDTO(requestDTO.getGroupName()));
    }

    @Override
    public MnemosineDTO<ResourceGroupListDTO> listGroups(ResourceGroupListRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ResourceGroupListDTO> mnemosineDTO = new MnemosineDTO<>();

        // Build response's data
        ResourceGroupListDTO resourceGroupListDTO = new ResourceGroupListDTO();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        for (ResourceGroup group: azure.resourceGroups().list()) {
            resourceGroupListDTO.addGroupName(group.name());
        }

        return mnemosineDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(resourceGroupListDTO);
    }

    @Override
    public MnemosineDTO<ResourceGroupInfoDTO> groupInfo(ResourceGroupInfoRequestDTO requestDTO) {
        // Build the response
        MnemosineDTO<ResourceGroupInfoDTO> resourceGroupInfoDTO = new MnemosineDTO<>();

        // Build azure
        Azure azure = MnemosineUtil.buildAzure(
                MnemosineUtil.buildCredentials(
                        requestDTO.getClientId(),
                        requestDTO.getTenantId(),
                        requestDTO.getSecret()),
                requestDTO.getSubscriptionId());

        // Check the resource group has been deleted
        ResourceGroup resourceGroupByName = azure.resourceGroups().getByName(requestDTO.getGroupName());
        if (resourceGroupByName == null)
            return resourceGroupInfoDTO.error(MnemosineDTO.CODE, "Impossibile trovare il resource group cercato");

        return resourceGroupInfoDTO.success(MnemosineDTO.CODE, MnemosineDTO.SUCCES_MESSAGE)
                .setData(new ResourceGroupInfoDTO(
                        resourceGroupByName.id(),
                        resourceGroupByName.name(),
                        resourceGroupByName.regionName()));
    }
}
