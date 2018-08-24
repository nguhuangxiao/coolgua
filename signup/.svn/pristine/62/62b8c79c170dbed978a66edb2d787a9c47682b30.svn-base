//ticket.js
var util = require('../../utils/util.js');
const canvas = require('../../utils/canvas.js')

const app = getApp()

Page({
  data: {
    userLogin: app.globalData.userLogin,
    height: '0px',
    name: '',
    phone: '',
    title: "",
    eventTime:"",
    startTime:"",
    endTime:"",
    address: '',
    qrcode: '',
    barcode: '',
    canShow: false,
    country: "",
    province: "",
    city: "",
    color:""
  },
  saveTicket(e) {
    var that = this
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
      height: 1108,
      destWidth: 750 * scale,
      destHeight: 1108 * scale,
      canvasId: 'shareImg',
      success: function (res) {
        console.log(res.tempFilePath);
        var sharePicUrl = res.tempFilePath;
        //微信授权-保存到手机相册
        wx.getSetting({
          success: (res) => {
            console.log(res)
            console.log(res.authSetting['scope.writePhotosAlbum'])
            if (res.authSetting['scope.writePhotosAlbum'] == false) {
              wx.openSetting({
                success: (res) => { }
              })
            } else {
              wx.saveImageToPhotosAlbum({
                filePath: sharePicUrl,
                success(res) {
                  wx.showModal({
                    content: '保存电子票成功，请到相册查看',
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
    })
  },
  getHeight() {
    let wh = wx.getSystemInfoSync().windowHeight
    let ww = wx.getSystemInfoSync().windowWidth
    let sw = wx.getSystemInfoSync().screenWidth
    let sh = wx.getSystemInfoSync().screenHeight
    var bh = wh - 69
    if(bh > 580){
      bh = 580
    }
    this.setData({
      height: bh + "px"
    })
  },
  goIndexPage: function (event){
    var url = event.currentTarget.dataset.url
    wx.reLaunch({
      url: url
    })
  },
  goPage: function (event) {
    var url = event.currentTarget.dataset.url
    util.goPage(url)
  },
  drawTicket: function(){
    /* 获得要在画布上绘制的图片 */
    let promise1 = new Promise(function (resolve, reject) {
      wx.getImageInfo({
        src: '../../common/img/canvas_share.jpg',
        success: function (res) {
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
    Promise.all(
      [promise1]
    ).then(res => {
      const ctx = wx.createCanvasContext('shareImg')
      /* 绘制图像到画布*/
      ctx.drawImage('../../' + res[0].path, 0, 0, 750, 1108)
      /* 绘制文字*/
      canvas.drawInfomation(ctx, this.data)
    })
  },
  getData: function(){
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
        that.setData({
          name: res.data.name,
          phone: res.data.cellphone,
          qrcode: res.data.numUrl,
          barcode: res.data.serialNumber,
          canShow: true
        }, ()=> {
          console.log(res.data.numUrl)
          that.drawTicket()
          wx.hideLoading()
        })
      }
    })
  },
  onLoad: function (options) {
    var that = this
	  wx.getStorage({
   	   key: 'event',
   	   success: function(res) {
   		  var time =res.data.eventTime.split('~')
   	     that.setData({
          title: res.data.name,
          eventTime:res.data.eventTime,
          address: res.data.address,
          startTime: time[0],
          endTime: time[1],
          country: res.data.country,
          province: res.data.province,
          city: res.data.city,
          color: res.data.color
   	     })
   	   }
   })
    this.getHeight()
    this.getData()   
  }
})
