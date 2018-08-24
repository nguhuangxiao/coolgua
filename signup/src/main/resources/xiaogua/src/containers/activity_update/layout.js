const baseFieldType = {
  0: 'cellphone',
  1: 'name',
  2: 'company',
  3: 'department',
  4: 'position',
  5: 'address',
  6: 'email',
  7: 'telephone',
  8: 'sex',
  9: 'country',
  10: 'province',
  11: 'city',
  12: 'photo'
}
export function createLayout(){
  let layout = []
  let target = document.getElementById("sortable4")
  let oLi = target.childNodes

  //添加基本字段
  layout.push({name: '手机',english: 'cellphone',custom:0, fieldId: 0, required: 1})
  layout.push({name: '姓名',english: 'name',custom:0, fieldId: 1, required: 1})

  for(let i=0; i<oLi.length; i++){
    let ret = {}
    let english = oLi[i].getAttribute('english')
    let custom = parseInt(oLi[i].getAttribute('custom'))
    var fieldId = oLi[i].getAttribute('fieldId')
    var typeId = parseInt(oLi[i].getAttribute('typeId'))
    var id = oLi[i].getAttribute('id')
    //基本类型
    if(!custom){
      ret.name = oLi[i].getElementsByClassName("labname")[0].value
      ret.english = baseFieldType[fieldId]
      ret.custom = 0
      ret.fieldId = fieldId
    }else{
      ret.custom = 1
      ret.name = oLi[i].getElementsByClassName("labname")[0].value
      ret.english = english
      ret.fieldId = fieldId
    }
    ret.typeId = typeId

    if(id != null || id != 'null'){
      ret.id = id
    }

    var requireBox = oLi[i].getElementsByClassName("requireCheck")[0]
    var requireBoxStatus = requireBox.childNodes[0].className
    if(requireBoxStatus === 'ant-checkbox ant-checkbox-checked'){
      ret.required = 1
    }else{
      ret.required = 0
    }

    if(typeId === 4 || typeId === 5){
      var name = (typeId === 4) ? "radio-box" : "checkbox-box"
      var dictionaryStr = ""
      var item = oLi[i].getElementsByClassName(name)[0]
      for(let key=0; key<item.childNodes.length; key++){
        var obj = item.childNodes[key]
        var put = obj.getElementsByClassName("labput")[0].value + "##@@##"
        dictionaryStr += put
      }
      var new_dictionaryStr = dictionaryStr.substring(0, dictionaryStr.length-6)
      ret.dictionary = new_dictionaryStr
    }else{
      ret.dictionary = ''
    }
    layout.push(ret)
  }
  console.log(layout)

  return layout
}
