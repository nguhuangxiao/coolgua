import React, {Component, PropTypes } from 'react'
import './dialog.less'
import ReactCSSTransitionGroup from 'react-addons-css-transition-group'

class Dialog extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.closeBtn = this.closeBtn.bind(this)
    this.state = {
      status: false
    }
  }

  render() {
    const {title, content} = this.props;
    const shadow = (
      this.props.modal &&
      <div className="dialog-shadow"></div>
    )
    return (
      this.state.status &&
      <div>
        <div className="react-dialog" ref="reactDialog">
          <div className="title">
            {title}<span className="icon-close close1" onClick={this.closeBtn}></span>
          </div>
          <div className="content">{content}</div>
        </div>
        { shadow }
      </div>
    )
  }
  show() {
    this.setState({status: true})
    this.refs.reactDialog.setAttribute("class", "react-dialog bounceIn")
  }
  hide() {
    this.setState({status: false})
    this.refs.reactDialog.setAttribute("class", "react-dialog")
  }
  closeBtn() {
    this.hide()
  }
  componentDidMount() {

  }
}

export default Dialog
