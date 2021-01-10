package mnemosine.dto.container;

public class ContainerDeleteDTO {
    public ContainerDeleteDTO(String deletedContainer) {
        this.deletedContainer = deletedContainer;
    }

    public ContainerDeleteDTO() {
    }

    public String getDeletedContainer() {
        return deletedContainer;
    }

    public void setDeletedContainer(String deletedContainer) {
        this.deletedContainer = deletedContainer;
    }

    private String deletedContainer;
}
