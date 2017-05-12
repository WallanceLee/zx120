/**
 * Created by wallance on 4/20/17.
 */
import * as ActionTypes from '../constants/constants';

const initialState = {
    // 位置信息
    containers: [],
    containerTypes: [],
    // 读卡器信息
    readers: [],
    // 已绑定车辆信息
    boundAmbulances: [],
    // 未绑定车辆信息
    unboundAmbulances: [],
    // 今日绑定车辆信息
    todayBoundAmbulances: [],
    // 已绑定人员信息
    boundMembers: [],
    // 未绑定人员信息
    unboundMembers: [],
    // 今日绑定车辆信息
    todayBoundMembers: [],
    // 资源类型
    resourceTypes: [],
    // 资源字段信息列表
    columnInfoList: [],
    organizations: [],
    containerTableTypes: [],
    ambulances: [],
    scanPolicies: [],
    ambulancesWithAllColumn: [],
    organizationsWithAllColumn: [],
    membersWithAllColumn: []
};

export default function admin(state = initialState, action) {
    const { type, payload } = action;

    switch (type) {
        case ActionTypes.QUERY_MEMBER_BOUND_SUCCESS:
            return Object.assign({}, state, {
                boundMembers: payload.entity
            });
        case ActionTypes.QUERY_MEMBER_UNBOUND_SUCCESS:
            return Object.assign({}, state, {
                unboundMembers: payload.entity
            });
        case ActionTypes.QUERY_MEMBER_TODAY_BOUND_SUCCESS:
            return Object.assign({}, state, {
                todayBoundMembers: payload.entity
            });
        case ActionTypes.QUERY_AMBULANCE_BOUND_SUCCESS:
            return Object.assign({}, state, {
                boundAmbulances: payload.entity
            });
        case ActionTypes.QUERY_AMBULANCE_UNBOUND_SUCCESS:
            return Object.assign({}, state, {
                unboundAmbulances: payload.entity
            });
        case ActionTypes.QUERY_AMBULANCE_TODAY_BOUND_SUCCESS:
            return Object.assign({}, state, {
                todayBoundAmbulances: payload.entity
            });
        // case ActionTypes.QUERY_RESOURCE_TYPE_SUCCESS:
        //     return Object.assign({}, state, {
        //         resourceTypes: payload.entity
        //     });
        case ActionTypes.QUERY_RESOURCE_COLUMN_LIST_SUCCESS:
            return Object.assign({}, state, {
                resourceColumns: payload.entity
            });
        case ActionTypes.QUERY_CUSTOM_VIEW_INFO_SUCCESS:

        case ActionTypes.QUERY_CONTAINER_INFO_SUCCESS:
            // return {
            //     containers: payload.entity
            // };
            return Object.assign({},
                state, {
                containers: payload.entity
            });
        case ActionTypes.DELETE_CONTAINER_INFO_SUCCESS:
        case ActionTypes.SAVE_CONTAINER_INFO_SUCCESS:
        case ActionTypes.QUERY_CONTAINER_TYPE_SUCCESS:
            return Object.assign({}, state, {
                containerTypes: payload.entity
            });
        case ActionTypes.QUERY_ALL_AMBULANCE_SUCCESS:
            return Object.assign({}, state, {
                ambulances: payload.entity
            });
        case ActionTypes.QUERY_READER_INFO_SUCCESS:
            return Object.assign({},
                state, {
                readers: payload.entity
            });
        case ActionTypes.QUERY_CONTAINER_SCAN_POLICY_SUCCESS:
            return Object.assign({}, state, {
                scanPolicies: payload.entity
            });
        case ActionTypes.SAVE_READER_INFO_SUCCESS:
        case ActionTypes.UPDATE_READER_INFO_SUCCESS:
        case ActionTypes.DELETE_READER_INFO_SUCCESS:
        case ActionTypes.QUERY_ORG_INFO_SUCCESS:
            return Object.assign({},
                state, {
                organizations: payload.entity
            });
        case ActionTypes.QUERY_CONTAINER_TABLE_TYPE_SUCCESS:
            return Object.assign({}, state, {
                containerTableTypes: payload.entity
            });
        case ActionTypes.QUERY_AMBULANCE_ALL_COLUMN_SUCCESS:
            return Object.assign({}, state, {
                ambulancesWithAllColumn: payload.entity
            });
        case ActionTypes.QUERY_MEMBER_ALL_COLUMN_SUCCESS:
            return Object.assign({}, state, {
                membersWithAllColumn: payload.entity
            });
        case ActionTypes.QUERY_ORGANIZATION_ALL_COLUMN_SUCCESS:
            return Object.assign({}, state, {
                organizationsWithAllColumn: payload.entity
            });
        case ActionTypes.QUERY_ALL_RESOURCE_TYPES_SUCCESS:
            return Object.assign({}, state, {
                resourceTypes: payload.entity
            });
        default:
            return state;
    }

}