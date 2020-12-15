package mnemosine.dto.group;

public class ResourceGroupInfoDTO {
    public ResourceGroupInfoDTO(String groupId, String groupName, String region) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.region = region;
    }

    public ResourceGroupInfoDTO() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String groupId;
    private String groupName;
    private String region;
}
