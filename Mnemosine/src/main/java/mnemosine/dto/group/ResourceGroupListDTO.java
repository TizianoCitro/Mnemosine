package mnemosine.dto.group;

import java.util.ArrayList;

public class ResourceGroupListDTO {
    public ResourceGroupListDTO(ArrayList<String> groupNames) {
        this.groupNames = groupNames;
    }

    public ResourceGroupListDTO() {
        groupNames = new ArrayList<>();
    }

    public void addGroupName(String groupName) {
        groupNames.add(groupName);
    }

    public ArrayList<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(ArrayList<String> groupNames) {
        this.groupNames = groupNames;
    }

    private ArrayList<String> groupNames;
}
