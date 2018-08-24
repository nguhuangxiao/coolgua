import React, { Component } from 'react'
import { Link } from 'react-router-dom'

import { galaxyChart}  from './galaxyChart.js'
import { galaxyBackground } from 'common/js/canvas'
import { getGalaxyData } from 'api/galaxy'
import { getLocalStorage } from 'common/js/util'
import Galaxydialog from 'containers/galaxy_dialog/galaxy_dialog'


class Galaxy extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      width:0,
      height:0,
      top: 66,
      left:0,
      marginLeft:0,
      marginTop:0,
      pageOption:{},
      currentPserson:{},
      lastUrl: "/",
      page: false,
      currentUrl:'/'
    };
  }
  render() {
    const { marginLeft, marginTop, width, height } = this.state;
    const eventId = this.props.match.params.id
    const link1 = "/signlist/"+eventId
    const link2 = "/galaxy/"+eventId
    return (
      <div>
        <div className="mask-header">
          <div style={{margin:'0 30px'}}>
            <Link to={this.state.lastUrl} className="return-btn">返回</Link>
          </div>
        </div>
        <div className="wrapper" style={{paddingTop:0}}>
          <canvas id="canvasBg" canvas-id="canvasBg" className="canvasBg"></canvas>
          <Galaxydialog params={this.state.currentPserson} ref="galaxydialog"/>
          <div className="page1">
            <div id="chartBox" style={{marginTop:marginTop,marginLeft:marginLeft,height:height ,width:width}} onClick={this.close.bind(this)}></div>
          </div>
        </div>
      </div>
    )
  }
  close() {
    this.refs.galaxydialog.close()
  }
  componentDidMount() {
    var body = document.getElementsByTagName("body")[0]
    body.style.overflowY = 'hidden'
    if(!this.props.location.state){
      lastUrl = '/'
    }else{
      var lastUrl = this.props.location.state.url
    }
    this.setState({
      lastUrl: lastUrl
    })
    galaxyBackground()
    var clintH = document.documentElement.clientHeight
    this.setState({
      height: clintH-66
    })

    var result = getGalaxyData(this.props.match.params.id)
    result.then((res) => {
      galaxyChart(res, this)
    })
  }
}


export default Galaxy
