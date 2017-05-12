/**
 * Created by wallance on 4/26/17.
 */
import * as ActionTypes from '../constants/constants';

var initialState = {
    operLogs: [],
    errLogs: [],
    loginLogs: []
};

export default function log(state = initialState, action) {

    const { type, payload } = action;

    switch (type) {
        case ActionTypes.QUERY_ERR_LOG_SUCCESS:
            return Object.assign({}, state, {
                errLogs: payload.entity
            });
        case ActionTypes.QUERY_LOGIN_LOG_SUCCESS:
            return Object.assign({}, state, {
                loginLogs: payload.entity
            });
        case ActionTypes.QUERY_OPERATION_LOG_SUCCESS:
            return Object.assign({}, state, {
                operLogs: payload.entity
            });
        default:
            return state;
    }

}