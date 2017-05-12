/**
 * Created by wallance on 4/21/17.
 */
import React, { Component } from 'react';

import { Table, TableHeader, TableHeaderColumn, TableRow, TableRowColumn, TableBody } from 'material-ui/Table';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import * as ServiceManageActions from '../../actions/ServiceManageActions';

class MemberDynamicTable extends Component {

    componentWillMount() {
        const { dispatch } = this.props;
        var actions = bindActionCreators(ServiceManageActions, dispatch);
        actions.getMemberDynamicInfo();
    }

    renderRow() {
        var tableRows = [];
        this.props.data.entity.forEach((item) => {
            tableRows.push(
                <TableRow>
                    <TableRowColumn>{item.memberName}</TableRowColumn>
                    <TableRowColumn>{item.memberType}</TableRowColumn>
                    <TableROwColumn>{item.desc}</TableROwColumn>
                    <TableRowColumn>{item.time}</TableRowColumn>
                </TableRow>
            );
        });
    }

    render() {
        return (
            <Table>
                <TableHeader>
                    <TableHeaderColumn>人员姓名</TableHeaderColumn>
                    <TableHeaderColumn>职位</TableHeaderColumn>
                    <TableHeaderColumn>描述</TableHeaderColumn>
                    <TableHeaderColumn>时间</TableHeaderColumn>
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
        data: state.ServiceManageReducer.memberDynamics
    };
};

export default connect(mapStateToProps)(MemberDynamicTable);