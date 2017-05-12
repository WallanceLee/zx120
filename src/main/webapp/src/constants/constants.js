export const NOTHING = 'NOTHING';

export const SHOW_MODAL = 'SHOW_MODAL';
export const CLOSE_MODAL = 'CLOSE_MODAL';

export const SHOW_MESSAGE = 'SHOW_MESSAGE';
export const CLOSE_MESSAGE = 'CLOSE_MESSAGE';

// 用户和权限管理
export const LOAD_USER_INFO = 'LOAD_USER_INFO';
export const LOGIN = 'LOGIN';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';
export const LOGOUT = 'LOGOUT';
export const LOGOUT_SUCCESS = 'LOGOUT_SUCCESS';
export const LOGOUT_FAILURE = 'LOGOUT_FAILURE';

// 查询用户信息
export const QUERY_USER_INFO = 'QUERY_USER_INFO';
export const QUERY_USER_INFO_SUCCESS = 'QUERY_USER_INFO_SUCCESS';

// 新增用户
export const SAVE_USER_INFO = 'SAVE_USER_INFO';
export const SAVE_USER_INFO_SUCCESS = 'SAVE_USER_INFO_SUCCESS';

// 删除用户
export const DELETE_USER_INFO = 'DELETE_USER_INFO';
export const DELETE_USER_INFO_SUCCESS = 'DELETE_USER_INFO_SUCCESS';

// 分配用户权限
export const ASSIGN_USER_AUTHORITY = 'ASSIGN_USER_AUTHORITY';
export const ASSIGN_USER_AUTHORITY_SUCCESS = 'ASSIGN_USER_AUTHORITY_SUCCESS';

// 更新用户密码
export const UPDATE_USER_PASSWORD = 'UPDATE_USER_PASSWORD';
export const UPDATE_USER_PASSWORD_SUCCESS = 'UPDATE_USER_PASSWORD_SUCCESS';

// 查询权限信息
export const QUERY_AUTHORITY_INFO = 'QUERY_AUTHORITY_INFO';
export const QUERY_AUTHORITY_INFO_SUCCESS = 'QUERY_AUTHORITY_INFO_SUCCESS';

// 新增权限
export const SAVE_AUTHORITY_INFO = 'SAVE_AUTHORITY_INFO';
export const SAVE_AUTHORITY_INFO_SUCCESS = 'SAVE_AUTHORITY_INFO_SUCCESS';

// 删除权限
export const DELETE_AUTHORITY_INFO = 'DELETE_AUTHORITY_INFO';
export const DELETE_AUTHORITY_INFO_SUCCESS = 'DELETE_AUTHORITY_INFO_SUCCESS';

// 业务管理
// 车辆动态
export const QUERY_AMBULANCE_DYNAMIC = 'QUERY_AMBULANCE_DYNAMIC';
export const QUERY_AMBULANCE_DYNAMIC_SUCCESS = 'QUERY_AMBULANCE_DYNAMIC_SUCCESS';

// 车辆库存
export const QUERY_AMBULANCE_STOCK = 'QUERY_AMBULANCE_STOCK';
export const QUERY_AMBULANCE_STOCK_SUCCESS = 'QUERY_AMBULANCE_STOCK_SUCCESS';

export const QUERY_MEMBER_DUTY = 'QUERY_MEMBER_DUTY';
export const QUERY_MEMBER_DUTY_SUCCESS = 'QUERY_MEMBER_DUTY_SUCCESS';

export const QUERY_MEMBER_DYNAMIC = 'QUERY_MEMBER_DYNAMIC';
export const QUERY_MEMBER_DYNAMIC_SUCCESS = 'QUERY_MEMBER_DYNAMIC_SUCCESS';

// 实时机构资源
export const QUERY_REALTIME_ORG_RESOURCE = 'QUERY_REALTIME_ORG_RESOURCE';
export const QUERY_REALTIME_ORG_RESOURCE_SUCCESS = 'QUERY_REALTIME_ORG_RESOURCE_SUCCESS';

// 实时原始记录
export const QUERY_RECORD_LOG = 'QUERY_RECORD_LOG';
export const QUERY_RECORD_LOG_SUCCESS = 'QUERY_RECORD_LOG_SUCCESS';

// 实时时间
export const QUERY_EVENT_INFO = 'QUERY_EVENT_INFO';
export const QUERY_EVENT_INFO_SUCCESS = 'QUERY_EVENT_INFO_SUCCESS';

// 系统维护
// RFID批量绑定
export const BATCH_BIND_RESOURCE_TAG = 'BATCH_BIND_RESOURCE_TAG';
export const BATCH_BIND_RESOURCE_TAG_SUCCESS = 'BATCH_BIND_RESOURCE_TAG_SUCCESS';

