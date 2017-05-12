package cn.edu.njupt.zx120.model;

/**
 * Created by wallance on 3/3/17.
 */
public enum ResourceTypeEnum {

    MEMBER("member"),
    AMBULANCE("ambulance"),
    ORGANIZATION("organization");

    private String type;

    private ResourceTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
