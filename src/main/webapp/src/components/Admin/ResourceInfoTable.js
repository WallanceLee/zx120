/**
 * Created by wallance on 4/22/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

import * as AdminActions from '../../actions/AdminActions';

class ResourceInfoTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            selectedResourceType: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
        this._actions.queryAmbulanceAllColumn();
        // this._actions.queryMemberAllColumn();
        this._actions.queryOrganizationColumn();
        this._actions.queryAllResourceTypes();
    }

    handleResourceTypeSelected(event, index, value) {
        console.log(value);
        this.setState({
            selectedResourceType: value
        });
    }

    renderAmbulanceTableRow() {
        var tableRow = [];
        this.props.ambulancesWithAllColumn.forEach((ambulance) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{ambulance.carId}</TableRowColumn>
                    <TableRowColumn>{ambulance.orgId}</TableRowColumn>
                    <TableRowColumn>{ambulance.carBrand}</TableRowColumn>
                    <TableRowColumn>{ambulance.name}</TableRowColumn>
                    <TableRowColumn>{ambulance.flag}</TableRowColumn>
                    <TableRowColumn>{ambulance.createTime}</TableRowColumn>
                    <TableRowColumn>{ambulance.lastModifiedTime}</TableRowColumn>
                    <TableRowColumn>{ambulance.adminCode}</TableRowColumn>
                    <TableRowColumn>{ambulance.carPhone}</TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    renderMemberTableRow() {

    }

    renderOrganizationTableRow() {
        var tableRow = [];
        this.props.organizationsWithAllColumn.forEach((organization) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{organization.orgId}</TableRowColumn>
                    <TableRowColumn>{organization.name}</TableRowColumn>
                    <TableRowColumn>{organization.type}</TableRowColumn>
                    <TableRowColumn>{organization.orgCode}</TableRowColumn>
                    <TableRowColumn>{organization.adminCode}</TableRowColumn>
                    <TableRowColumn>{organization.scheduleFlag}</TableRowColumn>
                    <TableRowColumn>{organization.orgLevel}</TableRowColumn>
                    <TableRowColumn>{organization.address}</TableRowColumn>
                    <TableRowColumn>{organization.phoneNumber}</TableRowColumn>
                    <TableRowColumn>{organization.onlineFlag}</TableRowColumn>
                    <TableRowColumn>{organization.createTime}</TableRowColumn>
                    <TableRowColumn>{organization.lastModifyTime}</TableRowColumn>
                </TableRow>
            )
        });
        return tableRow;
    }

    renderResourceTable() {
        switch (this.state.selectedResourceType) {
            case 'organization':
                return (
                    <Table>
                        <TableHeader displaySelectAll={false}>
                            <TableHeaderColumn>机构id</TableHeaderColumn>
                            <TableHeaderColumn>名称</TableHeaderColumn>
                            <TableHeaderColumn>类型</TableHeaderColumn>
                            <TableHeaderColumn>所属机构代码</TableHeaderColumn>
                            <TableHeaderColumn>行政编码</TableHeaderColumn>
                            <TableHeaderColumn>是否可以调度</TableHeaderColumn>
                            <TableHeaderColumn>机构等级</TableHeaderColumn>
                            <TableHeaderColumn>地址</TableHeaderColumn>
                            <TableHeaderColumn>电话号码</TableHeaderColumn>
                            <TableHeaderColumn>是否在线</TableHeaderColumn>
                            <TableHeaderColumn>创建时间</TableHeaderColumn>
                            <TableHeaderColumn>最后修改时间</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderOrganizationTableRow()}
                        </TableBody>
                    </Table>
                );
            case 'member':
                return (
                    <Table>
                        <TableHeader displaySelectAll={false}>
                            <TableHeaderColumn>人员id</TableHeaderColumn>
                            <TableHeaderColumn>姓名</TableHeaderColumn>
                            <TableHeaderColumn>职务</TableHeaderColumn>
                            <TableHeaderColumn>机构id</TableHeaderColumn>
                            <TableHeaderColumn>所属科室</TableHeaderColumn>
                            <TableHeaderColumn>是否注销</TableHeaderColumn>
                            <TableHeaderColumn>电话号码</TableHeaderColumn>
                            <TableHeaderColumn>短信号码</TableHeaderColumn>
                            <TableHeaderColumn>创建时间</TableHeaderColumn>
                            <TableHeaderColumn>最后修改时间</TableHeaderColumn>
                            <TableHeaderColumn>行政编码</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderMemberTableRow()}
                        </TableBody>
                    </Table>
                );
            case 'ambulance':
                return (
                    <Table>
                        <TableHeader displaySelectAll={false}>
                            <TableHeaderColumn>车辆id</TableHeaderColumn>
                            <TableHeaderColumn>机构id</TableHeaderColumn>
                            <TableHeaderColumn>车牌号</TableHeaderColumn>
                            <TableHeaderColumn>名称</TableHeaderColumn>
                            <TableHeaderColumn>标志</TableHeaderColumn>
                            <TableHeaderColumn>创建时间</TableHeaderColumn>
                            <TableHeaderColumn>最后修改时间</TableHeaderColumn>
                            <TableHeaderColumn>行政编码</TableHeaderColumn>
                            <TableHeaderColumn>随车电话</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderAmbulanceTableRow()}
                        </TableBody>
                    </Table>
                );
        }
    }

    renderResourceTypeList() {
        var tableRow = [];
        this.props.resourceTypes.forEach((resourceType) => {
            tableRow.push(
                <MenuItem value={resourceType} primaryText={
                    resourceType === 'ambulance' ? '车辆'
                        : resourceType === 'member' ? '人员'
                        : resourceType === 'organization' ? '机构' : ''} />
            );
        })
        return tableRow;
    }

    render() {
        return (
            <div>
                <SelectField hintText={"请选择资源类型"}
                             value={this.state.selectedResourceType}
                             onChange={this.handleResourceTypeSelected.bind(this)}>
                    {this.renderResourceTypeList()}
                </SelectField>
                <br/>
                {this.renderResourceTable()}
            </div>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        resourceTypes: state.AdminReducer.resourceTypes,
        ambulancesWithAllColumn: state.AdminReducer.ambulancesWithAllColumn,
        membersWithAllColumn: state.AdminReducer.membersWithAllColumn,
        organizationsWithAllColumn: state.AdminReducer.organizationsWithAllColumn
    };
};

export default connect(mapStateToProps)(ResourceInfoTable);
