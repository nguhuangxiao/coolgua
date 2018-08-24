import axios from 'axios'
import { tokenParam } from 'api/config'

export function getSponsor() {
  const url = tokenParam() + "/getUserInfo.action"
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getCurOrg(userId) {
  const url = tokenParam() + "/getCurOrg/"+userId+".action"
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getProjectSSO(userId) {
  const url = tokenParam() + "/getConfigInfo/"+userId+".action"
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getOrgList(userId) {
  const url = tokenParam() + "/getUserOrgs/"+userId+".action"
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function chooseOrgId(orgId) {
  const url = tokenParam() + "/saveCurOrg/"+orgId+".action"
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function logout(orgId){
    var url = tokenParam()+"/logout.action"
    return axios.get(url).then((res) => {
      return Promise.resolve(res.data)
    })
}

export function transArray(obj) {
  var ret = []
  Object.keys(obj).forEach(function(key){
    const arr = {}
    arr.url = key
    arr.name = obj[key]
    ret.push(arr)
   })
  return ret
}
