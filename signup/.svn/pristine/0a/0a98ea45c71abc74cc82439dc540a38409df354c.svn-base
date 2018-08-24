import axios from 'axios'
import qs from 'qs'


export function getRegisterList(data){
  const url = "/event/registers/"+data.eventId
  var params = {
    page: data.page,
    size: data.size
  }
  params = qs.stringify(params)
  return axios.post(url, params, {
    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
