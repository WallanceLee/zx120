package cn.edu.njupt.zx120.dto;

/**
 * Created by wallance on 3/22/17.
 */
public enum AuthStateEnum {

    WRONG_USERNAME_OR_PASSOWRD("用户名或密码错误"),
    LOGIN_SUCCESSFULLY("登录成功"),
    LOGOUT_SUCCESSFULLY("登出成功");

    private String type;

    AuthStateEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
