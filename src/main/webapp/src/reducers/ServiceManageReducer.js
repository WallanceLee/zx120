/**
 * Created by wallance on 4/15/17.
 */
import * as ActionTypes from '../constants/constants';

const initialState = {
    ambulanceStocks: [],
    ambulanceDynamics: [],
    memberDuties: [],
    memberDynamics: []
};

export default function serviceManage(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case ActionTypes.QUERY_AMBULANCE_STOCK_SUCCESS:
            return {
                ambulanceStocks: payload
            };
        case ActionTypes.QUERY_AMBULANCE_DYNAMIC_SUCCESS:
            return {
                ambulanceDynamics: payload
            };
        case ActionTypes.QUERY_MEMBER_DUTY_SUCCESS:
            return {
                memberDuties: payload
            }
        case ActionTypes.QUERY_MEMBER_DYNAMIC_SUCCESS:
            return {
                memberDynamics: payload
            }
        default:
            return state;
    }
}