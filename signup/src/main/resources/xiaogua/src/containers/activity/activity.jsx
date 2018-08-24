import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Icon, Spin } from 'antd'


import { getActivityData } from 'api/index'
import './activity.less'
import iconAll from 'common/image/icon.png'
import noEventPicture from 'common/image/event.jpg'
import person from 'common/image/person.png'
import time from 'common/image/time.png'
import position from 'common/image/position.png'

class Activity extends Component{
  constructor(props, context) {
    super(props, context)
    this.state = {
      list: null
    }
  }
  render() {
    if(this.state.list === null){
      return (
        <div className="loading">
          <Spin size="large" />数据加载中...
        </div>
      )
    }
    return (
      <div className="wrapper">
        <div className="containner">
          {
            !this.state.list.length ?
            <div className="noEventBox">
              <img src={noEventPicture}/>
              <Link to="/activity/add"><button className="btn createEventBtn"><Icon type="plus" />创建活动</button></Link>
            </div>
            :
            <div>
              <Link to="/activity/add"><button className="btn createEventBtn"><Icon type="plus" />新建活动</button></Link>
              <div className="card-main">
                {
                  this.state.list.map((item, index) => (
                    <div className="card-item" key={index}>
                      <img src={item.banner} />
                      <div className="info">
                        <p>{item.name}</p>
                        <p><img src={person}/><Link to={{
                          pathname: `/signlist/${item.id}`,
                          state: {item}
                        }}><span>报名人数：{item.regNum} 人</span></Link></p>
                        <p><img src={time}/><span>{item.registerStartTime} — {item.registerEndTime}</span></p>
                        {
                          item.country === '中国大陆' ||  item.country === ''?
                          <p><img src={position}/><span>{item.province}{item.city}{item.address}</span></p>
                          :
                          <p><img src={position}/><span>{item.country}</span></p>
                        }
                      </div>
                      <div className="set-btn">
                        {
                          item.status === 0 ? <div className="status event">报名未开始</div> :
                          item.status === 1 ? <div className="status eventing">报名进行中</div> :
                          <div className="status evented">报名已结束</div>
                        }

                        <div className="settle">
                          <div className="icon icon1"><Link to={{
                            pathname: `/activity/update/${item.id}`,
                            state: {item}
                          }}><span className="sp icon-sp1"></span>编辑</Link></div>
                          <div className="icon icon2"><Link to={{
                            pathname: `/galaxy/${item.id}`,
                            state: {url:location.pathname}
                          }}><span className="sp icon-sp2"></span>星系网</Link></div>
                        </div>
                        <div className="settle">
                          <div className="icon icon3"><Link to={{
                            pathname: `/entrance/${item.id}`,
                            state: {item}
                          }}><span className="sp icon-sp3"></span>入口</Link></div>
                          <div className="icon icon4"><Link to={{
                            pathname: `/statistics/${item.id}`,
                            state: {item}
                          }}><span className="sp icon-sp4"></span>统计</Link></div>
                        </div>
                      </div>
                    </div>
                  ))
                }
              </div>
            </div>
          }


        </div>
      </div>
    )
  }
  componentDidMount() {
    var body = document.getElementsByTagName("body")[0]
    body.style.overflowY = 'auto'
    const result = getActivityData()
    result.then((res) => {
      this.setState({list: res.events})
    })
  }
}


export default Activity
