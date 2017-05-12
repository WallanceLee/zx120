/**
 * Created by wallance on 4/26/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import * as LogActions from '../../actions/LogActions';

class OperationLogTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(LogActions, dispatch);
        this._actions.queryOperLog();
    }

    renderTableRow() {
        var tableRow = [];
        this.props.operLogs.forEach((operLog) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{operLog.account}</TableRowColumn>
                    <TableRowColumn>{operLog.username}</TableRowColumn>
                    <TableRowColumn>{operLog.host}</TableRowColumn>
                    <TableRowColumn>{operLog.methodName}</TableRowColumn>
                    <TableRowColumn>{operLog.spentTime}</TableRowColumn>
                    <TableRowColumn>{operLog.occurTime}</TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    render() {
        return (
            <Table>
                <TableHeader displaySelectAll={false}>
                    <TableHeaderColumn>用户名</TableHeaderColumn>
                    <TableHeaderColumn>姓名</TableHeaderColumn>
                    <TableHeaderColumn>主机名/IP</TableHeaderColumn>
                    <TableHeaderColumn>方法</TableHeaderColumn>
                    <TableHeaderColumn>耗时</TableHeaderColumn>
                    <TableHeaderColumn>发生时间</TableHeaderColumn>
                </TableHeader>
                <TableBody displayRowCheckbox={false}>
                    {this.renderTableRow()}
                </TableBody>
            </Table>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        operLogs: state.LogReducer.operLogs
    };
};

export default connect(mapStateToProps)(OperationLogTable);
