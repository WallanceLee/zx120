package cn.edu.njupt.zx120.model;

/**
 * Created by wallance on 1/13/17.
 * 这是用户权限类型枚举类，用户的类型只能
 * 来源于以下的类型。
 * USER是普通用户
 * ADMIN是管理员
 * SCHEDULER是调度员
 */
public enum AuthorityTypeEnum {

    USER("USER"),
    ADMIN("ADMIN"),
    SCHEDULER("SCHEDULER"),
    STOCK("STOCK"),
    RFID("RFID");

    String authorityType;

    AuthorityTypeEnum(String authorityType) {
        this.authorityType = authorityType;
    }

    public String getAuthorityType() {
        return authorityType;
    }

}
