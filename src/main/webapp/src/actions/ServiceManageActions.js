/**
 * Created by wallance on 4/15/17.
 */
import * as ActionTypes from '../constants/constants';

export function getAmbulanceStockInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AMBULANCE_STOCK],
            req: {
                method: 'query',
                url: '/api/serviceManage/ambulance/stock.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_STOCK_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function getAmbulanceDynamicInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AMBULANCE_DYNAMIC],
            req: {
                method: 'get',
                url: '/api/serviceManage/ambulance/dynamic.json',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_DYNAMIC_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function getMemberDutyInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_DUTY],
            req: {
                method: 'get',
                url: '/api/serviceManage/member/duty.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_DUTY_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function getMemberDynamicInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_DYNAMIC],
            req: {
                method: 'get',
                url: '/api/serviceManage/member/dynamic.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_DYNAMIC_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}
