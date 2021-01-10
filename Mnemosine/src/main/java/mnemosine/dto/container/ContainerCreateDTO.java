package mnemosine.dto.container;

public class ContainerCreateDTO {
    public ContainerCreateDTO(String name, String accountName, String lastModifiedDate, String url) {
        this.name = name;
        this.accountName = accountName;
        this.lastModifiedDate = lastModifiedDate;
        this.url = url;
    }

    public ContainerCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String name;
    private String accountName;
    private String lastModifiedDate;
    private String url;
}
