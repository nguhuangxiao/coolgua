import React from 'react'
import { BrowserRouter,  browserHistory, HashRouter, hashHistory } from 'react-router-dom'
import App from 'containers/index'

class RouterMap extends React.Component {
  render() {
    return (
        <BrowserRouter history={browserHistory}>
          <App/>
        </BrowserRouter>
    )
  }
}

export default RouterMap
