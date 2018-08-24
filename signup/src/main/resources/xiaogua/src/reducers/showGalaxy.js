import * as actionTypes from 'constants/user'

const initialState = {}

export default function User (state = initialState, action) {
  switch (action.type) {
    case actionTypes.SHOW_GALAXY_UPDATE:
       return action.data
      break;
    default:
      return state
  }
}
