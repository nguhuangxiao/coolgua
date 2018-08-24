import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { getSignInfo } from 'api/signlist'

class SignDetail extends React.Component {

  constructor(props, context) {
    super(props, context)
    this.state = {
      baseData:{},
      fieldData:[]
    }
  }
  render() {
    const base = this.state.baseData
    const eventId = this.props.match.params.id
    const openId = this.props.match.params.openId
    const link1 = "/signlist/"+eventId
    const link2 = "/signdetail/"+eventId+"/"+openId
    return (
      <div className="wrapper">
        <div className="containner">
          <div className="bread-nav">
            <div className="ant-breadcrumb">
              <span><span className="ant-breadcrumb-link"><Link to="/">活动列表</Link>
              <span className="ant-breadcrumb-separator">></span></span></span>
              <span><span className="ant-breadcrumb-link"><Link to={link1}>报名列表</Link>
              <span className="ant-breadcrumb-separator">></span></span></span>
              <span><span className="ant-breadcrumb-link"><Link to={link2}>报名详情页</Link>
              <span className="ant-breadcrumb-separator">></span></span></span>
            </div>
          </div>
        </div>
        <div className="signdetail-main">
          <div className="containner">
            <div className="signdetail-section">
              <div className="base-info">
                <div className="img-box">
                  {
                    base.photo ? <img src={base.photo}/> : <img src={base.head_imgurl}/>
                  }
                  <div className="person">
                    <p>姓名：{base.name}</p>
                    <p>邀请人：{base.inviter}</p>
                    <p>手机号：{base.cellphone}</p>
                    <p>报名时间：{base.create_time}</p>
                    <p>门票号：{base.ticket_num}</p>
                  </div>
                </div>
                <div className="ticket-box">
                  <div>门票</div>
                  <img src={base.ticket_num_url} className="ticket"/>
                </div>
              </div>
              <ul className="chart-ul">
                <li>受邀好友：<span>{base.inviteNum}</span></li>
                <li>邀请排行：<span>{base.inviteRank}</span></li>
                <li>影响力指数：<span>{base.effect}</span></li>
                <li>影响力排行：<span>{base.effectRank}</span></li>
                <li>分享次数：<span>{base.shareNum}</span></li>
              </ul>
              <div className="info-list">
                {
                  this.state.fieldData.map((item, index) => (

                    item.typeId == 0 ? <div className="list" key={index}>{item.chinese}{item.required ? <span className="must">*</span> : ""}：{item.data}</div>
                    : item.typeId == 1 ?
                      <div className="list" key={index}>
                        <p>{item.chinese}{item.required ? <span className="must">*</span> : ""}</p>
                        <textarea className="textarea-div" readOnly="" disabled="disabled" defaultValue={item.data}></textarea>
                      </div>
                    : item.typeId == 4 ?
                      <div className="list" key={index}>
                        <p>{item.chinese}{item.required ? <span className="must">*</span> : ""}</p>
                        <ul className="dictionary-ul">
                          <li>{item.data}</li>
                        </ul>
                      </div>
                    : item.typeId == 5 ?
                      <div className="list" key={index}>
                        <p>{item.chinese}{item.required ? <span className="must">*</span> : ""}</p>
                        <ul className="dictionary-ul">
                          {
                            item.dictionary.split('##@@##').map((ret, pn) => (
                              <li key={pn}>{ret}</li>
                            ))
                          }
                        </ul>
                      </div>
                    : ""


                  ))
                }

              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
  componentDidMount() {
    var eventId = this.props.match.params.id;
    var openId = this.props.match.params.openId;
    let result = getSignInfo(eventId,openId)
    result.then((res) => {
      var obj = {}
      for(let key in res.map.topData){
        let item = res.map.topData[key]
        obj[item.english] = item.data
      }
      this.setState({
        baseData: obj,
        fieldData: res.map.customData
      },()=>{
        console.log(this.state)
      })
    })
  }
}


export default SignDetail
