package cn.edu.njupt.zx120.model;

/**
 * Created by wallance on 3/13/17.
 */
public enum ResourceTableTypeEnum {
    MEMBER("sys_member_info"),
    DEVICE("device_info"),
    AMBULANCE("sys_ambul_info"),
    ORGANIZATION("sys_org_info");

    private String type;

    ResourceTableTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
