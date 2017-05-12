/**
 * Created by wallance on 2/19/17.
 */
import React, { Component, PropTypes } from 'react';

import { Link } from 'react-router';

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import Menu from 'material-ui/Menu';
import MenuItem from 'material-ui/MenuItem';
import FlatButton from 'material-ui/FlatButton';
import ArrowDropRight from 'material-ui/svg-icons/navigation-arrow-drop-right';
import IconMenu from 'material-ui/IconMenu';
import IconButton from 'material-ui/IconButton';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import Subheader from 'material-ui/Subheader';

import * as AuthActions from '../actions/UserActions';

class HomePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showingMenu: false
        }
    }

    showMenu() {
        this.setState({
            showingMenu: true
        });
    }

    hideMenu() {
        this.setState({
            showingMenu: false
        });
    }

    render() {
        const { children } = this.props;
        const Login = () => {
            return (
                <div>
                    <FlatButton label="登录" containerElement={<Link to="/login"/>}/>
                    <FlatButton label="注册" containerElement={<Link to="/register"/>}/>
                </div>
            );
        };
        const LoggedIn = () => {
            return (
                <div>
                    {this.props.username + "，您已登录" + "您是" + this.props.type}
                    <IconMenu
                        iconButtonElement={
                            <IconButton><MoreVertIcon /></IconButton>
                        }
                        targetOrigin={{horizontal: 'right', vertical: 'top'}}
                        anchorOrigin={{horizontal: 'right', vertical: 'top'}}
                    >
                        <MenuItem primaryText="Refresh" />
                        <MenuItem primaryText="Help" />
                        <MenuItem primaryText="Sign out" />
                    </IconMenu>
                </div>
            );
        };
        return (
            <div>
                <Drawer open={this.state.showingMenu} docked={false} onRequestChange={this.hideMenu.bind(this)}>
                    <Subheader>120急救系统</Subheader>
                    <Menu onTouchTap={this.hideMenu}>
                        <MenuItem primaryText='首页' containerElement={<Link to="/"/>}/>
                        <MenuItem primaryText='实时监控'
                                  containerElement={<Link to="/realtime"/>}
                                  rightIcon={<ArrowDropRight/>}
                                  menuItems={[
                                      <MenuItem primaryText="车辆"/>,
                                      <MenuItem primaryText="人员"/>,
                                      <MenuItem primaryText="设备与资产"/>,
                                      <MenuItem primaryText="读卡器状态"/>
                                  ]}/>
                        <MenuItem primaryText='业务管理'
                                  rightIcon={<ArrowDropRight/>}
                                  menuItems={[
                                      <MenuItem primaryText="人员考勤"
                                                rightIcon={<ArrowDropRight/>}
                                                menuItems={[
                                                    <MenuItem primaryText="在岗情况"
                                                              containerElement={<Link to="/serviceManage/member/duty"/>}/>,
                                                    <MenuItem primaryText="人员动态"
                                                              containerElement={<Link to="/serviceManage/member/dynamic"/>}/>
                                                ]} />,
                                      <MenuItem primaryText="车辆位置"
                                                rightIcon={<ArrowDropRight/>}
                                                menuItems={[
                                                    <MenuItem primaryText="车辆盘存"
                                                              containerElement={<Link to="/serviceManage/ambulance/stock"/>}/>,
                                                    <MenuItem primaryText="车辆动态"
                                                              containerElement={<Link to="/serviceManage/ambulance/dynamic"/>}/>
                                                ]} />,
                                      <MenuItem primaryText="设备位置"
                                                rightIcon={<ArrowDropRight/>}
                                                menuItems={[
                                                    <MenuItem primaryText="设备盘存"
                                                              containerElement={<Link to="/serviceManage/device/stock"/>}/>,
                                                    <MenuItem primaryText="设备动态"
                                                              containerElement={<Link to="/serviceManage/device/dynamic"/>}/>
                                                ]} />
                                  ]}/>
                        <MenuItem
                            primaryText="系统维护"
                            rightIcon={<ArrowDropRight />}
                            menuItems={[
                                <MenuItem
                                    primaryText="RFID系统管理"
                                    rightIcon={<ArrowDropRight />}
                                    menuItems={[
                                        <MenuItem primaryText="标签绑定" containerElement={<Link to="/admin/rfid/tagBinding"/>} />,
                                        <MenuItem primaryText="位置管理" containerElement={<Link to="/admin/rfid/positionsManagement"/>}/>,
                                        <MenuItem primaryText="读卡器管理" containerElement={<Link to="/admin/rfid/readerManagement"/>} />
                                    ]}
                                />,
                                <MenuItem primaryText="医疗资源导入"
                                          rightIcon={<ArrowDropRight/>}
                                          menuItems={[
                                              <MenuItem primaryText="查看"/>
                                          ]} />,
                                <MenuItem primaryText="用户权限管理"
                                          rightIcon={<ArrowDropRight/>}
                                          menuItems={[
                                              <MenuItem primaryText="用户管理"/>,
                                              <MenuItem primaryText="权限管理"/>
                                          ]} />,
                                <MenuItem primaryText="日志"
                                          rightIcon={<ArrowDropRight/>}
                                          menuItems={[
                                              <MenuItem primaryText="操作日志"/>,
                                              <MenuItem primaryText="告警日志"/>
                                          ]} />,
                            ]}
                        />
                        <MenuItem primaryText="查询与统计"
                                  rightIcon={<ArrowDropRight/>}
                                  menuItems={[
                                      <MenuItem primaryText="定制报表"/>,
                                      <MenuItem primaryText="查看"/>
                                  ]}/>
                    </Menu>
                </Drawer>
                <AppBar title="120医疗急救系统"
                        style={{ position: 'fixed' }}
                        onLeftIconButtonTouchTap={this.showMenu.bind(this)}
                        iconElementRight={this.props.loggedIn ? <LoggedIn /> : <Login />}/>
                {children}
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    console.log(state);
    return {
        username: state.UserReducer.username,
        type: state.UserReducer.type,
        loggedIn: state.UserReducer.loggedIn
    };
};

function mapDispatchToProps(dispatch) {
    return bindActionCreators(AuthActions, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
