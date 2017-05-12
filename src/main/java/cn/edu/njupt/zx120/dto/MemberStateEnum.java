package cn.edu.njupt.zx120.dto;

/**
 * Created by wallance on 3/19/17.
 */
public enum MemberStateEnum {

    MEMBER_CREATE_SUCCESSFULLY("员工创建成功"),
    MEMBER_CREATE_FAILED("员工创建失败"),

    MEMBER_NAME_NULL("员工姓名不能为空"),
    MEMBER_TYPE_NULL("员工职务不能为空"),
    MEMBER_ORG_NULL("员工所在机构不能为空"),
    MEMBER_ORG_NOT_EXISTENT("员工所在机构不存在"),
    MEMBER_ADMIN_AREA_NOT_EXISTENT("员工所在行政单位不存在"),
    MEMBER_DEPARTMENT_NULL("员工所在科室不能为空"),
    MEMBER_PHONE_NUM_NULL("员工电话号码不能为空"),
    MEMBER_ADMIN_CODE_NULL("员工行政编码不能为空"),

    MEMBER_NOT_FOUND("没有找到相关员工信息"),
    MEMBER_DELETE_SUCCESSFULLY("删除员工成功"),
    MEMBER_DELETE_FAILED("删除员工失败"),

    MEMBER_ON_DUTY("员工已出勤"),
    MEMBER_OFF("员工已下班"),
    MEMBER_ON_AMBULANCE("员工在急救车上"),

    MEMBER_ON_DUTY_READ_CARD_FAILED("员工出勤打卡失败"),
    MEMBER_OFF_READ_CARD_RAILED("员工下班打卡失败"),

    MEMBER_REGISTER_ON_CAR_FAILED("员工在急救车注册失败");

    private String state;

    MemberStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
