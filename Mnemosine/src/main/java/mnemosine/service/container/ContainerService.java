package mnemosine.service.container;

import com.azure.storage.blob.BlobContainerClient;
import com.microsoft.azure.management.Azure;
import mnemosine.dto.MnemosineDTO;
import mnemosine.dto.container.ContainerCreateDTO;
import mnemosine.dto.container.ContainerCreateRequestDTO;
import mnemosine.dto.container.ContainerDeleteDTO;
import mnemosine.dto.container.ContainerDeleteRequestDTO;
import mnemosine.dto.container.ContainerInfoDTO;
import mnemosine.dto.container.ContainerInfoRequestDTO;
import mnemosine.dto.container.ContainerListDTO;
import mnemosine.dto.container.ContainerListRequestDTO;

public interface ContainerService {
    public MnemosineDTO<ContainerCreateDTO> create(ContainerCreateRequestDTO requestDTO);
    public MnemosineDTO<ContainerDeleteDTO> delete(ContainerDeleteRequestDTO requestDTO);
    public MnemosineDTO<ContainerListDTO> listContainersByStorageAccount(ContainerListRequestDTO requestDTO);
    public MnemosineDTO<ContainerInfoDTO> info(ContainerInfoRequestDTO requestDTO);

    public BlobContainerClient getContainer(Azure azure, String groupName, String accountName, String containerName);
}
