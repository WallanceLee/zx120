/**
 * Created by wallance on 4/15/17.
 */
import React, { Component } from 'react';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn } from 'material-ui/Table';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import * as ServiceManageActions from '../../actions/ServiceManageActions';

class AmbulanceDynamicTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        const actions = bindActionCreators(ServiceManageActions, dispatch);
        actions.getAmbulanceDynamicInfo();
    }

    renderRow() {
        var tableRows = [];
        if (typeof(this.props.data) !== 'undefined' && this.props.data.hasOwnProperty('entity')) {
            this.props.data.entity.forEach((item) => {
                tableRows.push(
                    <TableRow>
                        <TableRowColumn>{item.carBrand}</TableRowColumn>
                        <TableRowColumn>{item.eventType}</TableRowColumn>
                        <TableRowColumn>{item.containerName}</TableRowColumn>
                        <TableRowColumn>{item.time}</TableRowColumn>
                    </TableRow>
                );
            });
        }
        return tableRows;
    }

    render() {
        return (
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHeaderColumn>车牌号</TableHeaderColumn>
                        <TableHeaderColumn>事件类型</TableHeaderColumn>
                        <TableHeaderColumn>所在位置</TableHeaderColumn>
                        <TableHeaderColumn>时间</TableHeaderColumn>
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {this.renderRow()}
                </TableBody>
            </Table>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        data: state.ServiceManageReducer.ambulanceDynamics
    }
};

export default connect(mapStateToProps)(AmbulanceDynamicTable);
