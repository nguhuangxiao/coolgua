import axios from 'axios'
import qs from 'qs'

export function getActivityReport(eventId, data) {
  const url = "/event/report/"+eventId
  data = qs.stringify(data)
  return axios.post(url, data, {
    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
