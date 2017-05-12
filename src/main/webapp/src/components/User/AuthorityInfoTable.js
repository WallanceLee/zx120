/**
 * Created by wallance on 4/25/17.
 */
import React from 'react';

import { bindActionCreators } from 'redux';

import { connect } from 'react-redux';

import { Table, TableHeader, TableHeaderColumn, TableBody, TableRow, TableRowColumn } from 'material-ui/Table';

import FlatButton from 'material-ui/FlatButton';

import * as AuthorityActions from '../../actions/AuthorityActions';

class AuthorityInfoTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currentAuthorityType: null
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(AuthorityActions, dispatch);
        this._actions.queryAuthorityInfo();
    }

    handleCreateNewAuthority() {

    }

    handleRemoveAuthority() {
        this._actions.deleteAuthorityInfo(this.state.currentAuthorityType)
        this._actions.queryAuthorityInfo();
    }

    handleRowSelection(selectedRows) {
        if (typeof(selectedRows) != 'undefined') {
            this.setState({
                currentAuthorityType: this.props.authorities[selectedRows[0]].type
            });
            console.log(this.props.authorities[selectedRows[0]].type);
        }
    }

    renderTableRow() {
        var tableRow = [];
        console.log(this.props.authorities);
        this.props.authorities.forEach((authority) => {
            tableRow.push(
                <TableRow>
                    <TableRowColumn>{authority.type}</TableRowColumn>
                    <TableRowColumn>{authority.department}</TableRowColumn>
                    <TableRowColumn>{authority.function}</TableRowColumn>
                    <TableRowColumn>{authority.param}</TableRowColumn>
                    <TableRowColumn>
                        <FlatButton label={'删除'} onTouchTap={this.handleRemoveAuthority.bind(this)}/>
                    </TableRowColumn>
                </TableRow>
            );
        });
        return tableRow;
    }

    render() {
        return(
            <div>
                <FlatButton label={'新增权限'} onTouchTap={this.handleCreateNewAuthority.bind(this)}/>
                <Table onRowSelection={this.handleRowSelection.bind(this)}>
                    <TableHeader displaySelectAll={false}>
                        <TableHeaderColumn>角色</TableHeaderColumn>
                        <TableHeaderColumn>部门</TableHeaderColumn>
                        <TableHeaderColumn>功能</TableHeaderColumn>
                        <TableHeaderColumn>参数</TableHeaderColumn>
                        <TableHeaderColumn>操作</TableHeaderColumn>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {this.renderTableRow()}
                    </TableBody>
                </Table>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        authorities: state.UserReducer.authorities
    };
};

export default connect(mapStateToProps)(AuthorityInfoTable);
