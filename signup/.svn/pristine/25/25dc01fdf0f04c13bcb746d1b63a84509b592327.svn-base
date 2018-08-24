const baseFieldArr = ['手机','姓名','单位','部门','职务','地址','邮箱','电话','性别','国家/省份/城市','照片']
const addFieldArr = ['单行文本','多行文本','单选框','多选框','附件']
const ADDFIELDLEN = 4
let TOTLENUM = 0
import { insertAfter } from "common/js/dom"
export function updateLayout(item){
  var str = "";
  var layoutJson = JSON.parse(item)
  var obj = []
  //数据中去掉基本字段
  for(let i in layoutJson){
    if(layoutJson[i].english !== 'cellphone' && layoutJson[i].english !== 'name'){
      obj.push(layoutJson[i])
    }
  }
  var sortable1 = document.getElementById("sortable1")
  for(let key in obj) {
    var id = obj[key].id
    var name = obj[key].name
    var english = obj[key].english
    var typeId = obj[key].typeId
    var fieldId = obj[key].fieldId
    var require = obj[key].required ? "ant-checkbox-checked" : ""
    var custom =  obj[key].custom
    if(!custom){
      for(let sn=0; sn<sortable1.childNodes.length; sn++){
        if(sortable1.childNodes[sn].getAttribute("fieldId") == fieldId){
          sortable1.removeChild(sortable1.childNodes[sn])
        }
      }
    }
    str +=  '<li typeId="'+typeId+'" custom="'+custom+'" fieldId="'+fieldId+'" english="'+english+'" id='+id+'>'
    if(typeId === 0 || typeId === 2){
      str += '<label class="ant-checkbox-wrapper requireCheck">'
        str += '<span class="ant-checkbox '+require+'">'
          str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
        str += '</span>'
        str += '<span>必填</span>'
      str += '</label>'
      if(!custom){
        str += '<input type="text" placeholder="标题名称" readonly="" class="labname ant-input" value="'+name+'"/>'
      }else{
        str += '<input type="text" placeholder="标题名称" class="labname ant-input" value="'+name+'"/>'
      }
      str += '<i class="anticon anticon anticon-delete"></i>'
      }else if (typeId === 1) {
        str += '<label class="ant-checkbox-wrapper requireCheck">'
          str += '<span class="ant-checkbox '+require+'">'
            str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
          str += '</span>'
          str += '<span>必填</span>'
        str += '</label>'
        str += '<textarea rows="4" class="labname">'+name+'</textarea>'
        str += '<i class="anticon anticon anticon-delete"></i>'
      }
      //单选框
      else if (typeId === 4) {
        str += '<label class="ant-checkbox-wrapper requireCheck">'
          str += '<span class="ant-checkbox '+require+'">'
            str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
          str += '</span>'
          str += '<span>必填</span>'
        str += '</label>'
        str += '<input type="text" value="'+name+'" class="labname"><i class="anticon anticon-delete"></i>'
        str += '<div class="radio-box" typeId="'+typeId+'">'
        let sourceArr = obj[key].dictionary.split("##@@##")
        for(let num in sourceArr){
          str += '<div>'
            str += '<label class="ant-radio-wrapper">'
              str += '<span class="ant-radio"><input type="radio" class="ant-radio-input"><span class="ant-radio-inner"></span></span>'
              if(sourceArr.length == 1){
                  str += '</label><input type="text" class="labput ant-input" placeholder="请输入选项" value="'+sourceArr[num]+'"/><i class="anticon anticon-plus" style="margin-left:43px"></i><i class="anticon anticon-minus" style="display:none"></i>'
              }else{
                  str += '</label><input type="text" class="labput ant-input" placeholder="请输入选项" value="'+sourceArr[num]+'"/><i class="anticon anticon-plus"></i><i class="anticon anticon-minus"></i>'
              }
          str += '</div>'
        }
        str += '</div>'
      }
      //多选框
      else if (typeId === 5) {
        str += '<label class="ant-checkbox-wrapper requireCheck">'
          str += '<span class="ant-checkbox '+require+'">'
            str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
          str += '</span>'
          str += '<span>必填</span>'
        str += '</label>'
        str += '<input type="text" value="'+name+'" class="labname"><i class="anticon anticon-delete"></i>'
        str += '<div class="checkbox-box" typeId="'+typeId+'">'
        let sourceArr = obj[key].dictionary.split("##@@##")
        for(let num in sourceArr){
          str += '<div>'
            str += '<label class="ant-checkbox-wrapper">'
              str += '<span class="ant-checkbox"><input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span></span>'
              if(sourceArr.length == 1){
                  str += '</label><input type="text" class="labput ant-input" placeholder="请输入选项" value="'+sourceArr[num]+'"/><i class="anticon anticon-plus" style="margin-left:43px"></i><i class="anticon anticon-minus" style="display:none"></i>'
              }else{
                  str += '</label><input type="text" class="labput ant-input" placeholder="请输入选项" value="'+sourceArr[num]+'"/><i class="anticon anticon-plus"></i><i class="anticon anticon-minus"></i>'
              }
          str += '</div>'
        }
        str += '</div>'
      }
      //附件
      else if (typeId === 6) {
        str += '<label class="ant-checkbox-wrapper requireCheck">'
          str += '<span class="ant-checkbox '+require+'">'
            str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
          str += '</span>'
          str += '<span>必填</span>'
        str += '</label>'
        str += '<div class="ant-upload ant-upload-select ant-upload-select-text" style="pointer-events: none">'
            str += '<span tabindex="0" class="ant-upload" role="button">'
              str += '<input type="file" accept="" style="display: none;">'
              str += '<button type="button" class="ant-btn"><i class="anticon anticon-upload"></i><span>上传附件</span></button>'
            str += '</span>'
        str += '</div>'
        str += '<input type="text" readonly="" value="'+name+'" class="labname" style="display:none">'
        str += '<i class="anticon anticon anticon-delete"></i>'
      }
      str += '</li>'
  }

  function requiredlab(){
    var target = this.parentNode
    var name = target.className.replace(/\s+/g,"");
    if(name == 'ant-checkbox'){
      target.className = 'ant-checkbox ant-checkbox-checked';
    }else{
      target.className = 'ant-checkbox';
    }
  }

  function baseIconRemove(){
    var sortBox1 = document.getElementById("sortable1");
    var oParent = this.parentNode;
    var oBox = oParent.parentNode;
    var sideLi = document.createElement("li");
    var fieldName = oParent.getElementsByClassName('labname')[0].value.replace(/\s+/g,"")
    sideLi.setAttribute('typeId', oParent.getAttribute('typeId'));
    sideLi.setAttribute('custom', oParent.getAttribute('custom'))
    sideLi.setAttribute('fieldId', oParent.getAttribute('fieldId'))
    sideLi.innerHTML = '<span>'+fieldName+'</span>';
    let num = 0
    for(let key in baseFieldArr){
      if(baseFieldArr[key] === fieldName){
        num = key
      }
    }
    //记忆还原
    oBox.removeChild(oParent);
    sortBox1.insertBefore(sideLi, sortBox1.childNodes[num-2])
  }

  function addIconRemove(){
    var oParent = this.parentNode;
    var oBox = oParent.parentNode;
    oBox.removeChild(oParent);
  }

  function bindEventOption(obj){
    for(var i=0; i<obj.childNodes.length; i++){
      var item = obj.childNodes[i]
      var addBtn = item.childNodes[2];
      var reduceBtn = item.childNodes[3];
      addBtn.addEventListener("click", addOption);
      addBtn.setAttribute('index', i+1)
      reduceBtn.addEventListener("click", reduceOption);
      reduceBtn.setAttribute('index', i+1)
    }
  }

  function addOption(){
    var index = parseInt(this.getAttribute('index'))+1
    var oParent = this.parentNode.parentNode;
    var typeId = oParent.getAttribute("typeId");
    var str = "";
    if(typeId === '4'){
      str += '<label class="ant-radio-wrapper">'
      str += '<span class="ant-radio"><input type="radio" class="ant-radio-input" value=""><span class="ant-radio-inner"></span></span>'
      str += '</label><input type="text" class="labput ant-input" value="选项'+index+'"/><i class="anticon anticon-plus"></i><i class="anticon anticon-minus"></i>'
    }else{
      str += '<label class="ant-checkbox-wrapper">'
      str += '<span class="ant-checkbox"><input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span></span>'
      str += '</label><input type="text" class="labput ant-input" value="选项'+index+'"/><i class="anticon anticon-plus"></i><i class="anticon anticon-minus"></i>'
    }
    var oDiv = document.createElement("div");
    oDiv.innerHTML = str
    insertAfter(oDiv, this.parentNode);
    bindEventOption(oParent)
    var addFirstChild = oParent.childNodes[0].getElementsByClassName('anticon-plus')[0]
    var firstChild = oParent.childNodes[0].getElementsByClassName('anticon-minus')[0]
    if(oParent.childNodes.length === 2){
      addFirstChild.style.marginLeft = '20px'
      firstChild.style.display = 'inline-block'
    }
  }

  function reduceOption(){
    var oParent = this.parentNode.parentNode;
    oParent.removeChild(this.parentNode, oParent);
    var addFirstChild = oParent.childNodes[0].getElementsByClassName('anticon-plus')[0]
    var firstChild = oParent.childNodes[0].getElementsByClassName('anticon-minus')[0]
    if(oParent.childNodes.length === 1){
      addFirstChild.style.marginLeft = '43px'
      firstChild.style.display = 'none'
    }
  }

  var sortable4 = document.getElementById("sortable4")
  sortable4.innerHTML = str

  for(let nu=0; nu<sortable4.childNodes.length; nu++){
    let custom = parseInt(sortable4.childNodes[nu].getAttribute("custom"))
    let typeId = sortable4.childNodes[nu].getAttribute("typeId")
    let pitem = sortable4.childNodes[nu]
    //必填
    let requireBox = pitem.getElementsByClassName("requireCheck");
    var checkbox = requireBox[0].getElementsByTagName('input')[0];
    checkbox.addEventListener("click", requiredlab);

    //删除
    let deleteBtn = pitem.getElementsByClassName('anticon-delete')[0];
    if(!custom){
      deleteBtn.addEventListener("click", baseIconRemove);
    }else{
      deleteBtn.addEventListener("click", addIconRemove);
    }

    //级联关系增加和删除
    if(typeId === '4' || typeId === '5'){
      var name = (typeId === '4') ? "radio-box" : "checkbox-box";
      var oParent = pitem.getElementsByClassName(name)[0]
      bindEventOption(oParent)
    }
  }

}
