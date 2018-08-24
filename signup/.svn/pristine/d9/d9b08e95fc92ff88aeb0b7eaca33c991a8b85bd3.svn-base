import React, { Component } from 'react'
import './header.less'
import { getUrlParam, setCookie } from 'common/js/dom'
import { getLocalStorage, setLocalStorage} from 'common/js/util'
import { ssoLink } from 'api/config'
import { getSponsor, getCurOrg, getProjectSSO, getOrgList, chooseOrgId, transArray, logout } from 'api/header'
import { Menu, Dropdown, Icon, message } from 'antd'
import Modal from 'components/modal/modal'
import Dialog from 'components/dialog/dialog'
import Message from 'components/Message/message'
import { Link } from 'react-router-dom'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import * as userActionFormFile from 'actions/user'
import * as projectSwitchActionFormFile from 'actions/projectSwitch'
import * as userSwitchActionFormFile from 'actions/userSwitch'
import * as showGalaxyActionFormFile from 'actions/showGalaxy'

import projectImage from 'common/image/project.png'
import renwu from 'common/image/renwu.png'

class Header extends Component {
  constructor(props) {
    super(props)
    this.state = {
      userName: "",
      userId: "",
      companyName:"",
      orgId:"",
      userStaff: false,
      projectStaff: false,
      data: [],
      orgList:[],
      msg: "",
    }
  }
  render() {
    const menu = (
      <Menu>
        <Menu.Item key="1">
          <span onClick={this.logout.bind(this)}>注销</span>
        </Menu.Item>
      </Menu>
    );
    return (
      <div id="header" className="header">
        <div className="header-container">
          <div className="user">
            <Dropdown overlay={menu} trigger={['click']}>
              <a className="ant-dropdown-link" href="#">
                <img src={renwu} className="renwu"/>  {this.state.userName}
              </a>
            </Dropdown>
          </div>
        </div>
        <Modal>
          <Dialog title="切换组织"  ref="orgDialog" />
        </Modal>
      </div>
    )
  }
  logout(){
    const result = logout(this.state.orgId)
    result.then((res) => {
      if(res.success){
        message.success('注销成功');
        setTimeout(() => {
          location.href = ssoLink()
        }, 2000)
      }else{
        location.href = ssoLink()
      }
    })
  }
  componentDidMount() {
    const sponsor = getSponsor()
    sponsor.then(res => {
      if(res.success){
        this.setState({userName: res.data.userName, userId: res.data.userId}, () => {
          setLocalStorage("userId", this.state.userId)
          setLocalStorage("userName", this.state.userName)
        })
        return res.data.userId
      }else{
        location.href = ssoLink()
      }
    }).then(res => {
      var result = getCurOrg(res)
      result.then(data => {
        this.setState({companyName: data.data.companyName, orgId: data.data.id}, () => {
          setLocalStorage("orgId", this.state.orgId)
        })
      })
      return res
    }).then(res => {
      var result = getProjectSSO(res)
      result.then(data => {
        this.setState({data: transArray(data.data.urls)}, ()=>{
          this.props.userActions.update({
              fp: getLocalStorage("fp"),
              userId: this.state.userId,
              orgId: this.state.orgId,
          })
        })
      })
    })
  }
}

function mapStateToProps(state) {
  return {
     "fp": state.fp,
     "userId": state.userId,
     "orgId": state.orgId,
     "userSwitch": state.userSwitch,
     "projectSwitch": state.projectSwitch,
     "showGalaxy": state.showGalaxy
 }
}

function mapDispatchToProps(dispatch) {
  return {
    userActions: bindActionCreators(userActionFormFile, dispatch),
    userSwitchActions: bindActionCreators(userSwitchActionFormFile, dispatch),
    projectSwitchActions: bindActionCreators(projectSwitchActionFormFile, dispatch),
    showGalaxyActions: bindActionCreators(showGalaxyActionFormFile, dispatch),
  }
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Header)
