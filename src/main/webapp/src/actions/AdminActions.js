/**
 * Created by wallance on 4/20/17.
 */
import * as ActionTypes from '../constants/constants';

export function batchBindResource(resourceType, resourceIdList, tagList) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.BATCH_BIND_RESOURCE_TAG],
            req: {
                method: 'post',
                url: '/api/admin/rfid/bind.json?mode=batch&resourceType=' + resourceType + '&resourceIdList=' + resourceIdList + '&tagList=' + tagList,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.BATCH_BIND_RESOURCE_TAG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function manualBindResource(resourceType, resourceId, tag) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.MANUAL_BIND_RESOURCE_TAG],
            req: {
                method: 'post',
                url: '/api/admin/rfid/bind.json?mode=manual&resourceType=' + resourceType + '&resourceId=' + resourceId + '&tag=' + tag,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.MANUAL_BIND_RESOURCE_TAG_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function rebindResource(resourceType, resourceId, tag) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.REBIND_RESOURCE_TAG],
            req: {
                method: 'post',
                url: '/api/admin/rfid/bind.json?mode=rebind&resourceType=' + resourceType + '&resourceId=' + resourceId + '&tag=' + tag,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.REBIND_RESOURCE_TAG_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function cancelResourceBind(resourceType, resourceId) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.UNBIND_RESOURCE_TAG],
            req: {
                method: 'post',
                url: '/api/admin/rfid/bind.json?mode=cancel&resourceType=' + resourceType + '&resourceId=' + resourceId,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.UNBIND_RESOURCE_TAG_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        })
    }
}

export function queryMemberBound() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_BOUND],
            req: {
                method: 'query',
                url: '/api/admin/member.json?mode=bound',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_BOUND_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryMemberUnbound() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_UNBOUND],
            req: {
                method: 'query',
                url: '/api/admin/member.json?mode=unbound',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_UNBOUND_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryMemberTodayBound() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_TODAY_BOUND],
            req: {
                method: 'query',
                url: '/api/admin/member.json?mode=today',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_TODAY_BOUND_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        })
    }
}

export function queryOrgInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_ORG_INFO],
            req: {
                method: 'get',
                url: '/api/admin/org/list.json'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_ORG_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryContainerInfo() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_CONTAINER_INFO],
            req: {
                method: 'query',
                url: '/api/admin/container/list.json',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_CONTAINER_INFO_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function saveContainerInfo(containerName, containerType, resourceId, resourceTable, scanPolicy) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.SAVE_CONTAINER_INFO],
            req: {
                method: 'post',
                url: '/api/admin/container/create.json',
                param: {
                    containerName: containerName,
                    containerType: containerType,
                    resourceId: resourceId,
                    resourceTable: resourceTable,
                    scanPolicy: scanPolicy
                },
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.SAVE_CONTAINER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function deleteContainerInfo(containerId) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.DELETE_CONTAINER_INFO],
            req: {
                method: 'delete',
                url: '/api/admin/container/delete.json?containerId=' + containerId,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.DELETE_CONTAINER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryContainerType() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_CONTAINER_TYPE],
            req: {
                method: 'query',
                url: '/api/admin/container/listTypes.json',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_CONTAINER_TYPE_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryReaderInfo() {
    console.log("queryReaderInfo");
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_READER_INFO],
            req: {
                method: 'query',
                url: '/api/admin/reader/getList.json',
            },
            onSuccess: payload => {
                console.log("queryReaderInfoSuccess");
                dispatch({
                    type: ActionTypes.QUERY_READER_INFO_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function saveReaderInfo(activeFlag, activeTime, shutdownTime, containerId, ip, note) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.SAVE_READER_INFO],
            req: {
                method: 'post',
                url: '/api/admin/reader/create.json',
                param: {
                    activeFlag: activeFlag,
                    activeTime: activeTime,
                    shutdownTime: shutdownTime,
                    containerId: containerId,
                    ip: ip === null ? "" : ip,
                    note: note === null ? "" : note
                },
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.SAVE_READER_INFO_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function updateReaderInfo(readerId, paramList, paramValueList) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.UPDATE_READER_INFO],
            req: {
                url: '/api/admin/reader/update.json?readerId=' + readerId + '&paramList=' + paramList + '&paramValueList=' + paramValueList,
                method: 'post'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.UPDATE_READER_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function deleteReaderInfo(readerId) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.DELETE_READER_INFO],
            req: {
                method: 'del',
                url: '/api/admin/reader/delete.json?readerId=' + readerId,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.DELETE_READER_INFO_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryResourceTypes() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_RESOURCE_TYPE],
            req: {
                method: 'query',
                url: '/api/admin/view/getResourceTypeList.json',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_RESOURCE_TYPE_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        })
    }
}

