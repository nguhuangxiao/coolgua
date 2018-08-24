import React from 'react'
import './message.less'
import ReactCSSTransitionGroup from 'react-addons-css-transition-group'

const DELAY = 1500

class Message extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      showFlag: false
    }
  }
  render() {
    return (
      this.state.showFlag &&
      <div className="message" ref="message">
          {this.props.msg}
      </div>
    )
  }
  show() {
    this.setState({showFlag: true})
    this.refs.message.setAttribute("class", "message bounceIn")
    setTimeout(() => {
      //this.setState({showFlag: false})
    }, DELAY)
  }
}

export default Message


Message.defaultProps = {
  msg: "" ,
  time: 2500
}
