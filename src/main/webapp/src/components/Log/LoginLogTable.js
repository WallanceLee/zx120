/**
 * Created by wallance on 4/26/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import * as LogActions from '../../actions/LogActions';

class LoginLogTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(LogActions, dispatch);
        this._actions.queryLoginLog();
    }

    renderTableRow() {
        var tableRow = [];
        this.props.loginLogs.forEach((loginLog) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{loginLog.account}</TableRowColumn>
                    <TableRowColumn>{loginLog.username}</TableRowColumn>
                    <TableRowColumn>{loginLog.host}</TableRowColumn>
                    <TableRowColumn>{loginLog.loginTime}</TableRowColumn>
                    <TableRowColumn>{loginLog.message}</TableRowColumn>
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
                    <TableHeaderColumn>登录时间</TableHeaderColumn>
                    <TableHeaderColumn>消息</TableHeaderColumn>
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
        loginLogs: state.LogReducer.loginLogs
    };
};

export default connect(mapStateToProps)(LoginLogTable);