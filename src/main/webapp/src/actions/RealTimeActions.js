/**
 * Created by wallance on 4/16/17.
 */
import * as ActionTypes from '../constants/constants';

export function getRealTimeOrgResource() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_REALTIME_ORG_RESOURCE],
            req: {
                method: 'query',
                url: '/api/realtime/orgResource.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_REALTIME_ORG_RESOURCE_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {
                console.log(err);
                console.log(res);
            }
        });
    }
}

export function getRecordLog() {
    console.log("I am here getRecordLog");
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_RECORD_LOG],
            req: {
                method: 'query',
                url: '/api/realtime/originalRecord.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_RECORD_LOG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {
                console.log(err);
                console.log(res);
            }
        })
    }
}

export function getEventInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_EVENT_INFO],
            req: {
                method: 'query',
                url: '/api/realtime/event.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_EVENT_INFO_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {
                console.log(err);
                console.log(res);
            }
        });
    }
}