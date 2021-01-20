package mnemosine.dto.container;

public class ContainerInfoDTO {
    public ContainerInfoDTO(String containerName, String accountName, String lastModifiedDate) {
        this.containerName = containerName;
        this.accountName = accountName;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ContainerInfoDTO() {
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    private String containerName;
    private String accountName;
    private String lastModifiedDate;
}
