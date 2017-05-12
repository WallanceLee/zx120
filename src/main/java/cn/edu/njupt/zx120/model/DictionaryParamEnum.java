package cn.edu.njupt.zx120.model;

/**
 * Created by wallance on 4/23/17.
 */
public enum DictionaryParamEnum {

    CONTAINER("container_type"),
    SCAN_POLICY("scan_policy"),
    RESOURCE("res_type"),
    ORGANIZATION("org_type"),
    EVENT("event_type");

    private String param;

    DictionaryParamEnum(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

}
