package cn.edu.njupt.zx120.model;

/**
 * 医护人员类型映射
 * Created by wallance on 1/18/17.
 */
public enum MemberTypeEnum {

    DOCTOR(30, "Doctor"),
    NURSE(40, "Nurse"),
    DRIVER(50, "Driver");

    private int id;
    private String type;

    MemberTypeEnum(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