// RFID手动绑定
export const MANUAL_BIND_RESOURCE_TAG = 'MANUAL_BIND_RESOURCE_TAG';
export const MANUAL_BIND_RESOURCE_TAG_SUCCESS = 'MANUAL_BIND_RESOURCE_TAG_SUCCESS';

// RFID重新绑定
export const REBIND_RESOURCE_TAG = 'REBIND_RESOURCE_TAG';
export const REBIND_RESOURCE_TAG_SUCCESS = 'REBIND_RESOURCE_TAG_SUCCESS';

// RFID删除绑定
export const UNBIND_RESOURCE_TAG = 'UNBIND_RESOURCE_TAG';
export const UNBIND_RESOURCE_TAG_SUCCESS = 'UNBIND_RESOURCE_TAG_SUCCESS';

// 查询已绑定人员信息
export const QUERY_MEMBER_BOUND = 'QUERY_MEMBER_BOUND';
export const QUERY_MEMBER_BOUND_SUCCESS = 'QUERY_MEMBER_BOUND_SUCCESS';

// 查询今日绑定人员信息
export const QUERY_MEMBER_TODAY_BOUND = 'QUERY_MEMBER_TODAY_BOUND';
export const QUERY_MEMBER_TODAY_BOUND_SUCCESS = 'QUERY_MEMBER_TODAY_BOUND_SUCCESS';

// 查询未绑定人员信息
export const QUERY_MEMBER_UNBOUND = 'QUERY_MEMBER_UNBOUND';
export const QUERY_MEMBER_UNBOUND_SUCCESS = 'QUERY_MEMBER_UNBOUND_SUCCESS';

export const QUERY_AMBULANCE_BOUND = 'QUERY_AMBULANCE_BOUND';
export const QUERY_AMBULANCE_BOUND_SUCCESS = 'QUERY_AMBULANCE_BOUND_SUCCESS';
export const QUERY_AMBULANCE_UNBOUND = 'QUERY_AMBULANCE_UNBOUND';
export const QUERY_AMBULANCE_UNBOUND_SUCCESS = 'QUERY_AMBULANCE_UNBOUND_SUCCESS';
export const QUERY_AMBULANCE_TODAY_BOUND = 'QUERY_AMBULANCE_TODAY_BOUND';
export const QUERY_AMBULANCE_TODAY_BOUND_SUCCESS = 'QUERY_AMBULANCE_TODAY_BOUND_SUCCESS';

// 查询位置信息
export const QUERY_CONTAINER_INFO = 'QUERY_CONTAINER_INFO';
export const QUERY_CONTAINER_INFO_SUCCESS = 'QUERY_CONTAINER_INFO_SUCCESS';

// 新增位置信息
export const SAVE_CONTAINER_INFO = 'SAVE_CONTAINER_INFO';
export const SAVE_CONTAINER_INFO_SUCCESS = 'SAVE_CONTAINER_INFO_SUCCESS';

// 删除位置信息
export const DELETE_CONTAINER_INFO = 'DELETE_CONTAINER_INFO';
export const DELETE_CONTAINER_INFO_SUCCESS = 'DELETE_CONTAINER_INFO_SUCCESS';

// 查询读卡器信息
export const QUERY_READER_INFO = 'QUERY_READER_INFO';
export const QUERY_READER_INFO_SUCCESS = 'QUERY_READER_INFO_SUCCESS';

// 新增读卡器信息
export const SAVE_READER_INFO = 'SAVE_READER_INFO';
export const SAVE_READER_INFO_SUCCESS = 'SAVE_READER_INFO_SUCCESS';

// 更新读卡器参数
export const UPDATE_READER_INFO = 'UPDATE_READER_INFO';
export const UPDATE_READER_INFO_SUCCESS = 'UPDATE_READER_INFO_SUCCESS';

// 打开读卡器
export const ACTIVE_READER = 'ACTIVE_READER';
export const ACTIVE_READER_SUCCESS = 'ACTIVE_READER_SUCCESS';

// 设置打开读卡器时间
export const SET_ACTIVE_READER_TIME = 'SET_ACTIVE_READER_TIME';
export const SET_ACTIVE_READER_TIME_SUCCESS = 'SET_ACTIVE_READER_TIME_SUCCESS';

// 关闭读卡器
export const SHUTDONW_READER = 'SHUTDOWN_READER';
export const SHUTDOWN_READER_SUCCESS = 'SHUTDOWN_READER_SUCCESS';

