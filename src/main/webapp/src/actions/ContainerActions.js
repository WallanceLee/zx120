/**
 * Created by wallance on 3/27/17.
 */
import * as ActionTypes from '../constants/constants';

export function showModal(modal) {
    return (dispatch) => {
        dispatch({
            type: ActionTypes.SHOW_MODAL,
            payload: modal
        });
    };
}

export function closeModal(modal) {
    return (dispatch) => {
        dispatch({
            type: ActionTypes.CLOSE_MODAL,
            payload: modal
        });
    }
}

export function showMessage(message, success = true, loading = false) {
    return (dispatch) => {
        dispatch({
            type: ActionTypes.SHOW_MESSAGE,
            payload: {
                success: success,
                message: message,
                loading: loading
            }
        });
    }
}

export function closeMessage(messageKey) {
    return (dispatch) => {
        dispatch({
            type: ActionTypes.CLOSE_MESSAGE,
            payload: messageKey
        });
    }
}