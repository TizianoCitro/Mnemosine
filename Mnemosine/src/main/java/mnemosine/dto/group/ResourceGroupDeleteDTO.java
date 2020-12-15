package mnemosine.dto.group;

public class ResourceGroupDeleteDTO {
    public ResourceGroupDeleteDTO(String deletedGroup) {
        this.deletedGroup = deletedGroup;
    }

    public ResourceGroupDeleteDTO() {
    }

    public String getDeletedGroup() {
        return deletedGroup;
    }

    public void setDeletedGroup(String deletedGroup) {
        this.deletedGroup = deletedGroup;
    }

    private String deletedGroup;
}
