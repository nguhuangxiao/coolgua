/**
 * 绘制圆
 * author: nguhuangxiao
 */
function circleImg1(url, ctx, dx, dy, sw, sy) { 
  ctx.drawImage(url, 48, 30, 80, 80)
  ctx.drawImage('../../common/img/logomask.png', 48, 30, 80, 80);
}

function circleImg(url, ctx, dx, dy, sw, sy) {
  ctx.save();
  ctx.beginPath();
  ctx.arc(sw / 2 + dx, sy / 2 + dy, sw / 2, 0, Math.PI * 2, false);
  ctx.closePath()
  ctx.setStrokeStyle('#fff')
  ctx.stroke()
  ctx.clip();
 
  ctx.drawImage(url, dx, dy, sw, sy); 
  ctx.restore();
}

/**
 * 绘制图片
 * author: nguhuangxiao
 */
function drawInfomation(ctx, data) {
  let name = data.name + " (" + data.phone +")"
  let title = data.title
  let eventTime = data.eventTime.split('~')
  let time = eventTime[0] + " 至 " + eventTime[1]
  let address = data.country + data.province + data.city + data.address
  let barcode = data.barcode
  let qrcode = data.qrcode

  ctx.setFontSize(26)
  ctx.fillText(name, 52, 70)
  ctx.fillText(title, 52, 158)

  ctx.drawImage('../../common/img/time2.png', 50, 220, 40, 40)
  ctx.fillText(time, 108, 250)
  ctx.drawImage('../../common/img/place2.png', 50, 312, 40, 40)
  if (address.length>22){
    var address1 = address.substring(0, 22)
    var address2 = address.substring(22)
    ctx.fillText(address1, 108, 338)
    ctx.fillText(address2, 108, 378)
  }else{
    ctx.fillText(address, 108, 338)
  }
  

  ctx.fillText(barcode, 310, 550)
  wx.getImageInfo({
    src: qrcode,
    success: function (res) {
      console.log(res)
      ctx.rect(204, 605, 343, 343)
      ctx.setStrokeStyle('#bebebe')
      ctx.drawImage(res.path, 205, 606, 342, 342)
      /* 绘制 */
      ctx.stroke()
      ctx.draw()
    }
  })
}

function drawFriendImageInfo(target, ctx, data, res) {
  ctx.setFillStyle('#fff')
  ctx.fillRect(0, 0, 750, 600)

  ctx.setFillStyle("#000")
  let title = data.title
  let eventTime = data.eventTime.split('~')
  let time = eventTime[0] + " 至 " + eventTime[1]
  let address = data.country + data.province + data.city + data.address

  let name = data.nickName + '邀请您一起参加'
  if (name.length>15){
    var name1 = name.substring(0, 17)
    var name2 = name.substring(17)
    ctx.setFontSize(36)
    ctx.fillText(name1, 118, 35)
    ctx.fillText(name2, 118, 74)
  }else{
    ctx.setFontSize(40)
    ctx.fillText(name, 118, 42)
  }
  
  let yaoqing = data.company
  ctx.setFontSize(31)
  if (name.length > 15) {
    ctx.fillText(yaoqing, 118, 110)
  }else{
    ctx.fillText(yaoqing, 118, 100)
  }
  

  ctx.drawImage(res[0].path, 0, 122, 750, 359)

  ctx.setFontSize(30)
  ctx.drawImage('../../common/img/time2.png', 25, 499, 40, 40)
  ctx.fillText(time, 75, 530)
  ctx.drawImage('../../common/img/place2.png', 25, 548, 40, 40)
  ctx.fillText(address, 75, 578)
  
  if (data.photo) {
    var logoUrl = data.photo
  } else{
    var logoUrl = data.avatarUrl
  }
  wx.getImageInfo({
    src: logoUrl,
    success: function (res) {
      circleImg(res.path, ctx, 0, 0, 110, 110)
      if (data.photo){
        target.setData({ canvasPhotoUrl: res.path }, () => {
          ctx.draw(false, target.tabShareImg1)
        })
      }else{
        target.setData({ canvasAvatarUrl: res.path }, () => {
          ctx.draw(false, target.tabShareImg1)
        })
      }
    }
  }) 
}

function drawUserLogo(url, ctx) {
  //放入logo
  ctx.save();
  ctx.beginPath();
  ctx.arc(375, 791, 56, 0, Math.PI * 2, false);
  ctx.setStrokeStyle('#fff')
  ctx.closePath()
  ctx.stroke()
  ctx.clip();
  ctx.drawImage(url, 319, 735, 112, 112); // 推进去图片，必须是https图片
  ctx.restore();

}

function drawQuenImageInfo(ctx, data, res) {
  if (data.photo){
    var logoUrl = data.canvasPhotoUrl
  }else{
    var logoUrl = data.canvasAvatarUrl
  }
  console.log(logoUrl)
  ctx.drawImage(data.wxBanner, 48, 117, 655, 344)
   
  let name = data.nickName + '邀请您一起参加'
  let eventTime = data.eventTime.split('~')
  let time = eventTime[0] + " 至 " + eventTime[1]
  let address = data.country + data.province + data.city + data.address
 
  ctx.setFontSize(40)
  ctx.fillText(name, 138, 60)
  ctx.setFontSize(30)

  ctx.drawImage('../../common/img/time2.png', 48, 523, 40, 40)
  ctx.fillText(time, 105, 554)


  ctx.drawImage('../../common/img/place2.png', 48, 581, 40, 40)
  if (address.length > 22) {
    var address1 = address.substring(0, 22)
    var address2 = address.substring(22)
    ctx.fillText(address1, 105, 612)
    ctx.fillText(address2, 105, 652)
  } else {
    ctx.fillText(address, 105, 612)
  }
  
  let yaoqing = data.company
  ctx.setFontSize(31)
  ctx.fillText(yaoqing, 138, 100)

  let title = data.title
  ctx.setFontSize(40)
  ctx.fillText(title, 48, 502)

  var qrcode = data.qrcode
  wx.getImageInfo({
    src: qrcode,
    success: function (res) {
      ctx.drawImage(res.path, 244, 660, 262, 262)
      /* 绘制 */
      drawUserLogo(logoUrl, ctx)
      circleImg1(logoUrl, ctx, 48, 30, 80, 80)
      ctx.draw()
    }
  })
}

module.exports = {
  drawInfomation,
  drawFriendImageInfo,
  drawQuenImageInfo
}