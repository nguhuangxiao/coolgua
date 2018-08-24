import echarts from 'echarts'
export function chartstatis1(data){
  var myChart = echarts.init(document.getElementById('main1'));
  let arrMap1 = []
  let arrMap2 = []
  let arrMap3 = []
  for(let key in data.chartDatas){
    let item = data.chartDatas[key]
    arrMap1.push(item.showcount)
    arrMap2.push(item.regcount)
    arrMap3.push(item.sharecount)
  }
  let option = {
      xAxis: {
          name:'时间/天',
          type: 'category',
          boundaryGap: false,
          data: data.days
      },
      tooltip : {
          trigger: 'axis',
          axisPointer: {
              type: 'cross',
              label: {
                  backgroundColor: '#6a7985'
              }
          }
      },
      yAxis: {
          name:'数量/个',
          type: 'value'
      },
      legend: {
          data:['浏览量','报名数','分享数']
      },
      series: [{
          name:'浏览量',
          data: arrMap1,
          type: 'line',
          areaStyle: {}
      },{
          name:'报名数',
          data: arrMap2,
          type: 'line',
          areaStyle: {}
      },{
          name:'分享数',
          data: arrMap3,
          type: 'line',
          areaStyle: {}
      }]
  };
  myChart.setOption(option)

}


export function chartstatis2(res){
    var myChart = echarts.init(document.getElementById('main2'));
    var friendName = []
    var friendVaue = []
    for(let key in res.data){
      friendName.push(res.data[key].name)
      friendVaue.push(res.data[key].effect)
    }
    let option = {
        title: {
            text: '影响力排行前十',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            minInterval: 1
        },
        yAxis: {
            type: 'category',
            data: friendName.reverse()
        },
        series: [
            {
                name: null,
                type: 'bar',
                data: friendVaue.reverse(),
                label: {
                    normal: {
                        position: 'right',
                        fontSize: 16,
                        fontWeight:700,
                        show: true
                    }
                }
            }

        ]
    };
    myChart.setOption(option)

}

export function chartstatis3(res){
  var myChart = echarts.init(document.getElementById('main3'));
  var friendName = []
  var friendVaue = []
  for(let key in res.data){
    friendName.push(res.data[key].name)
    friendVaue.push(res.data[key].invite_num)
  }

  let option = {
      title: {
          text: '邀请排行前十',
      },
      tooltip: {
          trigger: 'axis',
          axisPointer: {
              type: 'shadow'
          }
      },
      grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
      },
      xAxis: {
          type: 'value',
          minInterval: 1
      },
      yAxis: {
          type: 'category',
          data: friendName.reverse()
      },
      series: [
          {
              name: null,
              type: 'bar',
              data: friendVaue.reverse(),
              label: {
                  normal: {
                      position: 'right',
                      fontSize: 16,
                      fontWeight:700,
                      show: true
                  }
              }
          }

      ]
  };
  myChart.setOption(option)
}
