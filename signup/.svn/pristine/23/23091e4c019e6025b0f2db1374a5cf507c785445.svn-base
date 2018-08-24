import React, { Component } from 'react'
import { Link, hashHistory, withRouter, Route } from 'react-router-dom'

import { Breadcrumb } from 'antd'

const breadcrumbNameMap = {
  '/': '活动列表',
  '/activity/add': '活动创建',
  '/entrance': '报名入口',
  '/activity/add': '创建活动',
  '/activity/update': '活动编辑',
  '/statistics': '统计分析',
  '/signlist': '报名列表',
  '/galaxy': '报名星系表',
  '/signdetail': '报名详情页',
}
class Breadnav extends React.Component {

  constructor(props, context) {
    super(props, context)
  }
  render() {
    //const eventId = location.pathname.split('/')[3]
    let url = location.pathname.split('/')[1];
    let eventId
    if(url === 'activity'){
      eventId = location.pathname.split('/')[3];
    }else{
      eventId = location.pathname.split('/')[2];
    }
    eventId = eventId ? eventId : ""
    const pathSnippets = location.pathname.split('/').filter(i => i);
    const extraBreadcrumbItems = pathSnippets.map((_, index) => {
      let url = `/${pathSnippets.slice(0, index + 1).join('/')}`
        if(index < 1){
          var currentUrl = location.pathname.split('/')[2]
          if(currentUrl == 'add'){
            url = '/activity/add'
          }else if(currentUrl == 'update'){
            url = '/activity/update'
          }
          return (
            <Breadcrumb.Item key={url}>
              <Link to={url+"/"+eventId}>
                {breadcrumbNameMap[url]}
              </Link>
            </Breadcrumb.Item>
          )
        }
    })

    const breadcrumbItems = [(
      <Breadcrumb.Item key="/">
        <Link to="/">活动列表</Link>
      </Breadcrumb.Item>
    )].concat(extraBreadcrumbItems)
    return (
      <div className="bread-nav">
        <Breadcrumb separator=">">
            {breadcrumbItems}
        </Breadcrumb>
      </div>
    )
  }
}


export default Breadnav
