/**
 * Created by wallance on 4/27/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import FlatButton from 'material-ui/FlatButton';

import * as StatisticsActions from '../../actions/StatisticsActions';

class AmbulanceDynamicExcelExportPage extends Component {

    componentWillMount() {

        const { dispatch } = this.props;

        this._actions = bindActionCreators(StatisticsActions, dispatch);
    }

    render() {
        return (
            <div>
                <FlatButton label={'导出到EXCEL'}
                            onTouchTap={
                                (event) => {
                                    window.open('http://127.0.0.1:8080/api/statistics/ambulanceDynamic.json?type=null')
                                }
                            }/>
            </div>
        );
    }

}

export default connect()(AmbulanceDynamicExcelExportPage);
