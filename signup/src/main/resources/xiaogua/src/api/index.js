import axios from 'axios'
import { interfaceToken } from 'api/config'
import { getLocalStorage } from 'common/js/util'

const orgId = getLocalStorage('orgId')
export function getActivityData() {
  const url = "/event/getEvents/"+orgId
  return axios.post(url).then((res) => {
    return Promise.resolve(res.data)
  })
}
