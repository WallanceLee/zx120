/**
 * Created by wallance on 3/27/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import FlatButton from 'material-ui/FlatButton';

import * as UserActions from '../../actions/UserActions';


class UpdatePasswordModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            account: '',
            oldPassword: '',
            newPassword: '',
            newPasswordConfirmation: ''
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(UserActions, dispatch);
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleUpdatePassword() {
        this._actions.updateUserPassword(this.state.account, this.state.oldPassword, this.state.newPassword, this.state.newPasswordConfirmation);
    }

    render() {

        const buttons = [
            <FlatButton label={'提交'} onTouchTap={this.handleUpdatePassword.bind(this)}/>,
            <FlatButton label={'取消'} onTouchTap={this.closeModal.bind(this)}/>
        ];

        return(
            <Dialog open={this.state.showModal} modal={true} actions={buttons}>
                <TextField name={'account'}
                           hintText={"用户名"}
                           value={this.state.account}
                           onChange={this.handleChange.bind(this)}/>
                <br/>
                <TextField name={'oldPassword'}
                           hintText={'请输入旧密码'}
                           value={this.state.oldPassword}
                           onChange={this.handleChange.bind(this)}
                           type="password"/>
                <br/>
                <TextField name={'newPassword'}
                           hintText={'请输入新密码'}
                           value={this.state.newPassword}
                           onChange={this.handleChange.bind(this)}
                           type="password"/>
                <br/>
                <TextField name={'newPasswordConfirmation'}
                           hintText={'请再次输入新密码'}
                           value={this.state.newPasswordConfirmation}
                           onChange={this.handleChange.bind(this)}
                           type="password"/>
            </Dialog>
        );
    }
}

export default connect()(UpdatePasswordModal);
