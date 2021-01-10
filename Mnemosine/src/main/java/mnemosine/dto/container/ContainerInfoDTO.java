package mnemosine.dto.container;

public class ContainerInfoDTO {
    public ContainerInfoDTO(String name, String account, String lastModifiedDate) {
        this.name = name;
        this.account = account;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ContainerInfoDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    private String name;
    private String account;
    private String lastModifiedDate;
}