export function queryResourceColumns(resourceType) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_RESOURCE_COLUMN_LIST],
            req: {
                method: 'query',
                url: '/api/admin/view/getColumnList?resourceType' + resourceType,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_RESOURCE_COLUMN_LIST_SUCCESS,
                    payload: payload
                })
            },
            onFailure: (err, res) => {

            }
        })
    }
}

export function queryColumnInfo(resourceType, columnList) {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_CUSTOM_VIEW_INFO],
            req: {
                method: 'query',
                url: '/api/admin/view/getCustomInfo?resourceType' + resourceType + '&columnList=' + columnList,
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_CUSTOM_VIEW_INFO_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryAmbulanceBound() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AMBULANCE_BOUND],
            req: {
                method: 'get',
                url: '/api/admin/ambulance.json?mode=bound'
            },
            onSuccess: payload => {
                console.log(payload);
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_BOUND_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        })
    };
}

export function queryAmbulanceUnbound() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AMBULANCE_UNBOUND],
            req: {
                method: 'get',
                url: '/api/admin/ambulance.json?mode=unbound'
            },
            onSuccess: payload => {
                console.log(payload);
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_UNBOUND_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    };
}

export function queryAmbulanceTodayBound() {
    return (dispatch) => {
        dispatch({
            type: ActionTypes.QUERY_AMBULANCE_TODAY_BOUND,
            req: {
                method: 'get',
                url: '/api/admin/member.json?mode=today'
            },
            onSuccess: payload => {
                console.log(payload);
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_TODAY_BOUND_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });

    };
}

export function queryAllAmbulance() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_ALL_AMBULANCE],
            req: {
                method: 'query',
                url: '/api/admin/container/listAllAmbulance.json',
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_ALL_AMBULANCE_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryContainerTableType() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_CONTAINER_TABLE_TYPE],
            req: {
                url: '/api/admin/container/listTableTypes.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_CONTAINER_TABLE_TYPE_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryContainerScanPolicy() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_CONTAINER_SCAN_POLICY],
            req: {
                url: '/api/admin/container/listScanPolicy.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_CONTAINER_SCAN_POLICY_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    }
}

export function queryAmbulanceAllColumn() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_AMBULANCE_ALL_COLUMN],
            req: {
                url: '/api/admin/resourceInfo/ambulance.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_AMBULANCE_ALL_COLUMN_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    };
}

export function queryMemberAllColumn() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_MEMBER_ALL_COLUMN],
            req: {
                url: '/api/admin/resourceInfo/member.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_MEMBER_ALL_COLUMN_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    };
}

export function queryOrganizationColumn() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_ORGANIZATION_ALL_COLUMN],
            req: {
                url: '/api/admin/resourceInfo/organization.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_ORGANIZATION_ALL_COLUMN_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    };
}

export function queryAllResourceTypes() {
    return (dispatch) => {
        dispatch({
            type: [ActionTypes.QUERY_ALL_RESOURCE_TYPES],
            req: {
                url: '/api/admin/resourceInfo/resourceTypes.json',
                method: 'query'
            },
            onSuccess: payload => {
                dispatch({
                    type: ActionTypes.QUERY_ALL_RESOURCE_TYPES_SUCCESS,
                    payload: payload
                });
            },
            onFailure: (err, res) => {

            }
        });
    };
}
