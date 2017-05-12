/**
 * Created by wallance on 4/25/17.
 */
import * as ActionTypes from '../constants/constants';

export function queryAuthorityInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AUTHORITY_INFO],
            req: {
                method: 'query',
                url: '/api/authority/list.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_AUTHORITY_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function saveAuthorityInfo(type, department, _function, param) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.SAVE_AUTHORITY_INFO],
            req: {
                method: 'post',
                url: '/api/authority/create.json',
                param: {
                    type: type,
                    department: department,
                    _function: _function,
                    param: param
                }
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.SAVE_AUTHORITY_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function deleteAuthorityInfo(type) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.DELETE_AUTHORITY_INFO],
            req: {
                method: 'del',
                url: '/api/authority/delete.json?type=' + type
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.DELETE_AUTHORITY_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}
