import React from 'react'
import { Link, hashHistory } from 'react-router-dom'
import { getUrlParam } from 'common/js/dom'

class Navbar extends React.Component {
  constructor(props, context) {
    super(props, context)
  }
  render() {
    const url = location.pathname
    const link = "/"+url.slice(0).split('/')[1]
    return (
      <div className="navbar">
        <div className="containner">
        {
          link != '/config' ?
          this.props.list.map((item, index) => (
              <label className={item.src == link ? "title active" : "title"} key={index}>
                <Link to={item.src}>{item.title}</Link>
              </label>
          ))
          :
          <label className="active title">
            <span>配置</span>
          </label>
        }
        </div>
      </div>
    )
  }
}

export default Navbar

Navbar.defaultProps = {
  list: [
    {"title": "发送中的邮件", "src": "/"},
    {"title": "邮件任务列表", "src": "/mailList"},
    {"title": "黑名单", "src": "/blackList"}
  ]
}
