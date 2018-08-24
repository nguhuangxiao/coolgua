//myshare.js
var util = require('../../utils/util.js');
const app = getApp()
Page({
  data: {
    userLogin: app.globalData.userLogin,
    userInfo: {},
    photo: "",
    canvasPhotoUrl: "",
    title: "",
    sameTrade: '',
    inviteRanking: '',
    invitAttentionCount: 0,
    shareRanking: '',
    byInviteUser: null,
    userHeadPicUrl: "",
    inviteUser: [],
    isMore: false,
    color:'',
  },
  goIndexPage: function (event) {
    var url = event.currentTarget.dataset.url
    wx.reLaunch({
      url: url
    })
  },
  goPage: function (event) {
    var url = event.currentTarget.dataset.url
    wx.redirectTo({
      url: url
    })
  },
  goMyshare: function (event){
    var url = event.currentTarget.dataset.url
    util.goPage(url)
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
        console.log(res.data)
        var openId = wx.getStorageSync('user').openid
        var logobox = []
        for (var key in res.data.inviteUser){
          var item = res.data.inviteUser[key]
          if (res.data.inviteUser[key].open_id != openId && res.data.inviteUser[key].head_imgurl != 'undefined'){
            if (item.photo){
              item.photo = item.photo.replace(/.jpg/g, '_c.png')
            }
            logobox.push(item)
          }
        }
        if (logobox.length > 11){
          that.setData({isMore: true})
          logobox = logobox.slice(0, 11)
        }     
        //logobox = logobox.reverse()
        if (res.data.byInviteUser && res.data.byInviteUser.nickName){
          var byInviteUser = res.data.byInviteUser
        }else{
          var byInviteUser = null
        }
        that.setData({
          sameTrade: res.data.sameTrade,
          inviteRanking: res.data.inviteRanking,
          invitAttentionCount: res.data.invitAttentionCount,
          shareRanking: res.data.shareRanking,
          byInviteUser: byInviteUser,
          userHeadPicUrl: res.data.userHeadPicUrl,
          inviteUser: logobox,
          title: wx.getStorageSync('event').name,
          color: wx.getStorageSync('event').color,
          photo: res.data.userHeadPicUrl.replace(/.jpg/g, '_c.png')
        })
      }
    })
  }
})