// 设置关闭读卡器时间
export const SET_SHUTDOWN_READER_TIME = 'SET_SHUTDOWN_READER_TIME';
export const SET_SHUTDOWN_READER_TIME_SUCCESS = 'SET_SHUTDOWN_READER_TIME_SUCCESS';

// 删除读卡器信息
export const DELETE_READER_INFO = 'DELETE_READER_INFO';
export const DELETE_READER_INFO_SUCCESS = 'DETELE_READER_INFO_SUCCESS';

// 按照给定字段，查询资源信息
export const QUERY_CUSTOM_VIEW_INFO = 'QUERY_CUSTOM_VIEW_INFO';
export const QUERY_CUSTOM_VIEW_INFO_SUCCESS = 'QUERY_CUSTOM_VIEW_INFO_SUCCESS';

// 获取资源类型类表
export const QUERY_RESOURCE_TYPE = 'QUERY_RESOURCE_TYPE';
export const QUERY_RESOURCE_TYPE_SUCCESS = 'QUERY_RESOURCE_TYPE_SUCCESS';

// 获取资源字段列表
export const QUERY_RESOURCE_COLUMN_LIST = 'QUERY_RESOURCE_COLUMN_LIST';
export const QUERY_RESOURCE_COLUMN_LIST_SUCCESS = 'QUERY_RESOURCE_COLUMN_LIST_SUCCESS';

export const QUERY_ORG_INFO = 'QUERY_ORG_INFO';
export const QUERY_ORG_INFO_SUCCESS = 'QUERY_ORG_INFO_SUCCESS';

export const QUERY_CONTAINER_TYPE = 'QUERY_CONTAINER_TYPE';
export const QUERY_CONTAINER_TYPE_SUCCESS = 'QUERY_CONTAINER_TYPE_SUCCESS';

export const QUERY_ALL_AMBULANCE = 'QUERY_ALL_AMBULANCE';
export const QUERY_ALL_AMBULANCE_SUCCESS = 'QUERY_ALL_AMBULANCE_SUCCESS';

export const QUERY_CONTAINER_TABLE_TYPE = 'QUERY_CONTAINER_TABLE_TYPE';
export const QUERY_CONTAINER_TABLE_TYPE_SUCCESS = 'QUERY_CONTAINER_TABLE_TYPE_SUCCESS';

export const QUERY_CONTAINER_SCAN_POLICY = 'QUERY_CONTAINER_SCAN_POLICY';
export const QUERY_CONTAINER_SCAN_POLICY_SUCCESS = 'QUERY_CONTAINER_SCAN_POLICY_SUCCESS';

export const QUERY_AMBULANCE_ALL_COLUMN = 'QUERY_AMBULANCE_ALL_COLUMN';
export const QUERY_AMBULANCE_ALL_COLUMN_SUCCESS = 'QUERY_AMBULANCE_ALL_COLUMN_SUCCESS';
export const QUERY_ORGANIZATION_ALL_COLUMN = 'QUERY_ORGANIZATION_ALL_COLUMN';
export const QUERY_ORGANIZATION_ALL_COLUMN_SUCCESS = 'QUERY_ORGANIZATION_ALL_COLUMN_SUCCESS';
export const QUERY_MEMBER_ALL_COLUMN = 'QUERY_MEMBER_ALL_COLUMN';
export const QUERY_MEMBER_ALL_COLUMN_SUCCESS = 'QUERY_MEMBER_ALL_COLUMN_SUCCESS';

export const QUERY_ALL_RESOURCE_TYPES = 'QUERY_RESOURCE_TYPES';
export const QUERY_ALL_RESOURCE_TYPES_SUCCESS = 'QUERY_RESOURCE_TYPES_SUCCESS';

export const QUERY_OPERATION_LOG = 'QUERY_OPERATION_LOG';
export const QUERY_OPERATION_LOG_SUCCESS = 'QUERY_OPERATION_LOG_SUCCESS';

export const QUERY_LOGIN_LOG = 'QUERY_LOGIN_LOG';
export const QUERY_LOGIN_LOG_SUCCESS = 'QUERY_LOGIN_LOG_SUCCESS';

export const QUERY_ERR_LOG = 'QUERY_ERR_LOG';
export const QUERY_ERR_LOG_SUCCESS = 'QUERY_ERR_LOG_SUCCESS';

export const GET_AMBULANCE_DYNAMIC_EXCEL_EXPORT = 'GET_AMBULANCE_DYNAMIC_EXCEL_EXPORT';
export const GET_AMBULANCE_DYNAMIC_EXCEL_EXPORT_SUCCESS = 'GET_AMBULANCE_DYNAMIC_EXCEL_EXPORT_SUCCESS';
