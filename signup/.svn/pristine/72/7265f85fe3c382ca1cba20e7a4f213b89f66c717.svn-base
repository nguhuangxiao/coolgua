//index.js
//获取应用实例
const app = getApp()
var util = require('../../utils/util.js');
var WxParse = require('../../wxParse/wxParse.js');
let promise1, promise2
Page({
  data: {
    userLogin: false,
    banner: "",
    title: '',
    clickNum: "",
    shareNum: "",
    address: '',
    article: '',
    userInfo: null,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canShowTab: false,
    openId: '',
    inviteOpenId: '',
    eventId: '',
    event:{},
    eventStatus:null,
    canShow: '',
    eventTime:"",
    startTime:"",
    endTime:"",
    country:"",
    province:"",
    city:"",
    color:"",
    newStatus: false,
    activity:[]
  },
  //事件处理函数
  immediatelySignUp: function() {
    wx.navigateTo({
      url: '../sign/sign'
    })
  },
  goPage: function(event){
    var url = event.currentTarget.dataset.url
    util.goPage(url)
  },
  goIndexPage: function (event) {
    var url = event.currentTarget.dataset.url
    wx.reLaunch({
      url: url
    })
  },
  authorization: function (){ 
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
      this.getData()
    } else if (this.data.canIUse) {
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        this.getData()
        wx.setStorageSync('userInfo', res.userInfo)
      }
    }
  },
  getData: function (){
    console.log(this.data.eventId)
    var that = this
    var eventId = that.data.eventId
    var inviteOpenId = that.data.inviteOpenId
    inviteOpenId = inviteOpenId ? inviteOpenId : ""
    var that = this
    wx.login({
      success: res => {
        var url = app.globalData.host + '/userinfo/vote'   
        var data = {
          code: res.code,
          nickname: that.data.userInfo.nickName,
          headimgurl: that.data.userInfo.avatarUrl,
          sex: that.data.userInfo.gender,
          inviteOpenId: inviteOpenId,
          eventId: eventId
        }
        wx.request({
          url: url,
          data: data,
          success: function (res) {
            wx.setStorageSync('user', {
              showcount: res.data.showcount,
              sharecount: res.data.sharecount,
              openid: res.data.openid,
              session_key: res.data.session_key,
              userid: res.data.userid,
              restigerStatus: res.data.restigerStatus
            })
            that.setData({ 
              openId: res.data.openid,
              userLogin: res.data.restigerStatus
            })
          }
        })
      }
    })
  },
  getVote: function (){
    var that = this
    promise2 = new Promise(function (resolve, reject) {
      var eventId = that.data.eventId
      var inviteOpenId = that.data.inviteOpenId
      inviteOpenId = inviteOpenId ? inviteOpenId : ""
      var url = app.globalData.host + '/userinfo/getvote'
      wx.login({
        success: res => {
          wx.showLoading({
            title: '加载中',
            mask: true
          })
          wx.request({
            url: url,
            data: {
              code: res.code,
              eventId: eventId,
              inviteOpenId: inviteOpenId
            },
            method: "POST",
            header: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            success: function (res) {
              var time = res.data.event.eventTime.split('~')
              var introduce = WxParse.wxParse('article', 'html', res.data.event.introduce, that, 5)
              if (res.data.event.country == '中国大陆'){
                res.data.event.country = ''
              }
              wx.setStorageSync('event', res.data.event)
              that.setData({
                clickNum: res.data.showcount,
                shareNum: res.data.sharecount,
                userLogin: res.data.sign,
                banner: res.data.event.banner,
                title: res.data.event.name,
                eventTime: res.data.event.eventTime,
                startTime: time[0],
                endTime: time[1],
                address: res.data.event.address,
                event: res.data.event,
                eventStatus: res.data.event.status,
                canShowTab: true,
                country: res.data.event.country,
                province: res.data.event.province,
                city: res.data.event.city,
                color: res.data.event.color
              }, () => {
                wx.hideLoading()
              })
              app.globalData.userLogin = res.data.sign

              that.authorization()

              return Promise.resolve(res);
            },
            fail:function(){
              wx.hideLoading()
            }
          })
        }
      })
    })
  },
  getScene: function (scene){
    var that = this
    return promise1 = new Promise(function (resolve, reject) {
      wx.request({
        url: app.globalData.host + '/event/scene/' + scene,
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        success: function (res) {
          console.log("打印scene转化的参数，获取eventId")
          var data = res.data.data
          var eventId = data.eventId ? data.eventId : ''
          var openId = data.openId ? data.openId : ''
          that.setData({
            eventId: eventId,
            inviteOpenId: openId
          }, () => {
            //本地存储邀请人openid和活动id
            wx.setStorageSync('inviteOpenId', openId)
            wx.setStorageSync('eventId', eventId)
            that.getVote()
          })
          return Promise.resolve(res.data.data)
        }
      })
    })
    
  },
  getActivityList: function(){
    var that = this
    var url = app.globalData.host + '/event/getEvents/-1'
    wx.request({
      url: url,
      method: "POST",
      success: function (res) {
        console.log(res)
        that.setData({
          newStatus: true,
          activity: res.data.events
        })
      }
    })
  },
  onShow: function(){
    var that = this    
    var options = util.getCurrentPage().options
    var scene = decodeURIComponent(options.scene)
    console.log("scene场景值:" + scene)
    //scene有值代表通过小程序码访问，否则是微信分享链接进入
    if (scene !== 'undefined') {
      var getScene = this.getScene(scene)
    } else {
      if (options.eventId) {
        that.setData({
          eventId: options.eventId,
          inviteOpenId: options.inviteOpenId,
        }, () => {
          //本地存储邀请人openid和活动id
          var inviteOpenId = options.inviteOpenId ? options.inviteOpenId : ''
          wx.setStorageSync('inviteOpenId', options.inviteOpenId)
          wx.setStorageSync('eventId', options.eventId)
          that.getVote()
        })
      } else {
        var eventId = wx.getStorageSync('eventId')
        if (eventId){
          this.setData({
            eventId: wx.getStorageSync('eventId'),
            inviteOpenId: wx.getStorageSync('inviteOpenId'),
          }, () => {
            that.getVote()
          })
        }else{
          //表示从新入口进去，没有活动ID
          that.getActivityList()
        }
        
      }

    }   
  },
  onLoad: function (options) {
    var that = this
    /*
    Promise.all([promise1, promise2]).then(res => {
      console.log(res)
    })
    */
  },
  shareGetUserInfo: function (e) {
    var url = '../share/share'
    util.autoLogin(app, e, this, url)
  },
  loginGetUserInfo: function(e) {
    var url = '../sign/sign'
    util.autoLogin(app, e, this, url)
  },
  myshareGetUserInfo: function(e){
    var url = '../myshare/myshare'
    util.autoLogin(app, e, this, url)
  },
  ticketGetUserInfo: function(e){
    var url = '../ticket/ticket'
    util.autoLogin(app, e, this, url)
  }
  
})
