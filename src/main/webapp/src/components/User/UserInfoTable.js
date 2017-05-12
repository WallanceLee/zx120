/**
 * Created by wallance on 3/27/17.
 */
import React from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import FlatButton from 'material-ui/FlatButton';

import * as UserActions from '../../actions/UserActions';

class UserInfoTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currentUsername: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(UserActions, dispatch);
        this._actions.queryUserInfo();
    }

    handleCreateNewUser() {

    }

    handleUpdatePassword() {

    }

    handleRemoveAuthority() {
        this._actions.deleteUserInfo(this.state.currentUsername);
        this._action.queryUserInfo();
    }

    handleModifyAuthority() {

    }

    handleRowSelection(selectedRows) {
        if (typeof(selectedRows) != 'undefined') {
            this.setState({
                currentUsername: this.props.users[selectedRows[0]].account
            });
            console.log(this.props.users[selectedRows[0]].account);
        }
    }

    renderTableRow() {
        var tableRow = [];
        this.props.users.forEach((user) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{user.account}</TableRowColumn>
                    <TableRowColumn>{user.password}</TableRowColumn>
                    <TableRowColumn>{user.department}</TableRowColumn>
                    <TableRowColumn>{user.username}</TableRowColumn>
                    <TableRowColumn>
                        <FlatButton label={'修改密码'} onTouchTap={this.handleUpdatePassword.bind(this)}/>
                        <FlatButton label={'删除'} onTouchTap={this.handleRemoveAuthority.bind(this)}/>
                        <FlatButton label={'修改权限'} onTouchTap={this.handleModifyAuthority.bind(this)}/>
                    </TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    render() {
        return(
            <div>
                <FlatButton label={'新增用户'} onTouchTap={this.handleCreateNewUser.bind(this)}/>
                <Table onRowSelection={this.handleRowSelection.bind(this)}>
                    <TableHeader displaySelectAll={false}>
                        <TableHeaderColumn>用户名</TableHeaderColumn>
                        <TableHeaderColumn>密码</TableHeaderColumn>
                        <TableHeaderColumn>部门</TableHeaderColumn>
                        <TableHeaderColumn>姓名</TableHeaderColumn>
                        <TableHeaderColumn>操作</TableHeaderColumn>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {this.renderTableRow()}
                    </TableBody>
                </Table>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        users: state.UserReducer.users
    };
};

export default connect(mapStateToProps)(UserInfoTable);
