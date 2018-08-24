import React, { Component } from 'react'
import { Link } from 'react-router-dom'

class Sidebar extends React.Component {

  constructor(props, context) {
    super(props, context)
    this.state = {
      menu: [
        {link: 'activity/update', name: '活动编辑'},
        {link: 'entrance', name: '报名入口'},
        {link: 'signlist', name: '报名列表'},
        {link: 'statistics', name: '统计分析'},
      ]
    }
  }
  render() {
    let url = location.pathname.split('/')[1];
    let eventId
    if(url === 'activity'){
      eventId = location.pathname.split('/')[3];
      url = 'activity/update'
    }else{
      eventId = location.pathname.split('/')[2];
    }
    return (
      <div className="sidebar">
        <ul>
          {
            this.state.menu.map((item, index) => (
              <li key={index} className={item.link === url ? 'menu'+index+' active' : 'menu'+index}>
                <Link to={"/"+item.link+"/"+eventId}><i className={item.link+"-icon"}></i><span>{item.name}</span></Link>
              </li>
            ))
          }
        </ul>
      </div>
    )
  }
  componentDidMount() {

  }
}


export default Sidebar
