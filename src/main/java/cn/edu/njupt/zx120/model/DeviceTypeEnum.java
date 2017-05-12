package cn.edu.njupt.zx120.model;

/**
 * 设备类型枚举
 * OXYGEN_BOTTLE 氧气瓶
 * SHELF 担架
 * MACHINE 其他医用机器
 * Created by wallance on 3/5/17.
 */
public enum DeviceTypeEnum {
    OXYGEN_BOTTOLE("Oxygen Bottle"),
    SHELF("Shelf"),
    MACHINE("Machine");

    private String type;

    DeviceTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
