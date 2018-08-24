import axios from 'axios'
import qs from 'qs'

export function getCountryData() {
  const url = "/area/country"
  return axios.post(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getProvinceData() {
  const url = "/area/province"
  return axios.post(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getCityData(city) {
  const url = "/area/city?name="+city
  return axios.get(url).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function getActivityById(eventId) {
  const url = "/event/getEventById/"+eventId
  var data = {
    id: eventId
  }
  data = qs.stringify(data)
  return axios.post(url, data, {
    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}

export function saveActivityData(data) {
  data = qs.stringify(data)
  const url = "/event/saveEvent"
  return axios.post(url, data, {
    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
  }).then((res) => {
    return Promise.resolve(res.data)
  })
}
