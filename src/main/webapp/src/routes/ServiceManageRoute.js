/**
 * Created by wallance on 4/15/17.
 */

import AmbulanceStockTable from '../components/ServiceManage/AmbulanceStockTable'
import AmbulanceDynamicTable from "../components/ServiceManage/AmbulanceDynamicTable";

export const ServiceManageRoute = {
    path: 'serviceManage',
    component: 'div',
    indexRoute: {
        component: AmbulanceStockTable
    },
    childRoutes: [
        {
            path: '/ambulance/stock',
            component: AmbulanceStockTable
        },
        {
            path: 'ambulance/dynamic',
            component: AmbulanceDynamicTable
        }
    ]
};