/**
 * Created by wallance on 2/26/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';

import * as UserActions from '../../actions/UserActions';

import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';

class LoginModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            username: '',
            password: ''
        };
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {
        const { dispatch } = this.props;
        const actions = bindActionCreators(UserActions, dispatch);
        const buttons = [
            <FlatButton label="取消"
                        primary={true}
                        onTouchTap={this.closeModal.bind(this)}/>,
            <FlatButton label="登录"
                        primary={true}
                        onTouchTap={(event) => {
                            actions.login(this.state.username, this.state.password);
                        }}/>
        ];
        return(
            <Dialog title="请登录" actions={buttons} modal={true} open={this.state.showModal}>
                <TextField hintText="请输入用户名"
                           floatingLabelText="用户名"
                           name="username"
                           id="username"
                           onChange={this.handleChange.bind(this)}
                           type="text"
                           value={this.state.username}/><br/>
                <TextField hintText="请输入密码" floatingLabelText="密码"
                           name="password"
                           id="password"
                           onChange={this.handleChange.bind(this)}
                           type="password"
                           value={this.state.password}/>
            </Dialog>
        );
    }
}

export default connect()(LoginModal);
