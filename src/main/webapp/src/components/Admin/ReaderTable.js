/**
 * Created by wallance on 4/22/17.
 */
import React, { Component } from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import * as AdminActions from '../../actions/AdminActions';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn }  from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton';
import Toggle from 'material-ui/Toggle';
import MenuItem from 'material-ui/MenuItem';
import SelectField from 'material-ui/SelectField';

class ReaderTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            currentReaderId: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
        this._actions.queryReaderInfo();
        this._actions.queryContainerInfo();
    }

    handleCreateReader() {
    }

    handleRowSelection(selectedRows) {
        if (typeof(selectedRows) !== 'undefined')
            this.setState({
                currentReaderId: this.props.readers[selectedRows[0]].readerId
            });
    }

    handleContainerChange(event, index, value) {
        console.log(value);
        this._actions.updateReaderInfo(this.state.currentReaderId, "containerId", value);
        this._actions.queryReaderInfo();
    }

    handleActiveToggleChange(event, isInputChecked) {
        console.log("Toggle Checked:" + isInputChecked);
        this._actions.updateReaderInfo(this.state.currentReaderId, "activeFlag", isInputChecked);
        this._actions.queryReaderInfo();
    }

    renderContainerList() {
        var tableRow = [];
        this.props.containers.forEach((container) => {
            tableRow.push(
                <MenuItem value={container.containerId}
                          primaryText={container.containerId + ' ' + container.containerName} />
            );
        });
        return tableRow;
    }

    renderRow() {
        var tableRow = [];
        this.props.readers.forEach((reader) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{reader.readerId}</TableRowColumn>
                    <TableRowColumn>{reader.department}</TableRowColumn>
                    <TableRowColumn>
                        <SelectField onChange={this.handleContainerChange.bind(this)}
                                     autoWidth={true} value={reader.containerId}>
                            {this.renderContainerList()}
                        </SelectField>
                    </TableRowColumn>
                    <TableRowColumn>
                        <Toggle defaultToggled={!!reader.activated} onToggle={this.handleActiveToggleChange.bind(this)}/>
                    </TableRowColumn>
                    <TableRowColumn>{reader.activeTime}</TableRowColumn>
                    <TableRowColumn>{reader.shutdownTime}</TableRowColumn>
                    <TableRowColumn>
                        <FlatButton label={"删除"} onTouchTap={(event) => {
                            this._actions.deleteReaderInfo(this.state.currentReaderId);
                            this._actions.queryReaderInfo();
                        }}/>
                    </TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    render() {

        return (
            <div>
                <FlatButton label={"新增读卡器"} onTouchTap={this.handleCreateReader.bind(this)}/>
                <Table onRowSelection={this.handleRowSelection.bind(this)} selectable={true}>
                    <TableHeader displaySelectAll={false}>
                        <TableHeaderColumn>读卡器id</TableHeaderColumn>
                        <TableHeaderColumn>部门</TableHeaderColumn>
                        <TableHeaderColumn>位置</TableHeaderColumn>
                        <TableHeaderColumn>关/开</TableHeaderColumn>
                        <TableHeaderColumn>打开时间</TableHeaderColumn>
                        <TableHeaderColumn>关闭时间</TableHeaderColumn>
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
    console.log(state);
    return {
        readers: state.AdminReducer.readers,
        containers: state.AdminReducer.containers
    };
};

export default connect(mapStateToProps)(ReaderTable);
