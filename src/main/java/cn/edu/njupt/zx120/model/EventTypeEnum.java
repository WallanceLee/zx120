package cn.edu.njupt.zx120.model;

/**
 * 事件类型枚举类
 * Created by wallance on 2/24/17.
 */
public enum EventTypeEnum {

    // 员工下班、休息调用此状态
    MEMBER_REST("Member rest"),
    // 员工上班调用此状态
    MEMBER_IN_OFFICE("Member in original office"),
    // 员工跟随救护车调用此状态
    MEMBER_IN_AMBULANCE("Member in ambulance"),
    // 设备到达仓库
    DEVICE_ARRIVE_STOCK("Device arrived stock"),
    // 设备离开仓库
    DEVICE_LEAVE_STOCK("Device left stock"),
    // 设备跟随救护车
    DEVICE_ON_AMBULANCE("Device on ambulance"),
    // 设备状态不属于上面三种的任一种
    DEVICE_UNKNOWN("Device unknown"),
    // 救护车返回、到达目的地调用此状态
    AMBULANCE_ARRIVE_CONTAINER("AMBULANCE in stock"),
    // 救护车离开医院调用此状态
    AMBULANCE_LEAVE_CONTAINER("AMBULANCE on duty"),
    // 救护车被注销调用此状态
    AMBULANCE_DISABLED("AMBULANCE disabled");

    private String type;

    EventTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
