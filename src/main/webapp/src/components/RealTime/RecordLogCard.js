/**
 * Created by wallance on 4/16/17.
 */
import React, { Component, PropTypes } from 'react';
import FlatButton from 'material-ui/FlatButton';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import Toggle from 'material-ui/Toggle';

class RecordLogCard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            expanded: true
        }
    }

    handleExpandChange(expanded) {
        this.setState({
            expanded: expanded
        });
    };

    handleToggle(event, toggle) {
        this.setState({
            expanded: toggle
        });
    };

    handleExpand() {
        this.setState({
            expanded: true
        })
    };

    handleReduce() {
        this.setState({
            expanded: false
        });
    };

    parseTimeStamp(timestamp) {
        return new Date(parseInt(timestamp)).toLocaleString().replace(/:\d{1,2}$/, ' ');
    }

    renderRow(data) {
        var rows = [];
        var index = 0;
        if (typeof(data) === 'undefined')
            return [];
        data.forEach((item) => {
            rows.push(
                <p key={index}>
                    {item.recordId + "." + "读卡器" + item.readerId + "于" + this.parseTimeStamp(item.storeTime) + "存储" + item.rfidTag + "."}
                </p>
            );
            index++;
        });
        return rows;
    }

    render() {
        const {data} = this.props;
        return (
            <Card expanded={this.state.expanded} onExpandChange={this.handleExpandChange.bind(this)}>
                <CardHeader title="原始记录信息" actAsExpander={true} showExpandableButton={true}/>
                <CardText>
                    <Toggle toggled={this.state.expanded}
                            onToggle={this.handleToggle.bind(this)}
                            labelPosition="right"
                            label="滑至右边查看原始记录"/>
                </CardText>
                <CardTitle title="最新的原始记录" expandable={true}/>
                <CardText expandable={true}>
                    {this.renderRow(data)}
                </CardText>
                <CardActions>
                    <FlatButton label="展开" onTouchTap={this.handleExpand.bind(this)}/>
                    <FlatButton label="收起" onTouchTap={this.handleReduce.bind(this)}/>
                </CardActions>
            </Card>
        );
    }
}

RecordLogCard.propTypes = {
    data: PropTypes.array
};

export default RecordLogCard;