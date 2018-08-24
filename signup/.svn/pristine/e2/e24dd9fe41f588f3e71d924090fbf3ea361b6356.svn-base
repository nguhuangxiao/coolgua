import axios from 'axios'

export function dropExcellData(that, eventId, ids) {
  const url = "/event/exportReg/"+eventId
  let form = that.refs.download
  form.action = url
  var input = document.createElement("input")
  input.type = "hidden"
  input.name = "ids"
  input.value = JSON.stringify(ids)
  form.appendChild(input)
  form.submit()
  form.innerHTML = ""
}

export function getSignInfo(eventId, openId) {
  const url = "/event/register/"+eventId+"/"+openId
  return axios.post(url).then((res) => {
    return Promise.resolve(res.data)
  })
}
