package cn.edu.njupt.zx120.dto;

/**
 * Created by wallance on 3/19/17.
 */
public enum ResourceBindingStateEnum {

    RESOURCE_BINDING_NOT_FOUND("没有找到资源绑定信息"),
    RESOURCE_BINDING_LIST_NOT_FOUND("没有找到资源绑定列表"),
    RESOURCE_BINDING_DELETE_SUCCESSFULLY("资源解除绑定成功"),
    RESOURCE_BINDING_DELETE_FAILED("资源解除绑定失败"),
    RESOURCE_BINDING_CREATE_SUCCESSFULLY("资源绑定成功"),
    RESOURCE_BINDING_REBIND_SUCCESSFULLY("资源重新绑定成功"),
    RESOURCE_BINDING_REBIND_FAILED("资源重新绑定失败"),
    RESOURCE_BINDING_CREATE_FAILED("资源绑定失败"),
    RESOURCE_BINDING_RESOURCE_ID_NULL("资源id不能为空"),
    RESOURCE_BINDING_RESOURCE_TABLE_NULL("资源表不能为空"),
    RESOURCE_BINDING_RESOURCE_NAME_NULL("资源名称不能为空"),
    RESOURCE_BINDING_RESOURCE_TYPE_NULL("资源类型不能为空"),
    RESOURCE_BINDING_RESOURCE_TAG_NULL("资源标签不能为空"),
    RESOURCE_BINDING_UPDATE_BINDING_TIME_FAILED("更新资源绑定时间失败");

    private String state;

    ResourceBindingStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
