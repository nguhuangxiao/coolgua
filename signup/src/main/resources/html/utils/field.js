
function resolveFieldData(layout){
  var layout = JSON.parse(layout)
  var field = []
  for (let key in layout){
    let item = layout[key]
    let data = {}
    if (item.typeId === 4 || item.typeId === 5){
      let dictionary = item.dictionary.split('##@@##')
      let val = []
      for (key in dictionary){
        var put = {}
        put.name = dictionary[key]
        put.value = dictionary[key]
        val.push(put)
      }
      item.value = val
    }
  }
  return layout
}

function resolveFieldRequire(layout){
  var layout = JSON.parse(layout)
  var ret = []
  for (let key in layout) {
    if (layout[key].english != 'cellphone'){
      var obj = {}
      obj.required = layout[key].required
      obj.english = layout[key].english
      obj.name = layout[key].name
      ret.push(obj)
    }
  }
  return ret
}

module.exports = {
  resolveFieldData,
  resolveFieldRequire
}
