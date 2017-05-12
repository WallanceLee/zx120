/**
 * Created by wallance on 4/21/17.
 */
import React, { Component } from 'react';

import * as AdminActions from '../../actions/AdminActions';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import { RadioButton, RadioButtonGroup } from 'material-ui/RadioButton';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';
import TextField from 'material-ui/TextField';

class ResourceBindingModal extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: true,
            // 绑定方式
            bindMode: 'batch',
            // 资源类型
            resourceType: 0,
            // 标签起始编号
            fromTag: 0,
            // 标签结束编号
            toTag: 0,
            tagList: [],
            resourceBindings: [],
            currentResourceId: null,
            currentResourceTag: null,
            // manual
            tag: 0
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AdminActions, dispatch);
    }

    closeModal() {
        this.setState({
            showModal: false
        });
    }

    handleChangeResourceType(event, index, value) {
        this.setState({
            resourceType: value
        });
    }

    handleChangeBindMode(event, value) {
        console.log(value);
        this.setState({
            bindMode: value
        });
        if (value === 'batch' || value === 'manual') {
            if (this.state.resourceType === 0) {
                this._actions.queryAmbulanceUnbound();
            } else {
                this._actions.queryMemberUnbound();
            }
        } else {
            if (this.state.resourceType === 0) {
                this._actions.queryAmbulanceBound();
            } else {
                this._actions.queryMemberBound();
            }
        }
    }

    renderSelectField() {
        var menuItems = [];
        if (this.state.fromTag < this.state.toTag) {
            for (var i = this.state.fromTag; i <= this.state.toTag; i++) {
                menuItems.push(
                    <MenuItem value={i} primaryText={i}/>
                );
            }
        }
        return menuItems;
    }

    handleChangeTagRange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleChangeTag(event, index, value) {
        console.log(this.state.resourceBindings);
        this.setState({
            currentResourceTag: value
        });
        this.state.resourceBindings.push({
            resourceId: this.state.currentResourceId,
            resourceTag: value
        });
    }

    handleRowSelection(selectedRows) {
        console.log(selectedRows);
        if (typeof(selectedRows) !== 'undefined') {
            if (this.state.resourceType === 0)
                if (this.state.bindMode === 'batch' || this.state.bindMode === 'manual') {
                    console.log(this.props.unboundAmbulances);
                    this.setState({
                        currentResourceId: this.props.unboundAmbulances[selectedRows[0]].carId
                    });
                } else {
                    console.log(this.props.boundAmbulances);
                    this.setState({
                        currentResourceId: this.props.boundAmbulances[selectedRows[0]].carId
                    });
                }
            else {
                if (this.state.bindMode === 'batch' || this.state.bindMode === 'manual') {
                    console.log(this.props.unboundMembers);
                    this.setState({
                        currentResourceId: this.props.unboundMembers[selectedRows[0]].memberId
                    });
                } else {
                    console.log(this.props.boundMembers);
                    this.setState({
                        currentResourceId: this.props.boundMembers[selectedRows[0]].memberId
                    });
                }
            }
        }
    }

    handleManualTagChange(event) {
        console.log(event.target.value);
        this.setState({
            tag: event.target.value
        });
    }

    handleManualBind() {
        this._actions.manualBindResource(this.state.resourceType === 0 ? 'ambulance' : 'member', this.state.currentResourceId, this.state.tag);
        if (this.state.resourceType === 0) {
            this._actions.queryAmbulanceUnbound();
        } else {
            this._actions.queryMemberUnbound();
        }
    }

    handleRebind() {
        this._actions.rebindResource(this.state.resourceType === 0 ? 'ambulance' : 'member', this.state.currentResourceId, this.state.tag);
        if (this.state.resourceType === 0) {
            this._actions.queryAmbulanceBound();
        } else {
            this._actions.queryMemberBound();
        }
    }

    handleCancelBind() {
        this._actions.cancelResourceBind(this.state.resourceType === 0 ? 'ambulance' : 'member', this.state.currentResourceId);
        if (this.state.resourceType === 0) {
            this._actions.queryAmbulanceBound();
        } else {
            this._actions.queryMemberBound();
        }
    }

    renderRow() {
        var tableRows = [];
        var index = 0;
        if (this.state.bindMode === 'batch') {
            if (this.state.resourceType === 0) {
                this.props.unboundAmbulances.forEach((unboundAmbulance) => {
                    tableRows.push(
                        <TableRow name={index}>
                            <TableRowColumn>{unboundAmbulance.carId}</TableRowColumn>
                            <TableRowColumn>{unboundAmbulance.carBrand}</TableRowColumn>
                            <TableRowColumn>{unboundAmbulance.orgName}</TableRowColumn>
                            <TableRowColumn>
                                <SelectField value={typeof(this.state.resourceBindings[index]) === 'undefined' ? 0 : this.state.resourceBindings[index].resourceTag}
                                             onChange={this.handleChangeTag.bind(this)}>
                                    {this.renderSelectField()}
                                </SelectField>
                            </TableRowColumn>
                            <TableRowColumn>尚未绑定</TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            } else {
                this.props.unboundMembers.forEach((unboundMember) => {
                    tableRows.push(
                        <TableRow>
                            <TableRowColumn>{unboundMember.memberName}</TableRowColumn>
                            <TableRowColumn>{unboundMember.orgName}</TableRowColumn>
                            {/*<TableRowColumn>{unboundMember.department}</TableRowColumn>*/}
                            <TableRowColumn>
                                <SelectField value={typeof(this.state.resourceBindings[index]) === 'undefined' ? 0 : this.state.resourceBindings[index].resourceTag}
                                             onChange={this.handleChangeTag.bind(this)}>
                                    {this.renderSelectField()}
                                </SelectField>
                            </TableRowColumn>
                            <TableRowColumn>尚未绑定</TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            }
        } else if (this.state.bindMode === 'manual') {
            if (this.state.resourceType === 0) {
                this.props.unboundAmbulances.forEach((unboundAmbulance) => {
                    tableRows.push(
                        <TableRow name={index}>
                            <TableRowColumn>{unboundAmbulance.carId}</TableRowColumn>
                            <TableRowColumn>{unboundAmbulance.carBrand}</TableRowColumn>
                            <TableRowColumn>{unboundAmbulance.orgName}</TableRowColumn>
                            <TableRowColumn>
                                <TextField name="tag" value={this.state.tag}
                                           onChange={this.handleManualTagChange.bind(this)}/>
                            </TableRowColumn>
                            <TableRowColumn>尚未绑定</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="绑定"
                                            onTouchTap={this.handleManualBind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            } else {
                this.props.unboundMembers.forEach((unboundMember) => {
                    tableRows.push(
                        <TableRow name={index}>
                            <TableRowColumn>{unboundMember.memberName}</TableRowColumn>
                            <TableRowColumn>{unboundMember.orgName}</TableRowColumn>
                            {/*<TableRowColumn>{unboundMember.department}</TableRowColumn>*/}
                            <TableRowColumn>
                                <TextField name="tag" value={this.state.tag}
                                           onChange={this.handleManualTagChange.bind(this)}/>
                            </TableRowColumn>
                            <TableRowColumn>尚未绑定</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="绑定"
                                            onTouchTap={this.handleManualBind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            }
        } else if (this.state.bindMode === 'rebind') {
            if (this.state.resourceType === 0) {
                this.props.boundAmbulances.forEach((boundAmbulance) => {
                    tableRows.push(
                        <TableRow name={index} id={index}>
                            <TableRowColumn>{boundAmbulance.carId}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.carBrand}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.orgName}</TableRowColumn>
                            {/*<TableRowColumn>{boundAmbulance.rfidTag}</TableRowColumn>*/}
                            <TableRowColumn>
                                <TextField onChange={this.handleManualTagChange.bind(this)}
                                           value={this.state.tag}
                                           defaultValue={boundAmbulance.rfidTag} />
                            </TableRowColumn>
                            <TableRowColumn>{boundAmbulance.time}</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="重新绑定"
                                            onTouchTap={this.handleRebind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            } else {
                this.props.boundMembers.forEach((boundMember) => {
                    tableRows.push(
                        <TableRow name={index}>
                            <TableRowColumn>{boundMember.memberName}</TableRowColumn>
                            <TableRowColumn>{boundMember.orgName}</TableRowColumn>
                            {/*<TableRowColumn>{boundMember.department}</TableRowColumn>*/}
                            <TableRowColumn>
                                <TextField onChange={this.handleManualTagChange.bind(this)}
                                           value={this.state.tag}/>
                            </TableRowColumn>
                            <TableRowColumn>{boundMember.time}</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="重新绑定"
                                            onTouchTap={this.handleRebind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            }
        } else if (this.state.bindMode === 'cancel') {
            if (this.state.resourceType === 0) {
                this.props.boundAmbulances.forEach((boundAmbulance) => {
                    tableRows.push(
                        <TableRow name={index}>
                            <TableRowColumn>{boundAmbulance.carId}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.carBrand}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.orgName}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.rfidTag}</TableRowColumn>
                            <TableRowColumn>{boundAmbulance.time}</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="取消绑定"
                                            onTouchTap={this.handleCancelBind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            } else {
                this.props.boundMembers.forEach((boundMember) => {
                    tableRows.push(
                        <TableRow>
                            <TableRowColumn>{boundMember.memberName}</TableRowColumn>
                            <TableRowColumn>{boundMember.orgName}</TableRowColumn>
                            {/*<TableRowColumn>{boundMember.department}</TableRowColumn>*/}
                            <TableRowColumn>{boundMember.tag}</TableRowColumn>
                            <TableRowColumn>{boundMember.time}</TableRowColumn>
                            <TableRowColumn>
                                <FlatButton label="取消绑定"
                                            onTouchTap={this.handleCancelBind.bind(this)}
                                            primary={true}/>
                            </TableRowColumn>
                        </TableRow>
                    );
                    index++;
                })
            }
        }
        return tableRows;
    }

    renderRange() {
        if (this.state.bindMode === 'batch') {
            return (
                <div>
                    <TextField floatingLabelText={"请输入起始Tag"} onChange={this.handleChangeTagRange.bind(this)} value={this.state.fromTag} name="fromTag" />
                    {' - '}
                    <TextField floatingLabelText={"请输入结尾Tag"} onChange={this.handleChangeTagRange.bind(this)} value={this.state.toTag} name="toTag" />
                </div>
            );
        }
    }

    renderTable() {
        if (this.state.resourceType === 0) {
            if (this.state.bindMode === 'batch') {
                return (
                    <Table selectable={true} onRowSelection={this.handleRowSelection.bind(this)}>
                        <TableHeader adjustForCheckbox={false} displaySelectAll={false}>
                            <TableHeaderColumn>车辆id</TableHeaderColumn>
                            <TableHeaderColumn>车牌号</TableHeaderColumn>
                            <TableHeaderColumn>所属单位</TableHeaderColumn>
                            <TableHeaderColumn>RFID标签</TableHeaderColumn>
                            <TableHeaderColumn>绑定时间</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderRow()}
                        </TableBody>
                    </Table>
                );
            } else {
                //if (this.state.bindMode === 'batch') {
                    return (
                        <Table selectable={true} onRowSelection={this.handleRowSelection.bind(this)}>
                            <TableHeader displaySelectAll={false}>
                                <TableHeaderColumn>车辆id</TableHeaderColumn>
                                <TableHeaderColumn>车牌号</TableHeaderColumn>
                                <TableHeaderColumn>所属单位</TableHeaderColumn>
                                <TableHeaderColumn>RFID标签</TableHeaderColumn>
                                <TableHeaderColumn>绑定时间</TableHeaderColumn>
                                <TableHeaderColumn>操作</TableHeaderColumn>
                            </TableHeader>
                            <TableBody displayRowCheckbox={false}>
                                {this.renderRow()}
                            </TableBody>
                        </Table>
                    );
                //}
            }
        } else {
            if (this.state.bindMode === 'batch') {
                return (
                    <Table selectable={true} onRowSelection={this.handleRowSelection.bind(this)}>
                        <TableHeader displaySelectAll={false}>
                            <TableHeaderColumn>人员姓名</TableHeaderColumn>
                            <TableHeaderColumn>所属单位</TableHeaderColumn>
                            <TableHeaderColumn>RFID标签</TableHeaderColumn>
                            <TableHeaderColumn>绑定时间</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderRow()}
                        </TableBody>
                    </Table>
                );
            } else {
                return (
                    <Table selectable={true} onRowSelection={this.handleRowSelection.bind(this)}>
                        <TableHeader displaySelectAll={false}>
                            <TableHeaderColumn>人员姓名</TableHeaderColumn>
                            <TableHeaderColumn>所属单位</TableHeaderColumn>
                            <TableHeaderColumn>RFID标签</TableHeaderColumn>
                            <TableHeaderColumn>绑定时间</TableHeaderColumn>
                            <TableHeaderColumn>操作</TableHeaderColumn>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false}>
                            {this.renderRow()}
                        </TableBody>
                    </Table>
                );
            }
        }
    }

    batchModeSubmit() {
        // 将this.state.resourceBindings里的元素
        // 整理成tagList和resourceIdList形式
        var tagList = "";
        var resourceIdList = "";
        this.state.resourceBindings.forEach((resourceBinding) => {
            tagList = tagList.concat(resourceBinding.resourceTag + ",");
            resourceIdList = resourceIdList.concat(resourceBinding.resourceId + ",");
        });
        // 去除结尾的逗号
        tagList = tagList.substring(0, tagList.length - 1);
        resourceIdList = resourceIdList.substring(0, resourceIdList.length - 1);
        console.log(tagList);
        console.log(resourceIdList);
        this._actions.batchBindResource(this.state.resourceType === 0 ? 'ambulance' : 'member', resourceIdList, tagList);
        this._actions.queryAmbulanceUnbound();
    }

    render() {
        const actions = [
            <FlatButton label="关闭"
                        onTouchTap={this.closeModal.bind(this)}/>,
            <FlatButton label="提交"
                        onTouchTap={
                            (event) => {
                                switch (this.state.bindMode) {
                                    case 'batch':
                                        // 批量绑定
                                        this.batchModeSubmit();
                                    case 'manual':
                                        // 手动绑定
                                        this.closeModal.bind(this);
                                        break;
                                    case 'rebind':
                                        // 重新绑定
                                        this.closeModal.bind(this);
                                        break;
                                    case 'cancel':
                                        // 删除绑定
                                        this.closeModal.bind(this);
                                        break;
                                }
                            }}
            />
        ];
        return (
            <Dialog actions={actions}
                    modal={true}
                    open={this.state.showModal}
                    contentStyle={{ width: '100%', maxWidth: 'none' }}
                    autoScrollBodyContent={true}>
                <SelectField floatingLabelText="资源类型" value={this.state.resourceType} onChange={this.handleChangeResourceType.bind(this)}>
                    <MenuItem value={0} primaryText="车辆"/>
                    <MenuItem value={1} primaryText="人员"/>
                </SelectField>
                <RadioButtonGroup name="bindMode" defaultSelected="batch" onChange={this.handleChangeBindMode.bind(this)}>
                    <RadioButton value="batch" label="批量绑定" />
                    <RadioButton value="manual" label="手动绑定"/>
                    <RadioButton value="rebind" label="重新绑定"/>
                    <RadioButton value="cancel" label="取消绑定"/>
                </RadioButtonGroup>
                <br/>
                {this.renderRange()}
                <br/>
                {this.renderTable()}
            </Dialog>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        unboundMembers: state.AdminReducer.unboundMembers,
        unboundAmbulances: state.AdminReducer.unboundAmbulances,
        boundMembers: state.AdminReducer.boundMembers,
        boundAmbulances: state.AdminReducer.boundAmbulances
    };
};

export default connect(mapStateToProps)(ResourceBindingModal);
