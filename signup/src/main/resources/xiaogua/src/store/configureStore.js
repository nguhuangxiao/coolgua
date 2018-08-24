import { createStore } from 'redux'
import rootReducer from 'reducers/index'
import { getLocalStorage } from 'common/js/util'

const initialState = {
  user: {
    fp: getLocalStorage("fp"),
    orgId: getLocalStorage("orgId"),
    userId: getLocalStorage("userId")
  },
  userSwitch: false,
  projectSwitch: false,
  showGalaxy: false
}
export default function configureStore() {
  const store = createStore(rootReducer, initialState,
    window.devToolsExtension ? window.devToolsExtension() : undefined
  )
  return store
}
