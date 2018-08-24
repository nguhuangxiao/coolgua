import * as actionTypes from 'constants/user'

export function switchUpdate(data) {
  return {
    type: actionTypes.PROJECT_SWITCH_UPDATE,
    data
  }
}
