package cn.edu.njupt.zx120.model;

/**
 * 机构类型映射
 * Created by wallance on 1/18/17.
 */
public enum OrgTypeEnum {

    FIRST_AID(30, "急救站"),
    HOSPITAL(40, "医院");

    private int typeId;
    private String type;

    OrgTypeEnum(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getType() {
        return type;
    }
}
