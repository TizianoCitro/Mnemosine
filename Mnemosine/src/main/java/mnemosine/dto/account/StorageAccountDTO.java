package mnemosine.dto.account;

public class StorageAccountDTO {
    public StorageAccountDTO(String id, String name, String group, String region) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.region = region;
    }

    public StorageAccountDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String id;
    private String name;
    private String group;
    private String region;
}
