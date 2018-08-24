import React from 'react'
import "./sidebar.less"

class Sidebar extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      templateName: "",
      mailType: "",
      recipient: "",
      submitResult: ""
    }
  }

  render() {
    const mailSendTemplate = (
      <div>
        <div className="listbox">
          <p>邮件号码</p>
          <div>
            <input type="text" className="input" name="recipient" onChange={this.recipientChange.bind(this)}/>
          </div>
        </div>
        <div className="listbox">
          <p>提交结果</p>
          <div className="checkbox-lab">
            <label><input type="radio" name="submitResult" value="1" onChange={this.submitResult.bind(this)}/>提交成功</label>
            <label><input type="radio" name="submitResult" value="0" onChange={this.submitResult.bind(this)}/>还未提交</label>
          </div>
        </div>
      </div>
    )
    const mailListTemplate = (
      <div>
        <div className="listbox">
          <p>源数据</p>
          <div>
            <select name="dsType">
              <option value="">请选择</option>
              <option value="0">本地上传</option>
              <option value="1">外部数据源</option>
            </select>
          </div>
          <div className="listbox">
            <p>创建时间</p>
            <div><input type="text" className="input Wdate time" name="startTime" id="startTime" /></div>
            <div className="pm">至</div>
            <div><input type="text" className="input Wdate time" name="endTime" id="endTime"  /></div>
          </div>
        </div>
      </div>
    )
    return (
      <div className="sidebar">
        <div className="title">筛选器</div>
        <div className="sidebar-main">
          <div className="listbox">
            <p>任务名称</p>
            <div>
              <input type="text" className="input" name="templateName" onChange={this.templateNameChange.bind(this)}/>
            </div>
          </div>
          <div className="listbox">
            <p>邮件类型</p>
            <div>
              <select name="mailType" className="select" onChange={this.mailTypeChange.bind(this)}>
                <option value="">请选择</option>
                <option value="0">标准类型</option>
                <option value="1">个性邮件</option>
              </select>
            </div>
          </div>
          {/*mailSend页面*/}
          {this.props.type == "mailSend" ? mailSendTemplate : ""}

          {/*mailMail页面*/}
          {this.props.type == "mailList" ? mailListTemplate : ""}

          <div className="siderboxbtn">
            <input value="查询" type="button" id="search" className="button" onClick={this.sendData.bind(this)}/>
            <input value="重置" className="button" type="button"/>
          </div>
        </div>
      </div>
    )
  }

  templateNameChange(event) {
    this.setState({templateName: event.target.value})
  }
  mailTypeChange(event) {
    this.setState({mailType: event.target.value})
  }
  recipientChange(event) {
    this.setState({recipient: event.target.value})
  }
  submitResult(event) {
    this.setState({submitResult: event.target.value})
  }
  sendData() {
    let data = this.state
    this.props.getSidebarData(data)
  }
}

export default Sidebar
