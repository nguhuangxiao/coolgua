//share.js
const app = getApp()
const util = require('../../utils/util.js')
const canvas = require('../../utils/canvas.js')

Page({
  data: {
    userInfo: {},
    height: '0px',
    avatarUrl: "",
    canvasAvatarUrl: "",
    photo: "",
    canvasPhotoUrl: "",
    nickName: "",
    banner: "",
    wxBanner: "",
    company: "",
    title: "",
    address: '',
    qrcode: '',
    shareImg1: "",
    shareImg2: "",
    canShow: '',
    eventTime: "",
    startTime: '',
    endTime: '',
    country: "",
    province: "",
    city: "",
    color: "",
    timePath:"",
    addressPath:""
  },
  getHeight() {
    let wh = wx.getSystemInfoSync().windowHeight
    let ww = wx.getSystemInfoSync().windowWidth
    let sw = wx.getSystemInfoSync().screenWidth
    let sh = wx.getSystemInfoSync().screenHeight
    this.setData({
      height: (wh - 103 - 18) + "px"
    })
  },
  onShareAppMessage: function (res) {
    /*
    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      width: 750,
      height: 600,
      destWidth: 750,
      destHeight: 600,
      canvasId: 'shareImg1',
      success: function (oImg) {
        console.log(oImg.tempFilePath)
      },
      fail: function (res) {
        console.log(res)
      }
    })
    */
    var inviteOpenId = wx.getStorageSync('user').openid
    var eventId = wx.getStorageSync('eventId')
    var that = this
    if (res.from === 'button') {
      // 来自页面内转发按钮
    }
    console.log('分享好友连接:' + '/pages/index/index?inviteOpenId=' + inviteOpenId)
    return {
      title: that.data.title,
      path: '/pages/index/index?inviteOpenId=' + inviteOpenId + '&eventId=' + eventId,
      imageUrl: that.data.shareImg1,
      success(status) {
        console.log(status)
      }
    }
    
  },
  //生成微信好友分享图片
  drawImage1: function(){
    var that = this
    var src=wx.getStorageSync('event').banner
    let promise1 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: src,
        success: function (res) {
          that.setData({ wxBanner: res.path })
          resolve(res);
        }
      })
    })
    let promise2 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: '../../common/img/time2.png',
        success: function (res) {
          resolve(res);
        }
      })
    })
    let promise3 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: '../../common/img/place2.png',
        success: function (res) {
          resolve(res);
        }
      })
    })
    Promise.all([promise1, promise2, promise3]).then(res => {
      const ctx = wx.createCanvasContext('shareImg1', this)
      canvas.drawFriendImageInfo(that, ctx, this.data, res)
    }).catch(function (error) {//加上catch 
      console.log(error);
    })
  },
  //分享缩略图生成后执行保存图片
  tabShareImg1: function(){ 
    var that = this
    that.drawImage2()//生成我的朋友圈图片
    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      width: 750,
      height: 600,
      destWidth: 750,
      destHeight: 600,
      canvasId: 'shareImg1',
      success: function (oImg) {
        that.setData({ shareImg1: oImg.tempFilePath },()=>{
          setTimeout(()=>{
            wx.hideLoading()
          }, 100)
        })
        console.log("分享好友图片" + that.data.shareImg1)
      },
      fail: function (res) {
        console.log(res)
      }
    })
  },
  //生成分享图片到朋友圈
  drawImage2: function(){
    var that = this
    let promise1 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: '../../common/img/quanShare.jpg',
        success: function (res) {
          resolve(res);
        }
      })
    })
    let promise3 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: '../../common/img/logomask.png',
        success: function (res) {
          resolve(res);
        }
      })
    })
    Promise.all([promise1, promise3]).then(res => {
      const ctx = wx.createCanvasContext('shareImg2', this)
      ctx.drawImage('../../' + res[0].path, 0, 0, 750, 1002)  
      canvas.drawQuenImageInfo(ctx, this.data, res)
    })
  },
  
  tabShareImg2: function(e){
    var that = this

    that.drawImage2();
    
    var scale = null;
    wx.getSystemInfo({
      success: function (res) {
        scale = res.pixelRatio
      }
    })
    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      width: 750,
      height: 1002,
      destWidth: 750 * scale,
      destHeight: 1002 * scale,
      canvasId: 'shareImg2',
      quality: 1,
      success: function (res) {
        var sharePicUrl = res.tempFilePath;
        console.log("微信保存图片")
        console.log(sharePicUrl);
        that.setData({ shareImg2: sharePicUrl })
        
        //查看权限
        wx.getSetting({
          success: (res) => {
            if (res.authSetting['scope.writePhotosAlbum'] == false) {
              wx.openSetting({
                success: (res) => { }
              })
            }else{
              wx.saveImageToPhotosAlbum({
                filePath: sharePicUrl,
                success(res) {
                  wx.showModal({
                    content: '图片已保存到相册，赶紧晒一下吧~',
                    showCancel: false,
                    confirmText: '好的',
                    confirmColor: '#333',
                    success: function (res) {
                      console.log(res)
                    }
                  })
                }
              })
            }
          }
        })
        
      },
      fail: function (res) {
        console.log(res)
      }
    }, this)
  },
  shareCount() {
    var that = this
    var url = app.globalData.host + "/invite/shareNum"
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: {
        openId: wx.getStorageSync('user').openid,
        eventId: wx.getStorageSync('eventId')
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {}
    })
  },
  previousImg() {
    wx.previewImage({
      current: qrcode, // 当前显示图片的http链接
      urls: [qrcode] // 需要预览的图片http链接列表
    })
  },
  authorization: function() {
    var that = this
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              that.setData({
                userInfo: res.userInfo,
                avatarUrl: res.userInfo.avatarUrl,
                nickName: res.userInfo.nickName
              }, ()=> {
                that.drawImage1();
              })
            }
          })
        }
      }
    })
  },
  getCurrentUserData: function() {
    var that = this
    var url = app.globalData.host + "/register/getinfo"
    wx.showLoading({
      title: '加载中',
      mask: true
    })
    wx.request({
      url: url,
      data: {
        openId: wx.getStorageSync('user').openid,
        eventId: wx.getStorageSync('eventId')
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if(res.data.msg){
          var photo = res.data.photo ? res.data.photo : ''
          that.setData({
            qrcode: res.data.qrCodeUrl,
            photo: photo
          },()=>{
            wx.setStorageSync('photo', photo)
          })
        }
      },
      fail: function(){
        wx.hideLoading()
      }
    })
  },
  imageLoad: function(e){
    this.setData({canShow: 'active'})
  },
  onLoad: function (options) {
    var that = this
    wx.getStorage({
    	   key: 'event',
    	   success: function(res) {
           var time = res.data.eventTime.split('~')
    	     that.setData({
    	    	    banner: res.data.banner,
    	    	    company:res.data.name,
    	    	    title: res.data.name,
    	    	    eventTime:res.data.eventTime,
                startTime: time[0],
                endTime: time[1],
    	    	    address: res.data.address,
                country: res.data.country,
                province: res.data.province,
                city: res.data.city,
                color: res.data.color
    	     })
    	   }
    })
    this.getHeight()
    //登录后-查询数据
    
    this.getCurrentUserData()

    this.authorization()
  }
})
