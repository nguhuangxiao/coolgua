import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/graph';
import { detailGalaxyPage } from 'common/js/canvas'

const BIGSIZE = 100
const NORSIZE = 50
const SMLSIZE = 30
const WIDTH = document.documentElement.offsetWidth
const HEIGHT = document.documentElement.offsetHeight - 66



export function galaxyChart(data, that){
  var list = data.list
  let graphTable = {}
  for(let i in list){
    var inviter = list[i].inviter ? list[i].inviter : ""
    var name = list[i].name
    graphTable[name] = i
  }
  //核心位置
  var canvas_w = data.map.maxX - data.map.minX
  var canvas_y = data.map.maxY - data.map.minY
  var pidw = 0
  var pidh = 0
  if(canvas_w > WIDTH || canvas_y > HEIGHT){
    //计算圆心的偏差，让中心圆在页面中心
    canvas_w += 600
    canvas_y += 600
    pidw = canvas_w / 2 - WIDTH / 2
    pidh = canvas_y / 2 - HEIGHT / 2
  }else{
    canvas_w = WIDTH
    canvas_y = HEIGHT
  }
  that.setState({
    width: canvas_w,
    height: canvas_y,
    marginLeft: -pidw,
    marginTop: -pidh
  },()=>{
    var chart = echarts.init(document.getElementById('chartBox'));
    var graph = detailGalaxyPage(list, graphTable)
    let option = {
      title: {
          text: null,
          subtext: 'Default layout',
          top: 'bottom',
          left: 'right',
          borderRadius: 5
      },
      series : [
          {
              name: '邀请数量',
              type: 'graph',
              layout: 'none',  //circular,force
              data: graph.nodes,
              links: graph.links,
              roam: true,
              edgeSymbol:['arrow', 'circle']
          }
      ]
    };
    chart.setOption(option, true)
    var timer = null
    chart.on('mouseover', function (params) {
        clearTimeout(timer)
        var obj = {
          openId: params.data.openId,
          top:params.event.event.pageY,
          left:params.event.event.pageX
        }
        timer = setTimeout(()=>{
          that.setState({currentPserson: obj})
        }, 2000)
    });
    chart.on('mouseout', function (params) {
      clearTimeout(timer)
      setTimeout(()=>{
        if(that.refs.galaxydialog){
          that.refs.galaxydialog.close()
        }        
      },800)
    });
    chart.on('click', function (params) {
      clearTimeout(timer)
      that.props.history.push(location.pathname+'/'+params.data.openId)
    });
  })
}
