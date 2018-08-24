import React from 'react'

import './loading.less'

const CONNEXTTIME = 8
let STARTTIME = 0 //规定接口请求的时间

class Loading extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      error: false //网络请求错误
    }
  }
  render() {
    return(
      <div>
        {
          !this.state.error ?
          <div className="loading">
            <img src="/common/image/Spinner.gif"/>
            <p className="desc">加载中...</p>
          </div>
          :
          <div className="error-page">
            <div className="face">:(</div>
            <div className="reason">网络错误</div>
            <div className="desc">请检查网络状态</div>
            <div className="buttons">
              <button id="ember1520" className=" btn ember-view">返回</button>
              <button id="ember1522" className="btn-primary btn ember-view"><i className="fa fa-refresh"></i>重试</button>
            </div>
          </div>
        }
      </div>
    )
  }
  componentDidMount() {
    let timer = null
    timer = setInterval(() => {
      STARTTIME++
      if(STARTTIME >= CONNEXTTIME){
        this.setState({error: true})
        clearInterval(timer)
      }
    }, 1000)
  }
  componentWillUnmount(){
    //重写组件的setState方法，直接返回空
    this.setState = (state,callback)=>{
      return;
    }
  }
}

export default Loading
