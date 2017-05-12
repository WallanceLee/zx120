/**
 * Created by wallance on 4/22/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import FlatButton from 'material-ui/FlatButton';

import * as AdminActions from '../../actions/AdminActions';

class CreateNewContainerModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            containerName: null,
            containerType: null,
            containerTableType: null,
            resourceId: null,
            scanPolicy: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
        // 从数据库中加载容器类型信息
        this._actions.queryContainerType();
        // 从数据库中加载容器所属资源表类型
        this._actions.queryContainerTableType();
        // 加载机构信息
        this._actions.queryOrgInfo();
        // 加载车辆信息
        this._actions.queryAllAmbulance();
        // 加载扫描策略信息
        this._actions.queryContainerScanPolicy();
    }

    handleSubmit() {
        this._actions.saveContainerInfo(this.state.containerName,
                                        this.state.containerType, this.state.resourceId,
                                        this.state.containerTableType, this.state.scanPolicy);
    }

    handleTextFieldChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleContainerTypeChange(event, index, value) {
        this.setState({
            containerType: value
        });
    }

    handleContainerTableTypeChange(event, index, value) {
        console.log(value);
        this.setState({
            containerTableType: value
        });
    }

    handleResourceIdChange(event, index, value) {
        console.log(value);
        this.setState({
            resourceId: value
        });
    }

    handleScanPolicyChange(event, index, value) {
        console.log(value);
        this.setState({
            scanPolicy: value
        });
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    renderContainerTypeList() {
        var tableRow = [];
        this.props.containerTypes.forEach((containerType) => {
            tableRow.push(
                <MenuItem value={containerType.code}
                          primaryText={containerType.code + ' ' + containerType.paramValueChs}/>
            );
        })
        return tableRow;
    }

    renderContainerTableTypeList() {
        var tableRow = [];
        this.props.containerTableTypes.forEach((containerTableType) => {
            tableRow.push(
                <MenuItem value={containerTableType.tableName}
                          primaryText={containerTableType.resourceType}/>
            );
        });
        return tableRow;
    }

    renderResourceList() {
        var tableRow = [];
        if (this.state.containerTableType === 'sys_org_info') {
            this.props.organizations.forEach((organization) => {
                tableRow.push(
                    <MenuItem value={organization.orgId}
                              primaryText={organization.orgId + ' ' + organization.orgName}/>
                );
            })
        } else {
            this.props.ambulances.forEach((ambulance) => {
                tableRow.push(
                    <MenuItem value={ambulance.carId} primaryText={ambulance.carId + ' ' + ambulance.carBrand}/>
                );
            })
        }
        return tableRow;
    }

    renderScanPolicyList() {
        var tableRow = [];
        this.props.scanPolicies.forEach((scanPolicy) => {
            tableRow.push(
                <MenuItem value={scanPolicy.code} primaryText={scanPolicy.code + ' ' + scanPolicy.paramValueChs}/>
            );
        });
        return tableRow;
    }

    render() {
        const buttons = [
            <FlatButton label={'提交'} onTouchTap={this.handleSubmit.bind(this)}/>,
            <FlatButton label={'关闭'} onTouchTap={this.closeModal.bind(this)}/>
        ];
        return (
            <Dialog title={"创建新容器"}
                    modal={true}
                    open={this.state.showModal}
                    actions={buttons}
                    autoDetectWindowHeight={true}
                    autoScrollBodyContent={true}>
                <TextField name="containerName"
                           floatingLabelText={"容器名称"}
                           value={this.state.containerName}
                           onChange={this.handleTextFieldChange.bind(this)}/>
                <br/>
                <SelectField floatingLabelText={"容器类型"}
                             value={this.state.containerType}
                             onChange={this.handleContainerTypeChange.bind(this)}>
                    {this.renderContainerTypeList()}
                </SelectField>
                <br/>
                <SelectField floatingLabelText={"所属资源表"}
                             value={this.state.containerTableType}
                             onChange={this.handleContainerTableTypeChange.bind(this)}>
                    {this.renderContainerTableTypeList()}
                </SelectField>
                <br/>
                <SelectField floatingLabelText={"所属资源"}
                             value={this.state.resourceId}
                             onChange={this.handleResourceIdChange.bind(this)}>
                    {this.renderResourceList()}
                </SelectField>
                <br/>
                <SelectField floatingLabelText={"扫描策略"}
                             value={this.state.scanPolicy}
                             onChange={this.handleScanPolicyChange.bind(this)}>
                    {this.renderScanPolicyList()}
                </SelectField>
            </Dialog>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        containerTypes: state.AdminReducer.containerTypes,
        organizations: state.AdminReducer.organizations,
        ambulances: state.AdminReducer.ambulances,
        containerTableTypes: state.AdminReducer.containerTableTypes,
        scanPolicies: state.AdminReducer.scanPolicies
    };
};

export default connect(mapStateToProps)(CreateNewContainerModal);
