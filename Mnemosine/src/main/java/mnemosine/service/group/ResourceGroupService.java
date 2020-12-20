package mnemosine.service.group;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.ResourceGroup;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.group.ResourceGroupCreateDTO;
import mnemosine.dto.group.ResourceGroupCreateRequestDTO;
import mnemosine.dto.group.ResourceGroupDeleteDTO;
import mnemosine.dto.group.ResourceGroupDeleteRequestDTO;
import mnemosine.dto.group.ResourceGroupInfoDTO;
import mnemosine.dto.group.ResourceGroupInfoRequestDTO;
import mnemosine.dto.group.ResourceGroupListDTO;
import mnemosine.dto.group.ResourceGroupListRequestDTO;

public interface ResourceGroupService {
    public MnemosineDTO<ResourceGroupCreateDTO> create(ResourceGroupCreateRequestDTO requestDTO);
    public MnemosineDTO<ResourceGroupDeleteDTO> delete(ResourceGroupDeleteRequestDTO requestDTO);
    public MnemosineDTO<ResourceGroupListDTO> listGroups(ResourceGroupListRequestDTO requestDTO);
    public MnemosineDTO<ResourceGroupInfoDTO> groupInfo(ResourceGroupInfoRequestDTO requestDTO);

    public ResourceGroup getResourceGroup(Azure azure, String groupName);
}
