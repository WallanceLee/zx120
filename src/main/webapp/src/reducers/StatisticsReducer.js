/**
 * Created by wallance on 4/27/17.
 */
import * as ActionTypes from '../constants/constants';

var initialState = {

};

export default function check(state = initialState, action) {

    const { type, payload } = action;

    switch (type) {
        case ActionTypes.GET_AMBULANCE_DYNAMIC_EXCEL_EXPORT_SUCCESS:
            return Object.assign({}, state, {
                payload: payload
            });
        default:
            return state;
    }

}