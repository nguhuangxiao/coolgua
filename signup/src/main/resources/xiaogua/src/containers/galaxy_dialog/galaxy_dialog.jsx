import React, { Component } from 'react'

import { getGalaxyDialog } from 'api/galaxy'
import { message, Icon } from 'antd'
const HBOX = 314
const WBOX = 393
class GalaxyDialog extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      data:{},
      top:0,
      left:0
    }
    this.mousedown = this.mousedown.bind(this)
    this.mousemove = this.mousemove.bind(this)
    this.mouseup = this.mouseup.bind(this)
  }
  componentWillReceiveProps(newProps){
    if(!newProps.params.openId){
      return
    }
    var openId = newProps.params.openId
    var eventId = location.pathname.split('/')[2]
    var result = getGalaxyDialog(eventId, openId)
    var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
    var clientH = scrollHeight - HBOX - 30
    var clientW = document.documentElement.clientWidth - WBOX -30
    var top = newProps.params.top
    var left = newProps.params.left
    top = Math.min(top, clientH)
    left = Math.min(left, clientW)
    result.then((res) => {
      if(res.code === 200){
        this.setState({
          data: res.map,
          top:top,
          left:left,
          relativeX:null,
          relativeY:null,
          isDragging: false
        })
      }
    })
  }
  render() {
    const data = this.state.data
    if(!data || !data.cellphone){
      return null
    }
    return(
      <div className="react-dialog-person bounceIn" id="dialog" ref="reactDialog" style={{top: this.state.top,left: this.state.left}}
        onMouseDown={this.mousedown}
        onMouseMove={this.mousemove}
        onMouseUp={this.mouseup}
        >
    		<div className="innner">
    			<div className="base">
            {
              data.photo ? <img src={data.photo} className="logo"/>
              :
              <img src={data.head_imgurl} className="logo"/>
            }

    				<div className="information">
    					<p>{data.name}</p>
    					<p>{data.position ? data.position : ""}</p>
    					<p>{data.company ? data.company : ""}</p>
    					<div className="detail">
    						<span>手机：{data.cellphone}</span>
    						<span>报名时间：{data.create_time}</span>
    					</div>
    				</div>
    			</div>
    			<div className="static">
    				<ul>
    					<li>
                <label>受邀好友：<span>{data.inviteNum}</span></label>
                <label>邀请排行：<span>{data.inviteRank}</span></label>
              </li>
    					<li className="moreli">
                <label>影响力指数：<span>{data.effect}</span></label>
                <label>影响力排行：<span>{data.effectRank}</span></label>
                <label>分享次数：<span>{data.shareNum}</span></label>
              </li>
    				</ul>
    			</div>
    			<div className="ticket">
    				门票号：{data.ticket_num}
            {
              data.inviter ? <div className="yqbtn">邀请人：{data.inviter}</div> : ""
            }
    			</div>
    		</div>
    	</div>
    )
  }
  close(){
    this.setState({
      data: {}
    })
  }
  mousedown(e){
    var that = this
    this.setState({
      relativeX:e.pageX - this.state.left,
      relativeY:e.pageY - this.state.top,
      isDragging: true
    })
    e.preventDefault();
  }
  mousemove(e){
    if(this.state.isDragging === true){
        const moveX = e.pageX - this.state.relativeX;
        const moveY = e.pageY - this.state.relativeY;
        this.refs.reactDialog.style.left = moveX + "px"
        this.refs.reactDialog.style.top = moveY + "px"
    }
  }
  mouseup(e){
    var top = this.refs.reactDialog.style.top.replace(/px/,'')
    var left = this.refs.reactDialog.style.left.replace(/px/,'')
    e.preventDefault();
    this.setState({
        isDragging:false,
        relativeX:null,
        relativeY:null,
        top:top,
        left: left
    });
  }
  componentDidMount(){

  }
}


export default GalaxyDialog
