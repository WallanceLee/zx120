/**
 * Created by wallance on 4/25/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import FlatButton from 'material-ui/FlatButton';

import * as AuthorityActions from '../../actions/AuthorityActions';

class CreateNewAuthorityModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            type: null,
            department: null,
            _function: null,
            param: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AuthorityActions, dispatch);
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleSubmit() {
        this._actions.saveAuthorityInfo(this.state.type, this.state.department, this.state._function, this.state.param);
    }

    handleTextFieldChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {
        const buttons = [
            <FlatButton label={'提交'} onTouchTap={this.handleSubmit.bind(this)}/>,
            <FlatButton label={'取消'} onTouchTap={this.closeModal.bind(this)}/>
        ];
        return (
            <Dialog title={'创建新权限'} modal={true} open={this.state.showModal} actions={buttons}>
                <TextField hintText={'请输入权限类型'}
                           name="type"
                           value={this.state.type}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <TextField hintText={'请输入部门'}
                           name={'department'}
                           value={this.state.department}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <TextField hintText={'请输入功能'}
                           name={'_function'}
                           value={this.state._function}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <TextField hintText={'请输入功能参数'}
                           name={'param'}
                           value={this.state.param}
                           onChange={this.handleTextFieldChange.bind(this)}/>
            </Dialog>
        );
    }

}

export default connect()(CreateNewAuthorityModal);