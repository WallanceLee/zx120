/**
 * Created by wallance on 4/26/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import * as LogActions from '../../actions/LogActions';

class ErrLogTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(LogActions, dispatch);
        this._actions.queryErrLog();
    }

    renderTableRow() {
        var tableRow = [];
        this.props.errLogs.forEach((errLog) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{errLog.account}</TableRowColumn>
                    <TableRowColumn>{errLog.host}</TableRowColumn>
                    <TableRowColumn>{errLog.methodName}</TableRowColumn>
                    <TableRowColumn>{errLog.exception}</TableRowColumn>
                    <TableRowColumn>{errLog.occurTime}</TableRowColumn>
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
                    <TableHeaderColumn>主机名/IP</TableHeaderColumn>
                    <TableHeaderColumn>方法</TableHeaderColumn>
                    <TableHeaderColumn>异常</TableHeaderColumn>
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
        errLogs: state.LogReducer.errLogs
    };
};

export default connect(mapStateToProps)(ErrLogTable);
