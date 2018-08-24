//sign.js
const app = getApp()
var util = require('../../utils/field.js');
var cascade = require('../../utils/country.js');
let TIME = 60
Page({
  data: {
    mobile: '',
    testMobile: false,
    code: '',
    testCode: false,
    isSending: false,
    isSendingTxt: '获取验证码',
    height: '0px',
    userLogin: false,
    canClick: true,
    canSubmit: true,
    openId: '',
    inviteOpenid: '',
    fields:[],
    requireField:{},
    photo: "",
    selectChina: false,
    country: [],
    selectCountry: "请选择",
    province: [],
    selectProvince: "请选择",
    city: [],
    selectCity: "请选择",
    sex: ['男','女'],
    selectSex: "请选择",
    pindex:0,
    color:'',
    istag:false
  },
  getHeight() {
    let h = wx.getSystemInfoSync().windowHeight
    let m = 120 + 50
    this.setData({
      height: (h - m) + "px"
    })
  },
  sexPickerChange: function (option) {
    var item = this.data.sex[option.detail.value]
    this.setData({ selectSex: item })
  },
  countryPickerChange: function(option){
    var item = this.data.country[option.detail.value]
    this.setData({ selectCountry: item })
    if (option.detail.value == '0'){
      this.setData({ selectChina: true })
    }else{
      this.setData({ 
        selectChina: false,
        selectProvince: "请选择",
        selectCity: "请选择"
      })
    }
  },
  provincePickerChange: function (option) {
    var item = this.data.province[option.detail.value]
    this.setData({ selectProvince: item })
    console.log("开始请求接口，获取城市数据")
    var result = cascade.getCityData(item)
    result.then((res) => {
      console.log("城市数据已获取")
      console.log(res)
      console.log("**************************")
      this.setData({ city: res, selectCity: "请选择"})
    })
  },
  cityPickerChange: function (option) {
    var item = this.data.city[option.detail.value]
    this.setData({ selectCity: item })
  },
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          files: res.tempFilePaths
        });
        var eventId = wx.getStorageSync('eventId')
        var tempFilePaths = res.tempFilePaths
        wx.uploadFile({
          url: app.globalData.host + '/event/uploadFile/' + eventId, //仅为示例，非真实的接口地址
          filePath: tempFilePaths[0],
          name: 'file',
          formData: {
            'eventId': eventId
          },
          success: function (res) {
            var data = res.data
            var file = JSON.parse(data)
            that.setData({ photo: file.map.path })
          }
        })
      }
    })
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  //校验手机号
  testMobileLength(e) {
    if (e.detail.value.length >= 11){
      this.setData({
        testMobile: true,
        mobile: e.detail.value
      })
    }else{
      this.setData({
        testMobile: false
      })
    }
  },
  //获取验证码
  getValidCode(e) {
    console.log(e)
    var that = this;
    var num = this.data.mobile;
    var staff = /^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$/.test(num)
    if(!staff){
      wx.showModal({
        title: '提示',
        content: '请输入正确的11位手机号',
        showCancel: false,
        confirmText: '确定',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定');
          }
        }
      })
    }else{
      if (that.data.canClick){
        this.setData({
          canClick: false
        })
        //请求接口，获取验证码
        var url = app.globalData.host + '/register/phone'
        wx.request({
          url: url,
          data: { "phone": that.data.mobile },
          success: function (res) {
            console.log(res)
            if (res.data.success) {
              wx.showToast({
                title: '验证码发送成功',
                icon: 'none',
                duration: 2000,
                success: function () {
                  //验证码
                  var Timer = setInterval(() => {
                    TIME--
                    if (TIME === 0) {
                      clearInterval(Timer)
                      that.setData({ isSendingTxt: "获取验证码", isSending: false, canClick: true })
                      TIME = 60
                      return
                    } else {
                      that.setData({
                        isSendingTxt: TIME + "s",
                        isSending: true
                      })
                    }
                  }, 1000)
                }
              })
            } else {
              //发送失败
              wx.showToast({
                title: res.data.msg,
                icon: 'none',
                duration: 2000
              })
            }
          },
          fail: function (error) {
            console.log(error)
          }
        })
      }
    }
  },
  //校验验证码
  testCode(e) {
    var that = this;
    if (e.detail.value.length >= 6) {
      this.setData({
        code: e.detail.value,
        testCode: true,
      })

      wx.showLoading({
        title: '加载中',
        mask: true
      })

      //校验验证码登录
      //保存全局登录状态
      var url = app.globalData.host + "/register/verify"
      wx.request({
        url: url,
        data: { "phone": that.data.mobile, "codeNum": that.data.code},
        success: function (res) {
          console.log(res)
          if(res.data.success){
            wx.showToast({
              title: '验证成功',
              icon: 'none',
              duration: 2000,
              success: function(){
                that.setData({userLogin: true})
              }
            })
          }else{
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000
            })
          }
          setTimeout(function () {
            wx.hideLoading()
          }, 2000)
        },
        fail: function (error) {
          console.log(error)
        }
      })
    }
  },
  testForm: function (data){
    var status = true
    var event = wx.getStorageSync('event')
    var requireItem = util.resolveFieldRequire(event.layout)
    for (let key in requireItem){
      if (requireItem[key].required && data[requireItem[key].english] == '') {
        wx.showToast({
          title: '请填写' + requireItem[key].name,
          icon: 'none',
          duration: 2000,
          success: function () {
            status = false
            return false
          }
        })
        status = false
        return false
      } else if (requireItem[key].required && data[requireItem[key].english] == '中国大陆'){
        if (data['province'] == ''){
          wx.showToast({
            title: '请填写省份',
            icon: 'none',
            duration: 2000,
            success: function () {
              status = false
              return false
            }
          })
          status = false
          return false
        }
        if (data['city'] == '') {
          wx.showToast({
            title: '请填写城市',
            icon: 'none',
            duration: 2000,
            success: function () {
              status = false
              return false
            }
          })
          status = false
          return false
        }
      } else if (requireItem[key].english == 'email' && data['email'] != ''){
        var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/g.test(data['email'])
        if(!reg){
          wx.showToast({
            title: '请输入正确的邮箱格式',
            icon: 'none',
            duration: 2000,
            success: function () {
              status = false
              return false
            }
          })
          status = false
          return false
        }
      } else if (requireItem[key].english == 'telephone' && data['telephone'] != '') {
        var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.exec(data['telephone'])
        if (!reg) {
          wx.showToast({
            title: '请输入正确的电话格式',
            icon: 'none',
            duration: 2000,
            success: function () {
              status = false
              return false
            }
          })
          status = false
          return false
        }
      } 
    }
    return status
  },
  formSubmit(e) {
    //校验
    var that = this
    let data = e.detail.value
    data.photo = that.data.photo //单独上传照片字段
    data.sex = (that.data.selectSex != '请选择') ? that.data.selectSex : ''
    data.country = (that.data.selectCountry != '请选择') ? that.data.selectCountry : ''
    data.province = (that.data.selectProvince != '请选择') ? that.data.selectProvince : ''
    data.city = (that.data.selectCity != '请选择') ? that.data.selectCity : ''
    var testForm = this.testForm(data)
   
    if(!testForm){
      that.setData({ canSubmit: true })
      return
    }
    if (that.data.canSubmit) {
      that.setData({ canSubmit: false })
      //重新获取code,以前的code会失效
      wx.login({
        success: function (res) {
          if (res.code) {
            data.cellphone = that.data.mobile
            data.invite_openid = wx.getStorageSync('inviteOpenId');
            data.event_id = wx.getStorageSync('event').id;
            data.open_id = wx.getStorageSync('user').openid;
            var params = {
              data: data,
              code: res.code
            }
            console.log(params)
            var url = app.globalData.host + "/register/information"
            wx.request({
              url: url, 
              data: params,
              success: function (res) {
                if(res.data.msg){
                  wx.showToast({
                    title: '报名成功',
                    icon: 'none',
                    duration: 2000,
                    success: function(){
                      wx.setStorageSync('currentPerson', res.data)
                      setTimeout(() => {
                        wx.redirectTo({
                          url: '../ticket/ticket'
                        })
                      },2000)
                    }
                  })
                }
              },
              fail: function(){
                that.setData({ canSubmit: true })
              }
            })
          }
        }
      });
    }
  },
  onLoad: function (options) {
    var that = this
    this.getHeight()
    var event = wx.getStorageSync('event')
    var fields = util.resolveFieldData(event.layout)
    this.setData({
      fields: fields,
      color: event.color
    }, ()=>{
      console.log(this.data)
    })
    var countryList = cascade.getCountryData()
    countryList.then((res) => {
      that.setData({ country: res})
    })
    var provinceList = cascade.getProvinceData()
    provinceList.then((res) => {
      that.setData({ province: res })
    })
  }
})
