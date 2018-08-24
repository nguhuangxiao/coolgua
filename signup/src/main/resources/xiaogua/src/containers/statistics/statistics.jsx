import React, { Component } from 'react'

import { chartstatis1 } from './tabstatis.js'
import { effectRankChart, inviteRankChart } from './rank.js'
import { getActivityReport } from 'api/statistics.js'

import locale from 'antd/lib/date-picker/locale/zh_CN'
import { Button, Icon, message, Radio, Tabs, DatePicker, Spin} from 'antd';
import moment from 'moment'
import Sidebar from 'layouts/sidebar/sidebar'
import Breadnav from 'layouts/breadnav/breadnav'
import Galaxydialog from 'containers/galaxy_dialog/galaxy_dialog'
import { getLocalStorage } from 'common/js/util'


const orgId = getLocalStorage('orgId')
const fp = getLocalStorage('fp')
let That
let eventId
const TabPane = Tabs.TabPane;

function callback(key) {
  if(key == 1){
    var startTime = That.state.startTime == null ? "" : moment(That.state.startTime).format('YYYY-MM-DD HH:mm')
    var endTime = That.state.endTime == null ? "" : moment(That.state.endTime).format('YYYY-MM-DD HH:mm')
    var data = {type: "basic", dim: "day", startTime: startTime, endTime: endTime}
    var result1 = getActivityReport(eventId, data)
    result1.then((res)=>{
      That.setState({data: res.data})
      chartstatis1(res.data)
    })

  }else if(key == 2){
    var data = {type: "effect", dim: "day"}
    var result1 = getActivityReport(eventId, data)
    result1.then((res)=>{
      //chartstatis2(res)
      effectRankChart(res, That)
    })
  }else if(key == 3){
    var data = {type: "invite", dim: "day"}
    var result1 = getActivityReport(eventId, data)
    result1.then((res)=>{
      inviteRankChart(res, That)
    })
  }
}

