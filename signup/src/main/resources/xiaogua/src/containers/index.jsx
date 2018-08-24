import React from 'react'
import { Route } from 'react-router-dom'

import 'antd/dist/antd.css'
import 'common/less/resetAnt.less'
import "common/less/index.less"
import "common/less/animation.less"
import 'layouts/navbar/navbar.less'
import 'layouts/sidebar/sidebar.less'

import 'containers/activity_update/activity_update.less'
import 'containers/activity_add/activity_add.less'
import 'containers/activity_update/activity_update.less'
import 'containers/activity_update/subpage/field.less'
import 'containers/entrance/entrance.less'
import 'containers/galaxy/galaxy.less'
import 'containers/galaxy_dialog/galaxy_dialog.less'
import 'containers/signdetail/signdetail.less'
import 'containers/signlist/signlist.less'
import 'containers/statistics/statistics.less'

import Header from 'layouts/header/header'
import Navbar from 'layouts/navbar/navbar'
import Wrapper from 'layouts/wrapper/wrapper'

class App extends React.Component {
  constructor(props, context) {
    super(props, context)
  }
  render() {
    return (
      <div>
        <Header/>
        <Wrapper/>
      </div>
    )
  }
}

export default App
