package cn.edu.njupt.zx120.model;

/**
 * 容器类型枚举
 * AMBULANCE->救护车
 * STOCK->仓库
 * DEPARTMENT->办公室
 * RESTROOM->休息环境下调用
 * OTHERS->非上述容器的环境调用
 * Created by wallance on 2/24/17.
 */
public enum ContainerTypeEnum {

    AMBULANCE("AMBULANCE"),
    STOCK("STOCK"),
    OFFICE("OFFICE"),
    REST("Rest Room"),
    OTHERS("OTHERS");

    private String type;

    private ContainerTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
