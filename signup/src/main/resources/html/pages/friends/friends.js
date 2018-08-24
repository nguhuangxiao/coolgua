//ticket.js
const app = getApp()
Page({
  data: {
    title: "",
    userInfo: {},
    photo: "",
    canvasPhotoUrl: "",
    sameTrade: '',
    inviteUser: [],
    byInviteUser: null,
    color:""
  },
  authorization: function () {
    var that = this
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              that.setData({
                userInfo: res.userInfo
              })
            }
          })
        }
      }
    })
  },
  onLoad: function () {
    this.setData({
      color: wx.getStorageSync('event').color,
      photo: wx.getStorageSync('photo')
    })

    var that = this
    this.authorization()
    var url = app.globalData.host + "/invite/sametrade"
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: {
        openId: wx.getStorageSync('user').openid,
        eventId: wx.getStorageSync('eventId')
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var logobox = res.data.inviteUser.slice(0)
        for (let key in logobox){
          if (logobox[key].photo){
            logobox[key].photo = logobox[key].photo.replace(/.jpg/g, '_c.png')
          }
        }
        that.setData({
          sameTrade: res.data.sameTrade,
          inviteUser: logobox,
          byInviteUser: res.data.byInviteUser ? res.data.byInviteUser : null,
          title:wx.getStorageSync('event').name
        }, () => {
          console.log(that)
        })
      }
    })
  }
})
