import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import RouterMap from 'src/router/routerMap'
import configureStore from 'store/configureStore'
// 创建 Redux 的 store 对象
const store = configureStore()
store.subscribe(() => {
    window.userParams = store.getState()
  }
);

render (
  <Provider store={store}>
    <RouterMap/>
  </Provider>,
  document.getElementById("root")
)
