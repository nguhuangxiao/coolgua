import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import { browserHistory } from 'react-router-dom';

import { createLayout } from './layout.js'
import locale from 'antd/lib/date-picker/locale/zh_CN'
import { Input, Select, DatePicker, Upload, Button, Icon, message } from 'antd'
import moment from 'moment'
import Editor from 'wangeditor'
import Breadnav from 'layouts/breadnav/breadnav'
import Field from './subpage/field.jsx'

import { getCountryData, getProvinceData, getCityData, saveActivityData } from 'api/activity_add'
import { handleCountryData, formatDate, getLocalStorage, setLocalStorage, getChairLength } from 'common/js/util'
import colorPicture from 'common/image/color.png'

const Option = Select.Option
const { RangePicker } = DatePicker
const orgId = getLocalStorage('orgId')
const fp = getLocalStorage('fp')
let That;
const props = {
  name: 'file',
  action: "/event/uploadBanner/"+orgId+"/"+fp,
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
        banner: info.file.response.map.path,
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


class ActivityAdd extends Component{
  constructor(props, context) {
    super(props, context)
    this.state = {
      name: "",
      startTime: null,
      endTime: null,
      endOpen: false,
      registerStartTime: null,
      registerEndTime: null,
      registerEndOpen: false,
      address: "",
      banner: "",
      introduce: "",
      introduceTxt: "",
      color: "",
      isChina: false,
      country: "国家",
      province: "省份",
      city: "城市",
      allCountry:[],
      allProvince:[],
      allCity:[],
      layout: "",
      fileList: []
    }
  }
  handleChange(event){
    const name = event.target.name
    this.setState({[name]: event.target.value})
  }

  disabledStartDate = (startTime) => {
    const endTime = this.state.endTime;
    if (!startTime || !endTime) {
     return false;
    }
    return startTime.valueOf() > endTime.valueOf();
  }

  disabledEndDate = (endTime) => {
    const startTime = this.state.startTime;
    if (!endTime || !startTime) {
     return false;
    }
    return endTime.valueOf() <= startTime.valueOf();
  }

  onTimeChange = (field, value, dateString) => {
    this.setState({
     [field]: value,
    });
  }

  onStartChange = (value, dateString) => {
   this.onTimeChange('startTime', value, dateString);
  }

  onEndChange = (value, dateString) => {
   this.onTimeChange('endTime', value, dateString);
  }

  handleStartOpenChange = (open) => {
   if (!open) {
     this.setState({ endOpen: true });
   }
  }

  handleEndOpenChange = (open) => {
   this.setState({ endOpen: open });
  }

  /**报名时间**/
  registerDisabledStartDate = (registerStartTime) => {
    const registerEndTime = this.state.registerEndTime;
    if (!registerStartTime || !registerEndTime) {
     return false;
    }
    return registerStartTime.valueOf() > registerEndTime.valueOf();
  }

  registerDisabledEndDate = (registerEndTime) => {
    const endTime = this.state.endTime;
    const registerStartTime = this.state.registerStartTime;
    if (!registerEndTime || !registerStartTime) {
     return false;
    }
    if(endTime){
      if(registerEndTime.valueOf() >= endTime.valueOf()+86400){
        return true
      }
    }
    return registerEndTime.valueOf() <= registerStartTime.valueOf();
  }

  registerTimeChange = (field, value, dateString) => {
    this.setState({
     [field]: dateString,
    });
  }

  registerStartChange = (value, dateString) => {
    if(this.state.endTime){
      if(value.valueOf() >= this.state.endTime.valueOf()+86400){
        message.error("报名开始时间不能晚于活动截止时间")
        return
      }
    }
    this.onTimeChange('registerStartTime', value, dateString);
  }

  registerEndChange = (value, dateString) => {
   this.onTimeChange('registerEndTime', value, dateString);
  }

  registerStartOpenChange = (open) => {
   if (!open) {
     this.setState({ registerEndOpen: true });
   }
  }

  registerEndOpenChange = (open) => {
   this.setState({ registerEndOpen: open });
  }


  cascadeCountryChange(value){
    if(value == '中国大陆'){
      this.setState({
        isChina: true,
        country: value
      })
    }else{
      this.setState({
        province: "省份",
        city: "城市",
        isChina: false,
        country: value,
      })
    }
  }
  cascadeProvinceChange(value){
    this.setState({province: value}, ()=>{
      var result = getCityData(value)
      result.then((res) => {
        console.log(this.state.city)
        var cdata = handleCountryData(res.datas)
        this.setState({city:"城市", "allCity": cdata})
      })
    })
  }
  cascadeCityChange(value){
    this.setState({city:value})
  }

  settleColor(){

  }

  render() {
    const { startTime, endTime, endOpen, registerStartTime, registerEndTime, registerEndOpen } = this.state;
    return (
      <div className="wrapper">
        <div className="containner">
          <Breadnav/>
          <div className="activity-main activity-add-main">
            <div className="setting-item">
              <div className="item">
                <label>活动主题<span>*</span></label>
                <Input className="form-input" name="name" id="name" placeholder="活动主题字数不能超过20个汉字" onChange={this.handleChange.bind(this)}/>
              </div>
              <div className="item">
                <label>活动时间<span>*</span></label>
                <DatePicker
                  disabledDate={this.disabledStartDate}
                  showTime
                  locale={locale}
                  format="YYYY-MM-DD HH:mm"
                  value={startTime}
                  placeholder="开始时间"
                  onChange={this.onStartChange}
                  onOpenChange={this.handleStartOpenChange}
                />
                <span className="time-block">至</span>
                <DatePicker
                  disabledDate={this.disabledEndDate}
                  showTime
                  locale={locale}
                  format="YYYY-MM-DD HH:mm"
                  value={endTime}
                  placeholder="结束时间"
                  onChange={this.onEndChange}
                  open={endOpen}
                  onOpenChange={this.handleEndOpenChange}
                />
              </div>
              <div className="item">
                <label>报名时间<span>*</span></label>
                <DatePicker
                  disabledDate={this.registerDisabledStartDate}
                  showTime
                  locale={locale}
                  format="YYYY-MM-DD HH:mm"
                  value={registerStartTime}
                  placeholder="开始时间"
                  onChange={this.registerStartChange}
                  onOpenChange={this.registerStartOpenChange}
                />
                <span className="time-block">至</span>
                <DatePicker
                  disabledDate={this.registerDisabledEndDate}
                  showTime
                  locale={locale}
                  format="YYYY-MM-DD HH:mm"
                  value={registerEndTime}
                  placeholder="结束时间"
                  onChange={this.registerEndChange}
                  open={registerEndOpen}
                  onOpenChange={this.registerEndOpenChange}
                />
              </div>
              <div className="item">
                <label>活动地点<span>*</span></label>
                <div className="address-box">
                  <span>国家/地区</span>
                    <Select
                      showSearch
                      style={{ width: 169 }}
                      value={this.state.country}
                      placeholder="国家"
                      optionFilterProp="children"
                      onChange={this.cascadeCountryChange.bind(this)}
                      filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}
                      >
                      {
                        this.state.allCountry.map((item, index) => (
                          <Option value={item} key={index}>{item}</Option>
                        ))
                      }

                    </Select>
                </div>
                { !this.state.isChina ? null :
                  <div className="province-other-box">
                    <div className="address-box">
                        <span>省份</span>
                        <Select
                          showSearch
                          style={{ width: 169 }}
                          value={this.state.province}
                          placeholder="省份"
                          optionFilterProp="children"
                          onChange={this.cascadeProvinceChange.bind(this)}
                          filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}
                          >
                          {
                            this.state.allProvince.map((item, index) => (
                              <Option value={item} key={index}>{item}</Option>
                            ))
                          }
                        </Select>
                    </div>
                    <div className="address-box">
                        <span>城市</span>
                        <Select
                          showSearch
                          style={{ width: 169 }}
                          value={this.state.city}
                          placeholder="城市"
                          optionFilterProp="children"
                          onChange={this.cascadeCityChange.bind(this)}
                          filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}
                          >
                          {
                            this.state.allCity.map((item, index) => (
                              <Option value={item} key={index}>{item}</Option>
                            ))
                          }
                        </Select>
                    </div>
                  </div>
                }
                <div className="more-address" style={{width:765}}>
                  <span>详细地址</span><Input className="form-input" id="address" name="address" onChange={this.handleChange.bind(this)}/>
                </div>
              </div>
              <div className="item upitem">
                <label>Banner图<span>*</span></label>
                <div className="prevoius-pic">
                  <img src={this.state.banner}/>
                </div>
                <div className="upload-box">
                  <Upload {...props}
                    beforeUpload={beforeUpload}
                    showUploadList={false}
                    >
                   <Button className="upload-btn">
                     <Icon type="upload" />上传Banner
                   </Button>
                  </Upload>
                  <div>注：图片尺寸建议为：750*360，图片小于2M，支持jpg、png</div>
                </div>
              </div>
              <div className="item upitem">
                <label>活动介绍</label>
                <div id="editor" ref={(ref) => this.editorDom = ref} style={{width: 765,background:'#fff'}}></div>
              </div>
              <div className="item">
                <label>主色调</label>
                <input type="text" name="color" readOnly id="color" ref="color" onChange={this.handleChange.bind(this)} className="ant-input iColorPicker color-ant" />
                <a href="javascript:void(null)" id="icp_color" onClick={this.chooseColor.bind(this)}>
                  <img src={colorPicture} align="absmiddle" />
                </a>
              </div>
              <div className="item">
                <Field />
              </div>
            </div>
            <div className="save">
              <button onClick={this.saveActivity.bind(this)} className="btn nbtn">发布</button>
            </div>
          </div>
        </div>
      </div>
    )
  }
  chooseColor(){
    iColorShow('color','icp_color')
  }
  testActivity(){
    let staff = true
    if(this.state.name === ''){
      message.error("请填写活动主题")
      return false
    }else if(getChairLength(this.state.name)>40){
      message.error("活动主题的字数不能超过20个汉字")
      return false
    }
    if(this.state.startTime === null || this.state.endTime === null){
      message.error("请填写活动时间")
      return false
    }
    if(this.state.registerStartTime === null || this.state.registerEndTime === null){
      message.error("请填写报名时间")
      return false
    }
    if(this.state.address === ""){
      message.error("请填写详细地址")
      return false
    }
    if(this.state.banner === ""){
      message.error("请上传Banner图")
      return false
    }
    return staff
  }
  saveActivity(){
    let layout = createLayout()
    if(this.testActivity()){
      let layout = createLayout()
      let data = {}
      data['name'] = this.state.name
      data['eventTime'] = moment(this.state.startTime).format('YYYY-MM-DD HH:mm') + "~" +  moment(this.state.endTime).format('YYYY-MM-DD HH:mm')
      data['registerStartTime'] =  moment(this.state.registerStartTime).format('YYYY-MM-DD HH:mm')
      data['registerEndTime'] = moment(this.state.registerEndTime).format('YYYY-MM-DD HH:mm')
      data['country'] = this.state.country
      data['province'] = this.state.province == '省份' ? "" : this.state.province
      data['city'] = this.state.city == '城市' ? "" : this.state.city
      data['address'] = this.state.address
      data['banner'] = this.state.banner
      data['introduce'] = this.state.introduce
      data['color'] = this.refs.color.value
      data['orgId'] = getLocalStorage('orgId')
      data['creator'] = getLocalStorage('userName')
      data['layout'] = JSON.stringify(layout)
      console.log(data)
      let result = saveActivityData(data)
      result.then((res) => {
        if(res.code === 200){
            message.success('创建活动成功')
            setTimeout(() => {
                this.props.history.push('/');
            }, 2000)
        }
      })
    }

  }
  componentDidMount() {
    iColorPicker()
    That = this
    var editor = new Editor(ReactDOM.findDOMNode(this.editorDom));
    editor.customConfig.uploadFileName = 'file'
    editor.customConfig.uploadImgServer = "/event/uploadBanner/"+orgId+"/"+fp
    editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024
    // 使用 onchange 函数监听内容的变化，并实时更新到 state 中
    // 自定义菜单配置
    editor.customConfig.menus = [
      'source',
      'head',  // 标题
      'bold',  // 粗体
      'fontSize',  // 字号
      'fontName',  // 字体
      'italic',  // 斜体
      'underline',  // 下划线
      'strikeThrough',  // 删除线
      'foreColor',  // 文字颜色
      'backColor',  // 背景颜色
      'list',  // 列表
      'justify',  // 对齐方式
      'quote',  // 引用
      'emoticon',  // 表情
      'image',  // 插入图片
      'table',  // 表格
      'undo',  // 撤销
      'redo'  // 重复
    ]
    editor.customConfig.onchange = (html) => {
      this.setState({
        introduce: html,
        introduceTxt: editor.txt.text()
      })
    }
    editor.customConfig.uploadImgHooks = {
      success: function (xhr, editor, result) {

      },
      fail: function (xhr, editor, result) {

      },
      customInsert: function (insertImg, result, editor) {
        // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
        // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

        // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
        var url = result.map.path
        insertImg(url)
        // result 必须是一个 JSON 格式字符串！！！否则报错
      }
    }
    editor.customConfig.uploadImgShowBase64 = true
    editor.customConfig.showLinkImg = false
    editor.create();

    var result = getCountryData()
    result.then((res) => {
      var cdata = handleCountryData(res.datas)
      this.setState({allCountry: cdata})
    })

    var provincrArr = getProvinceData()
    provincrArr.then((res) => {
      var pdata = handleCountryData(res.datas)
      this.setState({allProvince: pdata})
    }).then((res) => {
      var cityrArr = getCityData(this.state.province)
      cityrArr.then((res) => {
        var ydata = handleCountryData(res.datas)
        this.setState({"allCity": ydata})
      })
    })

  }
}


export default ActivityAdd
