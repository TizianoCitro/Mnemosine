package mnemosine.dto.container;

public class ContainerCreateDTO {
    public ContainerCreateDTO(String containerName, String accountName, String lastModifiedDate, String url) {
        this.containerName = containerName;
        this.accountName = accountName;
        this.lastModifiedDate = lastModifiedDate;
        this.url = url;
    }

    public ContainerCreateDTO() {
    }

    public String getName() {
        return containerName;
    }

    public void setName(String containerName) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String containerName;
    private String accountName;
    private String lastModifiedDate;
    private String url;
}
