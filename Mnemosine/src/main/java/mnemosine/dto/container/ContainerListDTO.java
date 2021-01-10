package mnemosine.dto.container;

import java.util.ArrayList;

public class ContainerListDTO {
    public ContainerListDTO(ArrayList<ContainerInfoDTO> containers) {
        this.containers = containers;
    }

    public ContainerListDTO() {
        containers = new ArrayList<>();
    }

    public void addContainer(ContainerInfoDTO container) {
        containers.add(container);
    }

    public ArrayList<ContainerInfoDTO> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<ContainerInfoDTO> containers) {
        this.containers = containers;
    }

    private ArrayList<ContainerInfoDTO> containers;
}
