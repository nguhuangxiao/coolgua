import React, { Component } from 'react'
import { Link } from 'react-router-dom'

import { message, Checkbox, Pagination, LocaleProvider, Spin } from 'antd';
import zhCN from 'antd/lib/locale-provider/zh_CN';
import moment from 'moment'
import Sidebar from 'layouts/sidebar/sidebar'
import Breadnav from 'layouts/breadnav/breadnav'
import { getLocalStorage } from 'common/js/util'
import { getRegisterList } from 'api/register.js'
import { dropExcellData } from 'api/signlist.js'
import xinIcon from 'common/image/xin-icon.png'
import noDataPic from 'common/image/noData.png'

const orgId = getLocalStorage('orgId')
const fp = getLocalStorage('fp')
let eventId
let That = this

function onShowSizeChange(current, pageSize) {
  That.setState({
    page: 1,
    size: pageSize,
  },()=>{
    const data = {
      eventId: That.props.match.params.id,
      page: 1,
      size: pageSize
    }
    let result = getRegisterList(data)
    result.then((res) => {
      That.setState({
        list: res.map.page.list,
        total: res.map.page.total,
        pages: res.map.page.pages,
        page: res.map.page.pageNum,
        size: res.map.page.size
      })
    })
  })
}

function onshowChange(page, pageSize){
  That.setState({
    page: page,
    size: pageSize,
  },()=>{
    const data = {
      eventId: That.props.match.params.id,
      page: page,
      size: pageSize
    }
    let result = getRegisterList(data)
    result.then((res) => {
      That.setState({
        list: res.map.page.list,
        total: res.map.page.total,
        pages: res.map.page.pages,
        page: res.map.page.pageNum,
        size: res.map.page.size,
      })
    })
  })
}

const PAGENUM = 1
const PAGESIZE = 10

const ids = []
class Register extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      list:null,
      total: 0,
      pages: 0,
      page: PAGENUM,
      size: PAGESIZE
    }
  }
  onChange(id, e) {
    if(e.target.checked){
      ids.push(id)
    }else{
      ids.splice(ids.indexOf(id), 1 )
    }
  }
  dropExcell(){
    const eventId = this.props.match.params.id
    if(!this.state.list.length){
      message.warning('没有数据可以导出');
      return
    }
    var result = dropExcellData(this, eventId, ids)
  }
  render() {
    return (
      <div className="wrapper">
        <div className="containner">
          <Breadnav/>
        </div>
        <div className="containner main-flex">
          <Sidebar/>
          {this.state.list === null ?
              <div className="loading">
                <Spin size="large" />数据加载中...
              </div>
              :
              <div className="side-main register-main">
                <div className="toolbar">
                  <button className="btn nbtn" onClick={this.dropExcell.bind(this)}>导出</button>
                  <Link to={{
                    pathname: `/galaxy/${this.props.match.params.id}`,state: {url:location.pathname}
                  }}><button className="btn nbtn xinbtn"><img src={xinIcon}/>报名星系网</button></Link>
                </div>
                <div className="register-list">
                  {
                    !this.state.list.length ?
                    <div className="list-no-data">
                      <img src={noDataPic}/>
                      <p>暂无数据</p>
                    </div>
                    :
                    <ul>
                      {
                        this.state.list.map((item, index) => (
                            <li key={index}>
                              <Checkbox onChange={this.onChange.bind(this, item.id)} ></Checkbox>
                              <Link to={{
                                pathname: `/signdetail/${this.props.match.params.id}/${item.open_id}`,
                              }}>
                              <div className="register-card">
                                <div className="inner">
                                  {
                                    !item.photo ? <img src={item.head_imgurl} className="pic1"/> :
                                    <img src={item.photo} className="pic2"/>
                                  }

                                  <div className="info">
                                    <div className="info-top">
                                      <div className="top-contact">
                                        <p>{item.name}</p>
                                        <p>{item.position}</p>
                                        <p>{item.company}</p>
                                        <div className="small-txt">
                                          <p>电话： {item.cellphone}</p>
                                          <p>报名时间： {moment(item.create_time).format('YYYY-MM-DD HH:mm')}</p>
                                        </div>
                                      </div>
                                      <div className="top-takit">
                                        <div className="takit-bg">门票号：{item.ticket_num}</div>
                                        {
                                          !item.inviter ? null :
                                          <div className="invite-user">邀请人：{item.inviter}</div>
                                        }

                                      </div>
                                    </div>
                                    <div className="info-bottom">
                                      <ol>
                                        <li>受邀好友：<span>{item.inviteNum}</span></li>
                                        <li>受邀排行：<span>{item.inviteRank}</span></li>
                                        <li>影响力指数：<span>{item.effect}</span></li>
                                        <li>影响力排行：<span>{item.effectRank}</span></li>
                                        <li>分享次数：<span>{item.shareNum}</span></li>
                                      </ol>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              </Link>
                            </li>
                        ))
                      }
                    </ul>
                  }
                </div>
                <div className="pagination">
                  {
                    this.state.list.length ?
                    <LocaleProvider locale={zhCN}>
                      <div className="flex-pagination">
                        <Pagination showSizeChanger onChange={onshowChange} current={this.state.page} onShowSizeChange={onShowSizeChange} defaultCurrent={this.state.page} total={this.state.total} />
                        {
                          this.state.total ? <span className="totle-num">总计：{this.state.total}条</span> : null
                        }
                      </div>
                    </LocaleProvider>
                    : ""
                  }
                </div>
              </div>
          }

        </div>
        <iframe name="download" style={{display:'none'}}></iframe>
        <form ref="download" method="post" target="download" id="download"></form>
      </div>
    )
  }
  componentDidMount() {
    var body = document.getElementsByTagName("body")[0]
    body.style.overflowY = 'auto'
    That = this
    const data = {
      eventId: this.props.match.params.id,
      page: this.state.page,
      size: this.state.size
    }
    let result = getRegisterList(data)
    result.then((res) => {
      this.setState({
        list: res.map.page.list,
        total: res.map.page.total,
        pages: res.map.page.pages,
        page: res.map.page.pageNum,
        size: res.map.page.size
      })
    })
  }
}


export default Register
