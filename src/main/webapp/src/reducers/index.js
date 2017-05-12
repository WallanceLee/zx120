/**
 * Created by wallance on 2/26/17.
 */
import { combineReducers } from 'redux';
import UserReducer from './UserReducer';
import ServiceManageReducer from "./ServiceManageReducer";
import RealTimeReducer from "./RealTimeReducer";
import AdminReducer from "./AdminReducer";
import LogReducer from "./LogReducer";
import StatisticsReducer from "./StatisticsReducer";

const rootReducer = combineReducers({
    UserReducer,
    ServiceManageReducer,
    RealTimeReducer,
    AdminReducer,
    LogReducer,
    StatisticsReducer
});

export default rootReducer;
