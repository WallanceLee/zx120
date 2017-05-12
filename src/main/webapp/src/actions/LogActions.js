/**
 * Created by wallance on 4/26/17.
 */
import * as ActionTypes from '../constants/constants';

export function queryLoginLog() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_LOGIN_LOG],
            req: {
                method: 'get',
                url: '/api/log/login.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_LOGIN_LOG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryOperLog() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_OPERATION_LOG],
            req: {
                method: 'get',
                url: '/api/log/oper.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_OPERATION_LOG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryErrLog() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_ERR_LOG],
            req: {
                method: 'get',
                url: '/api/log/err.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_ERR_LOG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}
