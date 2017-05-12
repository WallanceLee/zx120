/**
 * Created by wallance on 4/21/17.
 */
import React, { Component } from 'react';

import { Table, TableHeader, TableHeaderColumn, TableRow, TableRowColumn, TableBody } from 'material-ui/Table';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import * as ServiceManageActions from '../../actions/ServiceManageActions';

class MemberDutyTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        var actions = bindActionCreators(ServiceManageActions, dispatch);
        actions.getMemberDutyInfo();
    }

    renderRow() {
        var tableRows = [];
        this.props.data.entity.forEach((item) => {
            tableRows.push(
                <TableRow>
                    <TableRowColumn>{item.memberName}</TableRowColumn>
                    <TableROwColumn>{item.department}</TableROwColumn>
                    <TableRowColumn>{item.memberType}</TableRowColumn>
                    <TableRowColumn>{item.containerName}</TableRowColumn>
                    <TableRowColumn>{item.duty}</TableRowColumn>
                </TableRow>
            );
        });
    }

    render() {
        return (
            <Table>
                <TableHeader>
                    <TableHeaderColumn>人员姓名</TableHeaderColumn>
                    <TableHeaderColumn>部门</TableHeaderColumn>
                    <TableHeaderColumn>职位</TableHeaderColumn>
                    <TableHeaderColumn>位置</TableHeaderColumn>
                    <TableHeaderColumn>在岗情况</TableHeaderColumn>
                </TableHeader>
                <TableBody>
                    {this.renderRow()}
                </TableBody>
            </Table>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        data: state.ServiceManageReducer.memberDuties
    }
};

export default connect(mapStateToProps)(MemberDutyTable);
