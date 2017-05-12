/**
 * Created by wallance on 4/22/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import MenuItem from 'material-ui/MenuItem';
import SelectField from 'material-ui/SelectField';
import Toggle from 'material-ui/Toggle';
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';

import * as AdminActions from '../../actions/AdminActions';

class CreateNewReaderModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            showModal: true,
            // department: "",
            // containerId: null,
            containerName: null,
            // orgName: "",
            ipAddress: "",
            activated: false,
            activeDate: null,
            activeTime: null,
            shutdownDate: null,
            shutdownTime: null,
            note: null
        }
    };

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
        this._actions.queryContainerInfo();
        this._actions.queryOrgInfo();
    }

    showModal() {
        this.setState({
            showModal: true
        });
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleSubmit() {
        // console.log(this.state);
        // console.log(this.state.activeDate.getFullYear());
        // console.log(this.state.activeDate.getMonth() + 1);
        // console.log(this.state.activeDate.getDate());
        // console.log(this.state.activeTime.getHours());
        // console.log(this.state.activeTime.getMinutes());
        // console.log(this.state.activeTime.getSeconds());
        // console.log(Date.parse(new Date(this.state.activeDate.getFullYear()
        //     + "-" + this.state.activeDate.getMonth()
        //     + "-" + this.state.activeDate.getDate()
        //     + " " + this.state.activeTime.getHours()
        //     + ":" + this.state.activeTime.getMinutes()
        //     + ":" + this.state.activeTime.getSeconds())));
        var timestamp = null;
        if (this.state.activated) {
            timestamp = Date.parse(new Date(this.state.activeDate.getFullYear()
                + "-" + this.state.activeDate.getMonth()
                + "-" + this.state.activeDate.getDate()
                + " " + this.state.activeTime.getHours()
                + ":" + this.state.activeTime.getMinutes()
                + ":" + this.state.activeTime.getSeconds()));
        } else {
            timestamp = Date.parse(new Date(this.state.shutdownDate.getFullYear()
                + "-" + this.state.shutdownDate.getMonth()
                + "-" + this.state.shutdownDate.getDate()
                + " " + this.state.shutdownTime.getHours()
                + ":" + this.state.shutdownTime.getMinutes()
                + ":" + this.state.shutdownTime.getSeconds()));
        }
        // console.log(this.state.containerName);
        this._actions.saveReaderInfo(this.state.activated,
                                        this.state.activated ? timestamp : null,
                                        this.state.activated ? null : timestamp,
                                        this.state.containerName,
                                        this.state.ipAddress,
                                        this.state.note);
        // console.log(this.state.shutdownDate.split(" ")[0] + " " + this.state.shutdownTime.split(" ")[1]);
    }

    handleActivateToggle(event, isInputChecked) {
        this.setState({
            activated: isInputChecked
        });
    }


    handleTextFieldChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleContainerChange(event, index, value) {
        this.setState({
            containerName: value
        });
    }

    handleActiveDateChange(event, date) {
        this.setState({
            activeDate: date
        });
    }

    handleShutdownDateChange(event, date) {
        this.setState({
            shutdownDate: date
        });
    }

    handleActiveTimeChange(event, time) {
        this.setState({
            activeTime: time
        });
    }

    handleShutdownTimeChange(event, time) {
        this.setState({
            shutdownTime: time
        });
    }

    renderContainerList() {
        var menuItems = [];
        this.props.containers.forEach((container) => {
            menuItems.push(
                <MenuItem value={container.containerId}
                          primaryText={container.containerId + ' ' + container.containerName} />
            );
        });
        return menuItems;
    }

    // renderOrgList() {
    //     var menuItems = [];
    //     this.props.organizations.forEach((organization) => {
    //         menuItems.push(
    //             <MenuItem value={organization.orgId}
    //                       primaryText={organization.orgId + ' ' + organization.orgName} />
    //         );
    //     });
    //     return menuItems;
    // }

    render() {
        const buttons = [
            <FlatButton label={"提交"}
                        primary={true}
                        onTouchTap={this.handleSubmit.bind(this)}/>,
            <FlatButton label={"关闭"}
                        primary={true}
                        onTouchTap={this.closeModal.bind(this)}/>
        ];
        return (
            <Dialog title={"创建新读卡器"}
                    modal={true}
                    open={this.state.showModal}
                    actions={buttons}
                    autoDetectWindowHeight={true}
                    autoScrollBodyContent={true}>
                <TextField name={"ipAddress"}
                           floatingLabelText={"IP地址"}
                           value={this.state.ipAddress}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <SelectField floatingLabelText={"位置信息"}
                             value={this.state.containerName}
                             onChange={this.handleContainerChange.bind(this)}
                             autoWidth={true}>
                    {this.renderContainerList()}
                </SelectField>
                <br/>
                <Toggle label={"关/开"} onToggle={this.handleActivateToggle.bind(this)}/>
                {this.state.activated ? (
                    <div>
                        <DatePicker hintText="开启日期"
                                    value={this.state.activeDate}
                                    onChange={this.handleActiveDateChange.bind(this)}/>
                        <TimePicker hintText="开启时间"
                                    format="24hr"
                                    value={this.state.activeTime}
                                    onChange={this.handleActiveTimeChange.bind(this)}/>
                    </div>
                ) : (
                    <div>
                        <DatePicker hintText="关闭日期"
                                    value={this.state.shutdownDate}
                                    onChange={this.handleShutdownDateChange.bind(this)}/>
                        <TimePicker hintText="关闭时间"
                                    format="24hr"
                                    value={this.state.shutdownTime}
                                    onChange={this.handleShutdownTimeChange.bind(this)}/>
                    </div>
                )}
                <TextField name="note"
                           hintText="备注"
                           multiLine={true} rows={1} rowsMax={4}
                           fullWidth={true}
                           value={this.state.note}
                           onChange={this.handleTextFieldChange.bind(this)}/>
            </Dialog>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        // organizations: state.AdminReducer.organizations,
        containers: state.AdminReducer.containers
    };
};

export default connect(mapStateToProps)(CreateNewReaderModal);
