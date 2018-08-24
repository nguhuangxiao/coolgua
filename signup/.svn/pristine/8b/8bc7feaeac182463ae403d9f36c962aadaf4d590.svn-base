const app = getApp()


function handleData(data) {
  let ret = []
  for (let key in data) {
    let index = data[key].search(/\|/)
    let val = data[key].substring(0, index)
    ret.push(val)
  }
  return ret
}

function getCountryData(){
  return new Promise(function (resolve, reject) { 
    var url = app.globalData.host + "/area/country"
    wx.request({
      url: url,
      success: function (res) {
        if (res.data.success) {
          return resolve(handleData(res.data.datas))
        }
      },
      fail: function (error) {

      }
    })
  })
}

function getProvinceData(){
  return new Promise(function (resolve, reject) {
    var url = app.globalData.host + "/area/province"
    wx.request({
      url: url,
      success: function (res) {
        if (res.data.success) {
          return resolve(handleData(res.data.datas))
        }
      },
      fail: function (error) {

      }
    })
  })
}

function getCityData(province){
  console.log("马上请求城市接口")
  return new Promise(function (resolve, reject) {
    var url = app.globalData.host + "/area/city?name=" + encodeURI(province)
    wx.request({
      url: url,
      success: function (res) {
        console.log("城市数据原始状态")
        console.log(res)
        if (res.data.success) {
          return resolve(handleData(res.data.datas))
        }
      },
      fail: function (error) {
        console.log("错误信息")
        console.log(error)
      }
    })
  })
}


module.exports = {
  getCountryData,
  getProvinceData,
  getCityData
}