import React, { Component } from 'react'

import { Button, Upload, Icon, message, Spin } from 'antd';
import Sidebar from 'layouts/sidebar/sidebar'
import Breadnav from 'layouts/breadnav/breadnav'
import { getLocalStorage } from 'common/js/util'
import { getActivityById } from 'api/activity_add'
import { saveWXLogo } from 'common/js/canvas.js'

const orgId = getLocalStorage('orgId')
const fp = getLocalStorage('fp')
let That
let eventId
let props = {
  name: 'file',
  action: "/event/uploadBanner/"+orgId+"/"+fp,
  data: {"eventId":eventId},
  listType: 'picture',
  className: 'upload-list-inline',
  headers: {
    authorization: 'authorization-text',
  },
  onChange(info) {
    const isJPG = info.file.type === 'image/jpeg' || info.file.type === 'image/png';
    if (!isJPG) {
      return
    }
    const isLt2M = info.file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
      return
    }
    if (info.file.status !== 'uploading') {
      That.setState({
        logo: info.file.response.map.path,
        fileList: info.fileList
      })
    }
    if (info.file.status === 'done') {
      message.success(`${info.file.name} 文件上传成功`);
    } else if (info.file.status === 'error') {
      message.error(`${info.file.name} 文件上传失败`);
    }
  },
}

function beforeUpload(file) {
  const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJPG) {
    message.error('请上传图片类型!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('图片大小不能超过2MB!');
  }
  return isJPG && isLt2M;
}

class Entrance extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
       loading: false,
       logo: "",
       acode: "",
       qrcode: "",
       fileList: []
    };
  }
  saveLocalLogo(name) {
    saveWXLogo(name)
  }
  render() {
    return (
      <div className="wrapper">
        <div className="containner">
          <Breadnav/>
        </div>
        <div className="containner main-flex">
          <Sidebar/>
          {this.state.acode === "" ?
            <div className="loading">
              <Spin size="large" />数据加载中...
            </div>
            :
            <div className="side-main entrance-main offsetLeft">
              <div className="card-trance">
                <div className="card-list">
                  <div className="box pic1" id="wxlogo1">
                    <img src={this.state.acode} className="qcode"/>
                    {this.state.logo ?
                      <img src={this.state.logo} className="logo"/>: ""
                    }
                  </div>
                  <p>
                    <button className="btn" onClick={this.saveLocalLogo.bind(this, 'wxlogo1')}>下载</button>
                  </p>
                </div>
                <div className="card-list">
                  <div className="box pic2" id="wxlogo2">
                    <img src={this.state.qrcode} className="qcode"/>
                    {this.state.logo ?
                      <img src={this.state.logo} className="logo"/>: ""
                    }
                  </div>
                  <p style={{marginTop: 50}}>
                    <button className="btn" onClick={this.saveLocalLogo.bind(this, 'wxlogo2')}>下载</button>
                  </p>
                </div>
              </div>
              <div className="previous-box">
                {this.state.logo ?
                  <div className="previous-picture">
                    <img src={this.state.logo} className="logo"/>
                  </div>: ""
                }
                <div className="info">
                  <div className="upload-logo" >
                    <Upload {...props}
                      beforeUpload={beforeUpload}
                      showUploadList={false}
                      >
                     <Button className="upload-btn">
                       <Icon type="upload" />上传Logo
                     </Button>
                    </Upload>
                  </div>
                  <span>注：logo图片尺寸为：68*68</span>
                </div>
              </div>
            </div>
          }

        </div>
        <canvas className="pic1-canvas" id="wxlogo1-canvas"></canvas>
        <canvas className="pic2-canvas" id="wxlogo2-canvas"></canvas>
      </div>
    )
  }
  componentDidMount() {
      That = this
      eventId = this.props.match.params.id
      props.data = {"eventId":eventId}

      const listItem = getActivityById(eventId)

      listItem.then((res) => {
        if(res.code === 200){
          That.setState({
            acode: res.event.acode,
            qrcode: res.event.qrcode,
            logo: res.event.logo
          })
        }
      })
  }
}


export default Entrance
