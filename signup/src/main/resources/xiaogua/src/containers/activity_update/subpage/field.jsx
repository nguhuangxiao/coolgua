import React, { Component } from 'react'

import { sortBaseDrag, sortAddDrag } from './field.js'
import { updateLayout } from './update.js'

class Field extends Component{
  constructor(props, context) {
    super(props, context)
    this.state = {
      layout: [],
      baseFiled: []
    }
  }
  componentWillReceiveProps(newProps){
    if(!newProps.layout){
      return
    }
    this.setState({layout: JSON.parse(newProps.layout)})
    updateLayout(newProps.layout)
  }
  render() {
    return (
      <div className="field-main" style={{marginBottom: 20}}>
          <label>填写报名信息</label>
          <div className="setting-content">
            <div className="field-area">
              <div id="base-box">
                <p>常用信息</p>
                <ul className="field-ul" id="sortable1">
                  <li typeid="0" fieldid="2" custom="0"><span>单位</span></li>
                  <li typeid="0" fieldid="3" custom="0"><span>部门</span></li>
                  <li typeid="0" fieldid="4" custom="0"><span>职务</span></li>
                  <li typeid="0" fieldid="5" custom="0"><span>地址</span></li>
                  <li typeid="0" fieldid="6" custom="0"><span>邮箱</span></li>
                  <li typeid="0" fieldid="7" custom="0"><span>电话</span></li>
                  <li typeid="0" fieldid="8" custom="0"><span>性别</span></li>
                  <li typeid="0" fieldid="9" custom="0"><span>国家/省份/城市</span></li>
                  <li typeid="2" fieldid="12" custom="0"><span>照片</span></li>
                </ul>
              </div>
              <div id="add-box">
                <p>自定义项</p>
                <ul className="field-ul" id="sortable3">
                  <li typeid="0" custom="1"><span>单行文本</span></li>
                  <li typeid="1" custom="1"><span>多行文本</span></li>
                  <li typeid="4" custom="1"><span>单选项</span></li>
                  <li typeid="5" custom="1"><span>多选项</span></li>
                </ul>
              </div>
            </div>
            <div className="sort-area">
              <div className="base-show">
                <h3>基本信息-必填</h3>
                <ul className="drag-ol" id="sortable2">
                  <li typeid="0" fieldid="0" custom="0">
                    <label>手机</label>
                    <input type="text" readOnly/>
                  </li>
                  <li typeid="0" fieldid="1" custom="0">
                    <label>姓名</label>
                    <input type="text" readOnly/>
                  </li>
                </ul>
              </div>
              <div className="add-show new-show">
                <h3>更多信息</h3>
                <ul className="drag-ol add-ul-h" id="sortable4">

                </ul>
              </div>
            </div>
          </div>
      </div>
    )
  }
  componentDidMount(){

    sortBaseDrag()
    sortAddDrag()
  }
}

export default Field
