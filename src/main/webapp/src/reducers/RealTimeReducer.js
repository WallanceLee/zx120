/**
 * Created by wallance on 4/16/17.
 */
import * as ActionTypes from '../constants/constants';

const initialState = {
    events: [],
    records: [],
    orgResources: []
};

export default function realtime(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case ActionTypes.QUERY_EVENT_INFO_SUCCESS:
            // console.log("11111111111111111111111");
            // console.log(payload.entity);
            return {
                events: payload.entity
            }
        case ActionTypes.QUERY_REALTIME_ORG_RESOURCE_SUCCESS:
            // console.log("22222222222222222222222");
            // console.log(payload.entity);
            return {
                orgResources: payload.entity
            };
        case ActionTypes.QUERY_RECORD_LOG_SUCCESS:
            // console.log("333333333333333333333333");
            // console.log(payload.entity);
            return {
                records: payload.entity
            };
        default:
            return state;
    }
}
