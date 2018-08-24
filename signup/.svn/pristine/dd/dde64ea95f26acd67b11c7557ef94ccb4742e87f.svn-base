import axios from 'axios'
import qs from 'qs'


export function getGalaxyData(eventId, openId){
  const url = !openId ? "/event/galaxy/"+eventId : "/event/galaxy/"+eventId+"?openId="+openId
  var params = {
    width:document.documentElement.offsetWidth,
    height:document.documentElement.offsetHeight-66
  }
  return axios.post(url,params).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getGalaxyDialog(eventId, openId){
  const url = "/event/reg-simple/"+eventId+"/"+openId
  return axios.post(url).then((res) => {
    return Promise.resolve(res.data)
  })
}
