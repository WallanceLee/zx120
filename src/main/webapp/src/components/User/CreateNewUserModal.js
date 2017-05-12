/**
 * Created by wallance on 4/25/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import FlatButton from 'material-ui/FlatButton';

import * as UserActions from '../../actions/UserActions';

class CreateNewUserModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            account: null,
            password: null,
            department: null,
            username: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(UserActions, dispatch);
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleTextFieldChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleSubmit() {
        this._actions.saveUserInfo(this.state.account, this.state.password, this.state.username, this.state.department);
    }

    render() {
        const buttons = [
            <FlatButton label={'提交'} onTouchTap={this.handleSubmit.bind(this)}/>,
            <FlatButton label={'取消'} ontouchtap={this.closeModal.bind(this)}/>
        ];
        return (
            <Dialog title={'创建新用户'}
                    modal={true}
                    open={this.state.showModal}
                    actions={buttons}>
                <TextField hintText={'请输入用户名'}
                           name={'account'}
                           value={this.state.account}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <TextField hintText={'请输入密码'}
                           name={'password'}
                           value={this.state.password}
                           onChange={this.handleTextFieldChange.bind(this)}
                           type="password"/>
                <br/>
                <TextField hintText={'请输入真实姓名'}
                           name={'username'}
                           value={this.state.username}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <TextField hintText={'请输入所在部门'}
                           name={'department'}
                           value={this.state.department}
                           onChange={this.handleTextFieldChange.bind(this)}/>
            </Dialog>
        );
    }

}

export default connect()(CreateNewUserModal);
