/**
 * Created by wallance on 4/15/17.
 */
import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import * as RealTimeActions from '../../actions/RealTimeActions';
import FlatButton from "material-ui/FlatButton";
import EventCard from "./EventCard";
import RecordLogCard from "./RecordLogCard";

class RealTimeWidget extends Component {

    constructor(props) {
        super(props);
        this.state = {
            showEventModal: false,
            showRecordModal: false
        };
    }

    componentWillMount() {
        const { dispatch } = this.props;
        this._actions = bindActionCreators(RealTimeActions, dispatch);
    }

    refreshMap() {
        var map = this._map;
        var geo = this._geo;
        var index = 0;
        var orgResources = this.props.orgResources;
        this._actions.getRealTimeOrgResource();
        var options = {
            onSearchComplete: function (results) {
                if (local.getStatus() == BMAP_STATUS_SUCCESS) {
                    // const props = this.props;
                    // 判断状态是否正确
                    geo.getPoint(results.getPoi(0).address, function (point) {
                        if (point) {
                            map.centerAndZoom(point, 12);
                            map.enableScrollWheelZoom(true);
                            var address = new BMap.Point(point.lng, point.lat);
                            var marker = new BMap.Marker(address);
                            map.addOverlay(marker);
                            marker.setLabel(new BMap.Label(orgResources[index].org.orgName.split(":")[1] + "剩余"
                                + orgResources[index].ambulanceResource.resourceRemainNumber + "辆救护车"
                                + orgResources[index].memberResource.resourceRemainNumber + "位医护人员", {offset:new BMap.Size(20,-10)}));
                            index++;
                        }
                    }, "郑州市");
                }
            }
        };
        var local = new BMap.LocalSearch(this.props.orgResources[0].org.orgName.split("市")[0] + "市", options);

        this.props.orgResources.forEach((orgResource) => {
            local.search(orgResource.org.orgName.split(":")[1]);
        });
    }

    refreshEvents() {
        this._actions.getEventInfo();
    }

    refreshRecordLog() {
        this._actions.getRecordLog();
    }

    componentDidMount() {
        this._map = new BMap.Map('map');
        // 创建地址解析器实例
        this._geo = new BMap.Geocoder();
        this.interval1 = setInterval(() => this.refreshEvents(), 5000);
        this.interval2 = setInterval(() => this.refreshMap(), 5000);
        this.interval3 = setInterval(() => this.refreshRecordLog(), 5000);
    }

    componentWillUnmount() {
        clearInterval(this.interval1);
        clearInterval(this.interval2);
        clearInterval(this.interval3);
    }

    render() {
        return (
            <div>
                <div id="map" style={{ width: '400px', height: '400px' }}>
                </div>
                <div id="result"></div>
                <div id="event">
                    {/*<FlatButton label="刷新事件信息列表"*/}
                                {/*onTouchTap={*/}
                                    {/*(event) => {*/}
                                        {/*this._actions.getEventInfo();*/}
                                    {/*}*/}
                                {/*}/>*/}
                    <EventCard data={this.props.events}/>
                </div>
                <div id="record">
                    {/*<FlatButton label="刷新原始记录信息列表"*/}
                                {/*onTouchTap={*/}
                                    {/*(event) => {*/}
                                        {/*this._actions.getRecordLog();*/}
                                        {/*console.log(this.props.records);*/}
                                    {/*}*/}
                                {/*}/>*/}
                    <RecordLogCard data={this.props.records}/>
                </div>
                {/*<FlatButton label="刷新资源信息地图"*/}
                            {/*onTouchTap={*/}
                                {/*(event) => {*/}
                                    {/*var map = this._map;*/}
                                    {/*var geo = this._geo;*/}
                                    {/*// geo.getPoint("淮安市西大街173号", function (point) {*/}
                                    {/*//     console.log(point);*/}
                                    {/*// })*/}
                                    {/*var index = 0;*/}
                                    {/*var addresses = [];*/}
                                    {/*var orgResources = this.props.orgResources;*/}
                                    {/*this._actions.getRealTimeOrgResource();*/}
                                    {/*var options = {*/}
                                        {/*onSearchComplete: function (results) {*/}
                                            {/*if (local.getStatus() == BMAP_STATUS_SUCCESS) {*/}
                                                {/*// const props = this.props;*/}
                                                {/*// 判断状态是否正确*/}
                                                {/*geo.getPoint(results.getPoi(0).address, function (point) {*/}
                                                    {/*if (point) {*/}
                                                        {/*map.centerAndZoom(point, 12);*/}
                                                        {/*map.enableScrollWheelZoom(true);*/}
                                                        {/*var address = new BMap.Point(point.lng, point.lat);*/}
                                                        {/*var marker = new BMap.Marker(address);*/}
                                                        {/*map.addOverlay(marker);*/}
                                                        {/*marker.setLabel(new BMap.Label(orgResources[index].org.orgName.split(":")[1] + "剩余"*/}
                                                            {/*+ orgResources[index].ambulanceResource.resourceRemainNumber + "辆救护车"*/}
                                                            {/*+ orgResources[index].memberResource.resourceRemainNumber + "位医护人员", {offset:new BMap.Size(20,-10)}));*/}
                                                        {/*index++;*/}
                                                    {/*}*/}
                                                {/*}, "郑州市");*/}
                                            {/*}*/}
                                        {/*}*/}
                                    {/*};*/}
                                    {/*var local = new BMap.LocalSearch(this.props.orgResources[0].org.orgName.split("市")[0] + "市", options);*/}

                                    {/*this.props.orgResources.forEach((orgResource) => {*/}
                                        {/*local.search(orgResource.org.orgName.split(":")[1]);*/}
                                    {/*});*/}
                                {/*}}*/}
                {/*/>*/}
            </div>
        );
    }

}

const mapStateToProps = (state) => {
    return {
        orgResources: state.RealTimeReducer.orgResources,
        events: state.RealTimeReducer.events,
        records: state.RealTimeReducer.records
    }
};

export default connect(mapStateToProps)(RealTimeWidget);
