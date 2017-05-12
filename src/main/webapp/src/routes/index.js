/**
 * Created by wallance on 2/28/17.
 */
import React from 'react';

import { Router, Route, IndexRoute } from 'react-router';

import HomePage from "../components/HomePage";
import LoginModal from "../components/User/LoginModal";
import RegisterModal from "../components/User/RegisterModal";
import AmbulanceStockTable from '../components/ServiceManage/AmbulanceStockTable'
import AmbulanceDynamicTable from "../components/ServiceManage/AmbulanceDynamicTable";

export const rootRoute = {
    path: '/',
    component: 'div',
    indexRoute: {
        component: HomePage
    },
    childRoutes: [
        {
            childRoutes: [
                require('./ServiceManageRoute'),
                require('./AdminRoute'),
                require('./CheckRoute'),
                require('./LogRoute'),
                require('./RealTimeRoute'),
                require('./UserRoute')
            ]
        },
        {
            path: 'login',
            component: LoginModal
        },
        {
            path: 'register',
            component: RegisterModal
        },
        {
            path: 'serviceManage',
            component: 'div',
            childRoutes: [
                {
                    path: 'ambulance',
                    component: 'div',
                    childRoutes: [
                        {
                            path: 'stock',
                            component: AmbulanceStockTable
                        }
                    ]
                }
            ]
        }
    ]
};
