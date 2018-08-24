
function circleImg(url, ctx, dx, dy, sw, sy) {
  ctx.save();
  ctx.beginPath();
  ctx.arc(sw / 2 + dx, sy / 2 + dy, sw / 2, 0, Math.PI * 2, false);
  ctx.closePath()
  ctx.clip();

  ctx.drawImage(url, dx, dy, sw, sy);
  ctx.restore();
}

const LogoSyle = {
  wxlogo1: {
    width: 180,
    height: 180,
    logoPostion: [56, 56, 68, 68]
  },
  wxlogo2: {
    width: 180,
    height: 205,
    logoPostion: [71, 71, 38, 38]
  }
}

export function saveWXLogo(name){
  var oBody = document.getElementById(name)
  var pic1 = oBody.childNodes[0].src
  var pic2 = oBody.childNodes[1].src
  var title = "小瓜报名"+Date.parse(new Date())
  var canvas = document.getElementById(name+"-canvas")
  var img = new Image()
  img.crossOrigin="anonymous";
 	img.src = pic1
  img.onload=function(){
    canvas.width = LogoSyle[name].width;//这个设置不能丢，否者会成为canvas默认的300*150的大小
    canvas.height = LogoSyle[name].height;//这个设置不能丢，否者会成为canvas默认的300*150的大小
    var ctx = canvas.getContext("2d");
    var logo = new Image()
    logo.crossOrigin="anonymous";
   	logo.src = pic2
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(img, 0, 0, LogoSyle[name].width, LogoSyle[name].height);

    if(pic2){
      logo.onload = function(){
        var position = LogoSyle[name].logoPostion;
        circleImg(logo, ctx, position[0], position[1], position[2], position[3])
        var dataURL = canvas.toDataURL("image/jpeg");
        var aBtn = document.createElement("a");
        aBtn.href = dataURL;
        aBtn.download = title+".jpg";
        oBody.appendChild(aBtn);
        aBtn.click();
        aBtn.remove();
      }
    }else{
      var dataURL = canvas.toDataURL("image/jpeg");
      var aBtn = document.createElement("a");
      aBtn.href = dataURL;
      aBtn.download = title+".jpg";
      oBody.appendChild(aBtn);
      aBtn.click();
      aBtn.remove();
    }
 	}
}

export function galaxyBackground(){
  var canvas = document.getElementById("canvasBg");
  var ctx = canvas.getContext("2d");
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  var w = canvas.width;
  var h = canvas.height;

  var part_count = 600;
  var P = [];
  var X, Y;
  var angle = 0.01;
  var centerX = w * 0.5,
      centerY = h * 0.5;

  var part = function(x,y,ix,iy,vx,vy,a,dist) {
    this.x = x;
    this.y = y;
  	this.ix = ix;
    this.iy = iy;
    this.vx = vx;
    this.vy = vy;
    this.a = a;
    this.dist = dist;
  }
  function init(){
    var x,y,ix,iy,vx,vy,a,dist;
    for(var i=0; i<part_count;i++){
      ix = x;
      iy = y;
      vx = random(-1,1);
      vy = random(-1,1);
      let rand = random(-80,100);
      dist = part_count/10+i;
      a = 1;

      P.push(new part(x,y,ix,iy,vx,vy,a,dist));
    }
  }
  init();
  function bg(){
    ctx.fillStyle = '#000';
    ctx.globalAlpha=0.25;
    ctx.fillRect(0,0,canvas.width,canvas.height,1);
  }
  function distance(dx,dy){
    return Math.sqrt(dx * dx + dy * dy);
  }
  function draw(){
    for(var i=0; i<P.length;i++){
      var p = P[i];
      p.a += 0.008;
      p.x = centerX + Math.cos(i+p.a) * (p.dist*i*0.1);
      p.y = centerY + Math.sin(i+p.a) * (p.dist);
      ctx.fillStyle = '#fff';
      ctx.fillRect(p.x, p.y,3, 3);
    }
  }
  function loop(){
    bg();
    draw();
    window.requestAnimationFrame(loop);
  }
  loop();
  function resize(){
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    centerX = window.innerWidth * 0.5;
    centerY = window.innerHeight * 0.5;
  }
  function random(min, max) {
    return Math.random() * (max - min) + min;
  }
}

export function detailGalaxyPage(data, graphTable){
   var source = {}
   source.nodes = []
   source.links = []
   for(let key in data){
     //创建node
     var ret = {}
     ret.name = data[key].name
     ret.id = key
     ret.itemStyle = null
     ret.value = data[key].invite_num
     ret.symbol = data[key].photo ? "image://"+data[key].photo : "image://"+data[key].head_imgurl
     ret.symbolSize = data[key].diameter

     ret.x = data[key].x
     ret.y = data[key].y

     ret.label = {normal: {show: false}}
     ret.openId =  data[key].open_id

     //创建Links
     var put = {}
     put.source = key
     let inviter
     let starkey
     if(data[key].inviter){
       starkey = data[key].inviter ? data[key].inviter : ""
       put.target = graphTable[starkey]
     }else{
       put.target = null
     }

     put.id = key
     put.name = null
     put.lineStyle = {color:'#fff'}

     source.nodes.push(ret)
     source.links.push(put)
   }
   return source
}
