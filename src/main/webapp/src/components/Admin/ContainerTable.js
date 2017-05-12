/**
 * Created by wallance on 4/22/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import * as AdminActions from '../../actions/AdminActions';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton';

class ContainerTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            currentContainerId: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
        this._actions.queryContainerInfo();
    }

    handleCreateNewContainer() {

    }

    handleRowSelection(selectedRows) {
        if (typeof(selectedRows) != 'undefined') {
            this.setState({
                currentContainerId: this.props.containers[selectedRows[0]].containerId
            });
        }
    }

    renderRow() {
        var tableRow = [];
        this.props.containers.forEach((container) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{container.containerId}</TableRowColumn>
                    <TableRowColumn>{container.department}</TableRowColumn>
                    <TableRowColumn>{container.containerName}</TableRowColumn>
                    <TableRowColumn>{container.lastScanTime}</TableRowColumn>
                    <TableRowColumn>
                        <FlatButton label={'删除'} onTouchTap={
                            (event) => {
                                this._actions.deleteContainerInfo(this.state.currentContainerId);
                                this._actions.queryContainerInfo();
                            }}
                        />
                    </TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    render() {
        return (
            <div>
                <FlatButton label={'新增容器'} onTouchTap={this.handleCreateNewContainer.bind(this)}/>
                <Table onRowSelection={this.handleRowSelection.bind(this)} selectable={true}>
                    <TableHeader displaySelectAll={false}>
                        <TableHeaderColumn>容器id</TableHeaderColumn>
                        <TableHeaderColumn>所属部门</TableHeaderColumn>
                        <TableHeaderColumn>容器名称</TableHeaderColumn>
                        <TableHeaderColumn>最后扫描时间</TableHeaderColumn>
                        <TableHeaderColumn>操作</TableHeaderColumn>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {this.renderRow()}
                    </TableBody>
                </Table>
            </div>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        containers: state.AdminReducer.containers
    };
};

export default connect(mapStateToProps)(ContainerTable);
