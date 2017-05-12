/**
 * Created by wallance on 3/13/17.
 */
import React from 'react';

import * as ActionTypes from '../constants/constants';

import * as ContainerActions from '../actions/ContainerActions';

import * as http from '../utils/http';

import LoginModal from '../components/User/LoginModal';

let serverUrl = 'http://localhost:8080';

export default ({dispatch}) => next => action => {
    const { type, payload = null, req = {} } = action;
    if (!type || type.constructor !== Array) return next(action);

    const [ BEGIN, SUCCESS, FAILURE, showMessage = true ] = type;

    const { method, url, param } = req;

    dispatch({
        type: BEGIN || ActionTypes.NOTHING,
        payload: payload
    });

    const { onSuccess = null, onFailure = null } = action;

    http[method](
        serverUrl + url, param,
        (res) => {
            if (SUCCESS && SUCCESS.constructor === Array) {
                SUCCESS.forEach(TYPE => dispatch({
                    type: TYPE || ActionTypes.NOTHING,
                    payload: payload || res.body
                }));
            } else {
                dispatch({
                    type: SUCCESS || ActionTypes.NOTHING,
                    payload: payload || res.body
                });
            }
            if (res.body.message && showMessage) {
                dispatch(ContainerActions.showMessage(res.body.message));
            }
            !onSuccess || onSuccess(res.body)
        },
        (err, res) => {
            if (err && 401 === err.status) {
                dispatch(ContainerActions.showModal(<LoginModal/>));
            }
            if (res.body.message && showMessage) {
                dispatch(ContainerActions.showMessage(res.body.message, false));
            }
            dispatch({
                type: FAILURE || ActionTypes.NOTHING,
                payload: {
                    err: err,
                    res: res
                }
            });
            !onFailure || onFailure(err, res);
        }
    );
}
