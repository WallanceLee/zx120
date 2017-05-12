/**
 * Created by wallance on 2/26/17.
 */
import * as ActionTypes from '../constants/constants';
import { encoding, decoding } from '../utils/base64';
import { browserHistory } from 'react-router';
import { request401Resend, clearRequest401List, getRequest401ListLength } from '../utils/http';

export function loadUserInfo() {
    return dispatch => dispatch({
        type: ActionTypes.LOAD_USER_INFO,
        payload: {
            userInfo: JSON.parse(localStorage.getItem('userInfo') || '{}'),
            loggedIn: JSON.parse(localStorage.getItem('loggedIn') || 'false')
        }
    });
}

export function login(account, password) {
    return (dispatch, getState) => {
        dispatch({
            type: [ActionTypes.CLOSE_MODAL, ActionTypes.LOGIN_FAILURE],
            req: {
                method: 'post',
                url: '/login.json',
                param: {
                    auth: encoding(account + ":" + password)
                }
            },
            onSuccess: payload => {
                console.log(payload);
                localStorage.setItem('userInfo', JSON.stringify(payload.entity));
                localStorage.setItem('loggedIn', JSON.stringify(true));
                const userState = getState().user;
                dispatch({
                    type: ActionTypes.LOGIN_SUCCESS,
                    payload: payload
                });
                if ( getRequest401ListLength() === 0 || !userState.userInfo.authorityList ||
                        userState.userInfo.authorities[0].url !== payload.entity.authorities[0].url
                ) {
                    clearRequest401List();
                    browserHistory.push("/");
                } else {
                    request401Resend();
                }
            },
            onFailure: (err, res) => {
                console.log(encoding(account + ":" + password));
                localStorage.removeItem('userInfo');
                localStorage.removeItem('loggedIn');
                localStorage.removeItem('token')
            }
        });
    }
}

export function logout() {
    return dispatch => {
        localStorage.removeItem('userInfo');
        localStorage.removeItem('loggedIn');
        localStorage.removeItem('token');
        dispatch({
            type: ActionTypes.LOGOUT
        });
        history.get().push('/')
    }
}

export function queryUserInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_USER_INFO],
            req: {
                url: '/api/user/getList.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_USER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function saveUserInfo(account, password, username, department) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.SAVE_USER_INFO],
            req: {
                method: 'post',
                url: '/api/user/create.json',
                param: {
                    info: encoding(account + ":" + password + ":" + username + ":" + department)
                }
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.SAVE_READER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function deleteUserInfo(account) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.DELETE_USER_INFO],
            req: {
                method: 'del',
                url: '/api/user/delete.json?account=' + account
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.DELETE_USER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function updateUserPassword(account, oldPassword, newPassword, newPasswordConfirmation) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.UPDATE_USER_PASSWORD],
            req: {
                method: 'post',
                url: '/api/user/updatePassword.json',
                param: {
                    account: account,
                    oldPassword: oldPassword,
                    newPassword: newPassword,
                    newPasswordConfirmation: newPasswordConfirmation
                }
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.UPDATE_USER_PASSWORD_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function assignAuthority(account, type) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.ASSIGN_USER_AUTHORITY],
            req: {
                method: 'post',
                url: '/api/user/assignRole.json?account=' + account + '&type=' + type
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.ASSIGN_USER_AUTHORITY_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}