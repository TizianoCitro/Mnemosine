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
import mnemosine.service.ResourceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/group")
public class ResourceGroupController {
    @PostMapping("/create")
    public MnemosineDTO<ResourceGroupCreateDTO> create(@RequestBody ResourceGroupCreateRequestDTO requestDTO) {
        return resourceGroupService.create(requestDTO);
    }

    @DeleteMapping("/delete")
    public MnemosineDTO<ResourceGroupDeleteDTO> delete(@RequestBody ResourceGroupDeleteRequestDTO requestDTO) {
        return resourceGroupService.delete(requestDTO);
    }

    @GetMapping("/groups")
    public MnemosineDTO<ResourceGroupListDTO> groups(@RequestBody ResourceGroupListRequestDTO requestDTO) {
        return resourceGroupService.listGroups(requestDTO);
    }

    @GetMapping("/info")
    public MnemosineDTO<ResourceGroupInfoDTO> info(@RequestBody ResourceGroupInfoRequestDTO requestDTO) {
        return resourceGroupService.groupInfo(requestDTO);
    }

    @Autowired
    private ResourceGroupService resourceGroupService;
}
