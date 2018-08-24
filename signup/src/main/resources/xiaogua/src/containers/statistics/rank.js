
function createZData(obj, arr, type){
  console.log(arr)
  var color = ['#ff2246', '#ff2246','#ff2246','#91a2d2','#91a2d2','#91a2d2','#91a2d2','#91a2d2','#91a2d2','#91a2d2']
  var simKey = type === 2 ? 'effect' : 'invite_num'
  let val = []
  for(let key in arr){
    val.push(arr[key][simKey])
  }
  val.sort(function(a,b){return a-b})
  const minVal = val[0]
  const maxVal = val[val.length-1];
  let maxChartNum = Math.ceil(maxVal + maxVal*0.2)
  var ol = '<ol>'
  for(let i in arr){
    var ml = 'margin-right:-42px'
    if(i == '9'){
      ml = 'margin-right:-51px'
    }
    var scale = (arr[i][simKey] / maxChartNum) * 100
    var num = parseInt(i)+1
    ol += '<li><div class="snumdiv" openId='+arr[i].open_id+' kwidth='+scale+' style="transition: width 1.5s;width:0;background:'+color[i]+'"><span class="val">'+arr[i][simKey]+'</span><span class="pm" style="'+ml+'">NO.<i>'+num+'</i></span></div></li>'
  }
  ol += '</ol>'
  return ol
}

function selectPerson(name, that){
  var oImg = name.getElementsByClassName('avatal')
  var oLi = name.getElementsByClassName('snumdiv')
  for(let key=0; key<oImg.length; key++){
    var item = oImg[key]
    item.addEventListener("click", function(event){
      var obj = {}
      var openId = this.getAttribute('openId')
      obj.openId = openId
      obj.top = event.pageY
      obj.left = event.pageX
      that.setState({currentPserson: obj})
    });
    oLi[key].addEventListener("click", function(event){
      var ret = {}
      var openId = this.getAttribute('openId')
      ret.openId = openId
      ret.top = event.pageY
      ret.left = event.pageX
      that.setState({currentPserson: ret})
    });
  }
}

function cssTransition(id){
  var box = document.getElementsByClassName("chart")[id]
  var oLi = box.childNodes[0].children
  for(let i=0;i<oLi.length;i++){
    var num = oLi[i].childNodes[0].getAttribute('kwidth')
    oLi[i].childNodes[0].style.width = num + "%"
  }
}

export function effectRankChart(data, that){
  var arr = data.data
  var myChart = document.getElementById('main2')
  var main = document.createElement('div')
  main.className = 'inner-box'
  var title = document.createElement('div')
  title.className = 'title'
  title.innerHTML = '影响力排行前十'
  main.appendChild(title)
  var box = document.createElement('div')
  box.className = 'chart-box'
  var LogoUl = document.createElement('ul')
  var oLi = ''
  for(let key in arr){
    oLi += '<li><img src="'+arr[key].head_imgurl+'" class="avatal" openId="'+arr[key].open_id+'"/><span class="name">'+arr[key].name+'</span></li>'
  }
  LogoUl.innerHTML = oLi
  box.appendChild(LogoUl)
  var chart = document.createElement('div')
  chart.className = 'chart'
  var html = createZData(chart, arr, 2)
  chart.innerHTML = html
  box.appendChild(chart)
  main.appendChild(box)

  var botitle = document.createElement('div')
  botitle.className = 'botitle'
  botitle.innerHTML = '影响力指数（曝光率）'
  main.appendChild(botitle)

  myChart.innerHTML = ""
  myChart.appendChild(main)
  setTimeout(()=>{
    cssTransition(0)
    selectPerson(myChart, that)
  },500)
}


export function inviteRankChart(data, that){
  var arr = data.data
  var myChart = document.getElementById('main3')
  var main = document.createElement('div')
  main.className = 'inner-box'

  var title = document.createElement('div')
  title.className = 'title'
  title.innerHTML = '邀请排行前十'
  main.appendChild(title)

  var box = document.createElement('div')
  box.className = 'chart-box'
  var LogoUl = document.createElement('ul')
  var oLi = ''
  for(let key in arr){
    oLi += '<li><img src="'+arr[key].head_imgurl+'" class="avatal" openId="'+arr[key].open_id+'"/><span class="name">'+arr[key].name+'</span></li>'
  }
  LogoUl.innerHTML = oLi
  box.appendChild(LogoUl)
  var chart = document.createElement('div')
  chart.className = 'chart'
  var html = createZData(chart, arr, 3)
  chart.innerHTML = html
  box.appendChild(chart)
  main.appendChild(box)

  var botitle = document.createElement('div')
  botitle.className = 'botitle'
  botitle.innerHTML = '邀请好友数'
  main.appendChild(botitle)

  myChart.innerHTML = ""
  myChart.appendChild(main)
  setTimeout(()=>{
    cssTransition(1)
    selectPerson(myChart, that)
  },500)

}
