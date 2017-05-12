/**
 * Created by wallance on 4/27/17.
 */
import * as ActionTypes from '../constants/constants';

export function getAmbulanceDynamicExcelExport() {
    return (dispatch) => {
        dispatch({
            type: [],
            req: {
                method: 'download',
                url: ''
            },
            onSuccess: payload => {
                console.log("I am a test of success");
                window.open('http://127.0.0.1:8080/api/statistics/ambulanceDynamic.json?type=null')
            },
            onFailure: (err, res) => {

            }
        });
    }
}
