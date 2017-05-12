/**
 * Created by wallance on 3/13/17.
 */
import * as ActionTypes from '../constants/constants';

const initialState = {
    username: "",
    type: "",
    loggedIn: false,
    users: [],
    authorities: []
};

export default function user(state = initialState, action = {}) {
    const { type, payload } = action;
    switch (type) {
        case ActionTypes.LOAD_USER_INFO:
            return payload;
        case ActionTypes.LOGIN_SUCCESS:
            return {
                username: action.payload.entity.account,
                type: action.payload.entity.authorities[0].type,
                loggedIn: true
            };
        case ActionTypes.LOGIN_FAILURE:
            return {
                userInfo: state.userInfo,
                loggedIn: false
            };
        case ActionTypes.LOGOUT:
            return {
                userInfo: {},
                loggedIn: false
            };
        case ActionTypes.QUERY_USER_INFO_SUCCESS:
            return Object.assign({}, state, {
                users: payload.entity
            });
        case ActionTypes.QUERY_AUTHORITY_INFO_SUCCESS:
            return Object.assign({}, state, {
                authorities: payload.entity
            });
        default:
            return state;
    }
}
