//index.js
//获取应用实例
const app = getApp()
var WxParse = require('../../wxParse/wxParse.js');

Page({
  data: {
    motto: 'Coolgua报名',
    form: {
      title: '2018苦瓜开放日',
      clickNum: 2324,
      shareNum: 324,
      startTime: '2018-03-20 9:30',
      endTime: '2018-03-22 18:00',
      address: '北京市海淀区中关村会议中心',
      article:'<p style="color:red;">2018苦瓜开放日给你带来不一样的会展体验！</p><p>欢迎你报名参会，同时我们推出有奖推荐活动，转发分享本次活动，众多礼品等着你。</p><p>奖励规则：</p><p>1、成功邀请3名好友报名，活动现场领取精美礼品1份。</p><p>2、成功邀请10名好友报名，为你准备活动期间3天的午餐。</p><p>3、邀请排行前5名，将领取现金红包500元。</p><p>4、活动推广分享排行前10名，将领取现金红包200元。</p><p>你还在等什么，赶快报名参加活动！</p>'
    },
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  immediatelySignUp: function() {
    wx.navigateTo({
      url: '../sign/sign'
    })
  },
  onLoad: function () {
    var temp = WxParse.wxParse('article', 'html', this.data.form.article, this, 5);
    var temp = Object.assign({}, this.data.form, {
      article: temp
    })
    this.setData({form: temp})
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
