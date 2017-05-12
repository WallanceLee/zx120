import React, { Component } from 'react';

import { Route, Router } from 'react-router';
import { browserHistory } from 'react-router';

import { Provider } from 'react-redux';

import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import { store } from './store';

import HomePage from "./components/HomePage";
import LoginModal from "./components/User/LoginModal";
import RegisterModal from "./components/User/RegisterModal";
import AmbulanceStockTable from "./components/ServiceManage/AmbulanceStockTable";
import AmbulanceDynamicTable from "./components/ServiceManage/AmbulanceDynamicTable";
import RealTimeWidget from "./components/RealTime/RealTimeWidget";
import MemberDutyTable from "./components/ServiceManage/MemberDutyTable";
import MemberDynamicTable from "./components/ServiceManage/MemberDynamicTable";
import ResourceBindingModal from "./components/Admin/ResourceBindingModal";
import ReaderTable from "./components/Admin/ReaderTable";
import CreateNewReaderModal from "./components/Admin/CreateNewReaderModal";
import ContainerTable from "./components/Admin/ContainerTable";
import CreateNewContainerModal from "./components/Admin/CreateNewContainerModal";
import ResourceInfoTable from "./components/Admin/ResourceInfoTable";
import UserInfoTable from "./components/User/UserInfoTable";
import UpdatePasswordModal from "./components/User/UpdatePasswordModal";
import CreateNewUserModal from "./components/User/CreateNewUserModal";
import AuthorityInfoTable from "./components/User/AuthorityInfoTable";
import CreateNewAuthorityModal from "./components/User/CreateNewAuthorityModal";
import ErrLogTable from "./components/Log/ErrLogTable";
import LoginLogTable from "./components/Log/LoginLogTable";
import OperationLogTable from "./components/Log/OperationLogTable";
import AmbulanceDynamicExcelExportPage from "./components/Statistics/AmbulanceDynamicExcelExportPage";

const muiTheme = getMuiTheme({});

class App extends Component {

    render() {
        return(
            <Provider store={store}>
                <MuiThemeProvider muiTheme={muiTheme}>
                    <Router history={browserHistory}>
                        <Route path="/" component={HomePage}>
                            <Route path="/login" component={LoginModal}/>
                            <Route path="/register" component={RegisterModal}/>
                            <Route path="/serviceManage/ambulance/stock" component={AmbulanceStockTable}/>
                            <Route path="/ambulanceDynamic" component={AmbulanceDynamicTable}/>
                            <Route path="/realtime" component={RealTimeWidget}/>
                            <Route path="/serviceManage/member/duty" component={MemberDutyTable}/>
                            <Route path="/serviceManage/member/dynamic" component={MemberDynamicTable}/>
                            <Route path="/admin/rfid/tagBinding" component={ResourceBindingModal}/>
                            <Route path="/admin/rfid/readerManagement" component={ReaderTable}/>
                            <Route path="/createNewReader" component={CreateNewReaderModal}/>
                            <Route path="/admin/rfid/positionsManagement" component={ContainerTable}/>
                            <Route path="/createNewContainer" component={CreateNewContainerModal}/>
                            <Route path="/resourceInfo" component={ResourceInfoTable}/>
                            <Route path="/userInfo" component={UserInfoTable}/>
                            <Route path="/updatePassword" component={UpdatePasswordModal}/>
                            <Route path="/createNewUser" component={CreateNewUserModal}/>
                            <Route path="/authorityInfo" component={AuthorityInfoTable}/>
                            <Route path="/createNewAuthority" component={CreateNewAuthorityModal}/>
                            <Route path="/errLog" component={ErrLogTable}/>
                            <Route path="/loginLog" component={LoginLogTable}/>
                            <Route path="/operLog" component={OperationLogTable}/>
                            <Route path="/export" component={AmbulanceDynamicExcelExportPage}/>
                        </Route>
                    </Router>
                </MuiThemeProvider>
            </Provider>
        );
    }

}

export default App;