class Statistics extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      size: 'day',
      data:  {},
      startTime: null,
      endTime: null,
      endOpen: false,
      currentPserson:{}
    };
  }

  onChange = (e) => {
    this.setState({size: e.target.value})
    var startTime = this.state.startTime == null ? "" : moment(this.state.startTime).format('YYYY-MM-DD HH:mm')
    var endTime = this.state.endTime == null ? "" : moment(this.state.endTime).format('YYYY-MM-DD HH:mm')
    var data = {type: "basic", dim: e.target.value, startTime: startTime, endTime: endTime}
    var result1 = getActivityReport(eventId, data)
    result1.then((res)=>{
      this.setState({data: res.data})
      chartstatis1(res.data)
    })
  }

  disabledStartDate = (startTime) => {
    const endTime = this.state.endTime;
    if (!startTime || !endTime) {
     return false;
    }
    return startTime.valueOf() > endTime.valueOf();
  }

  disabledEndDate = (endTime) => {
    const startTime = this.state.startTime;
    if (!endTime || !startTime) {
     return false;
    }
    return endTime.valueOf() <= startTime.valueOf();
  }

  onTimeChange = (field, value, dateString) => {
    this.setState({
     [field]: value,
    });
  }

  onStartChange = (value, dateString) => {
   this.onTimeChange('startTime', value, dateString);
  }

  onEndChange = (value, dateString) => {
   this.onTimeChange('endTime', value, dateString);
  }

  handleStartOpenChange = (open) => {
   if (!open) {
     this.setState({ endOpen: true });
   }
  }

  handleEndOpenChange = (open) => {
   this.setState({ endOpen: open });
  }

  searchChart(){
    var startTime = this.state.startTime == null ? "" : moment(this.state.startTime).format('YYYY-MM-DD HH:mm')
    var endTime = this.state.endTime == null ? "" : moment(this.state.endTime).format('YYYY-MM-DD HH:mm')
    var data = {type: "basic", dim: this.state.size, startTime: startTime, endTime: endTime}
    var result = getActivityReport(eventId, data)
    result.then((res)=>{
      this.setState({data: res.data})
      chartstatis1(res.data)
    })
  }

  render() {
    const { size } = this.state;
    const { startTime, endTime, endOpen, registerStartTime, registerEndTime, registerEndOpen } = this.state;
    if(!this.state.data.showcount){
      return (
        <div className="loading">
          <Spin size="large" />数据加载中...
        </div>
      )
    }
    return (
      <div className="wrapper">
        <div className="containner">
          <Breadnav/>
        </div>
        <div className="containner main-flex">
          <Sidebar/>
          <div className="side-main statistics-main" onClick={this.close.bind(this)}>
            <div className="toolbar">
              <Tabs onChange={callback} type="card">
                <TabPane tab="基础分析" key="1">
                  <div className="select-option">
                    <div className="chart-dim">
                      <em>统计维度</em>
                      <Radio.Group value={size} onChange={this.onChange} style={{ marginBottom: 16 }}>
                        <Radio.Button value="day">日</Radio.Button>
                        <Radio.Button value="month">月</Radio.Button>
                      </Radio.Group>
                    </div>
                    <div className="chart-time">
                      <em>时间区间</em>
                      <DatePicker
                        disabledDate={this.disabledStartDate}
                        showTime
                        locale={locale}
                        format="YYYY-MM-DD HH:mm"
                        value={startTime}
                        placeholder="开始时间"
                        onChange={this.onStartChange}
                        onOpenChange={this.handleStartOpenChange}
                      />
                      <span className="time-block">至</span>
                      <DatePicker
                        disabledDate={this.disabledEndDate}
                        showTime
                        locale={locale}
                        format="YYYY-MM-DD HH:mm"
                        value={endTime}
                        placeholder="结束时间"
                        onChange={this.onEndChange}
                        open={endOpen}
                        onOpenChange={this.handleEndOpenChange}
                      />
                      <button className="btn nbtn" onClick={this.searchChart.bind(this)}>查询</button>
                    </div>
                  </div>
                  <div className="data-show">
                    <table className="table-list">
                      <tbody>
                        <tr>
                          <th>浏览量</th>
                          <th>报名数</th>
                          <th>报名转化率</th>
                          <th>分享数</th>
                        </tr>
                        <tr>
                          <td>{this.state.data.showcount}</td>
                          <td>{this.state.data.regcount}</td>
                          <td>{this.state.data.convertrate}</td>
                          <td>{this.state.data.sharecount}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div className="tab-chart">
                    <div id="main1" className="chartmain" style={{height: 500}}></div>
                  </div>
                </TabPane>
                <TabPane tab="影响力排行" key="2">
                    <div className="tab-chart">
                      <div id="main2" className="rank-section" style={{minHeight: 500}}></div>
                    </div>
                </TabPane>
                <TabPane tab="邀请排行" key="3">
                  <div className="tab-chart">
                    <div id="main3" className="rank-section" style={{minHeight: 500}}></div>
                  </div>
                </TabPane>
              </Tabs>
            </div>
          </div>
        </div>
        <Galaxydialog params={this.state.currentPserson} ref="galaxydialog"/>
      </div>
    )
  }
  close() {
    this.refs.galaxydialog.close()
    this.setState({
      currentPserson: {}
    })
  }
  componentDidMount() {
    That = this
    eventId = this.props.match.params.id
    var startTime = this.state.startTime == null ? "" : moment(this.state.startTime).format('YYYY-MM-DD HH:mm')
    var endTime = this.state.endTime == null ? "" : moment(this.state.endTime).format('YYYY-MM-DD HH:mm')
    var data = {type: "basic", dim: "day", startTime: startTime, endTime: endTime}
    var result1 = getActivityReport(eventId, data)
    result1.then((res)=>{
      this.setState({
        data: res.data,
        startTime: moment(res.data.startTime),
        endTime: moment(res.data.endTime)
      },()=>{
        chartstatis1(res.data)
      })

    })
  }
}


export default Statistics
