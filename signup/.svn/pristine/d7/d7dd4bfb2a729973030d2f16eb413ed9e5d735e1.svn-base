/**
  排序拖拽
  基本字段：手机、姓名、单位、部门、职位、地址、邮箱、电话、性别、国家/省份/城市、照片
  INSERT INTO tb_field VALUES ('0', 'cellphone', '0');
  INSERT INTO tb_field VALUES ('1', 'name', '0');
  INSERT INTO tb_field VALUES ('2', 'company', '0');
  INSERT INTO tb_field VALUES ('3', 'department', '0');
  INSERT INTO tb_field VALUES ('4', 'position', '0');
  INSERT INTO tb_field VALUES ('5', 'address', '0');
  INSERT INTO tb_field VALUES ('6', 'email', '0');
  INSERT INTO tb_field VALUES ('7', 'telephone', '0');
  INSERT INTO tb_field VALUES ('8', 'sex', '0');
  INSERT INTO tb_field VALUES ('9', 'country', '0');
  INSERT INTO tb_field VALUES ('10', 'province', '0');
  INSERT INTO tb_field VALUES ('11', 'city', '0');
  INSERT INTO tb_field VALUES ('12', 'photo', '0');

  public static final Integer STRING = 0; 单行文本
  public static final Integer TEXT = 1; 多行文本
  public static final Integer IMAGE = 2; 图片
  public static final Integer URL = 3; 链接
  public static final Integer RADIO = 4; 单选
  public static final Integer CHECKBOX = 5; 多选
  public static final Integer ATTACHMENT = 6; 附件
**/
const baseFieldArr = ['手机','姓名','单位','部门','职务','地址','邮箱','电话','性别','国家/省份/城市','照片']
const addFieldArr = ['单行文本','多行文本','单选框','多选框','附件']
const ADDFIELDLEN = 4
let TOTLENUM = 0

import { insertAfter } from "common/js/dom"

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
  TOTLENUM--
}

function requiredlab(){
  var target = this.parentNode
  var name = target.className
  if(name === 'ant-checkbox'){
    target.className = 'ant-checkbox ant-checkbox-checked';
  }else{
    target.className = 'ant-checkbox';
  }
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

export function sortBaseDrag(){
  $( "#sortable1, #sortable4").sortable({
    connectWith: "#sortable4",
    placeholder: "sortable-placeholder-one",
    remove: function(event, ui){
      var oParentBox = document.getElementById("sortable4");
      var target = ui.item[0];
      if(target){
        var typeId = target.getAttribute("typeId");
        var custom = target.getAttribute("custom");
        var fieldId = target.getAttribute("fieldId");
        var fieldName = target.getAttribute("name");
        var fieldLab = target.childNodes[0].innerHTML;
        var oLi = document.createElement("li");
        var str = "";

        str += '<label class="ant-checkbox-wrapper requireCheck">'
          str += '<span class="ant-checkbox">'
            str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
          str += '</span>'
          str += '<span>必填</span>'
        str += '</label>'
        str += '<input type="text" class="labname" readonly="" value="'+fieldLab+'"><i class="anticon anticon-delete"></i>'

        oLi.innerHTML = str;
        oLi.setAttribute('typeId', typeId)
        oLi.setAttribute('fieldId', fieldId)
        oLi.setAttribute('custom', custom)
        oParentBox.replaceChild(oLi, target);

        //绑定点击事件
        var requireBox = oLi.getElementsByClassName("requireCheck");
        var checkbox = requireBox[0].getElementsByTagName('input')[0];
        checkbox.addEventListener("click", requiredlab);

        //监听删除
        var removeIcon = oLi.getElementsByClassName('anticon-delete')[0];
        removeIcon.addEventListener("click", baseIconRemove);
      }
    }
  }).disableSelection();
}

export function sortAddDrag(){
  $( "#sortable3, #sortable4" ).sortable({
    connectWith: "#sortable4",
    placeholder: "sortable-placeholder-two",
    beforeStop: function(event, ui){
      //关键，防止字段区域拖拽到工具栏
      if(event.target.id === 'sortable4'){
        return
      }
    },
    out: function(event, ui){

    },
    over: function(event, ui){

    },
    start: function(event, ui){
      var inner = ui.item[0].innerHTML
      var holder = ui.placeholder[0];
      holder.innerHTML = inner;
      holder.style.opacity = 0.5;
    },
    stop: function(event, ui){
      var sortBox1 = document.getElementById("sortable3");
      if(sortBox1.childNodes.length < ADDFIELDLEN){
        var target = ui.item[0].cloneNode();
        var name = ui.item[0].childNodes[0].innerHTML.replace(/\s+/g,"")
        target.innerHTML = ui.item[0].innerHTML;
        let num = 0
        for(let key in addFieldArr){
          if(addFieldArr[key] === name){
            num = key
          }
        }
        sortBox1.insertBefore(target, sortBox1.childNodes[num])
      }
    },
    remove: function(event, ui){
      TOTLENUM ++
      var oParentBox = document.getElementById("sortable4");
      var target = ui.item[0];
      var custom = target.getAttribute("custom");
      var typeId = target.getAttribute("typeId");
      var str = "";
      if(target){
        //单行文本
        if(typeId === '0'){
          str += '<label class="ant-checkbox-wrapper requireCheck">'
            str += '<span class="ant-checkbox">'
              str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
            str += '</span>'
            str += '<span>必填</span>'
          str += '</label>'
          str += '<input type="text" class="labname" value="标题名称'+TOTLENUM+'"><i class="anticon anticon-delete"></i>'
        //多行文本
      }else if (typeId === '1') {
          str += '<label class="ant-checkbox-wrapper requireCheck">'
            str += '<span class="ant-checkbox">'
              str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
            str += '</span>'
            str += '<span>必填</span>'
          str += '</label>'
          str += '<textarea rows="4" class="labname">标题名称'+TOTLENUM+'</textarea><i class="anticon anticon-delete"></i>'
        }
        //单选框
        else if (typeId === '4') {
          str += '<label class="ant-checkbox-wrapper requireCheck">'
            str += '<span class="ant-checkbox">'
              str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
            str += '</span>'
            str += '<span>必填</span>'
          str += '</label>'
          str += '<input type="text" value="标题名称'+TOTLENUM+'" class="labname"><i class="anticon anticon-delete"></i>'
          str += '<div class="radio-box" typeId="'+typeId+'">'
            str += '<div>'
              str += '<label class="ant-radio-wrapper">'
                str += '<span class="ant-radio"><input type="radio" class="ant-radio-input" value=""><span class="ant-radio-inner"></span></span>'
              str += '</label><input type="text" class="labput" value="选项1"/><i class="anticon anticon-plus" style="margin-left:43px"></i><i class="anticon anticon-minus" style="display:none"></i>'
            str += '</div>'
          str += '</div>'
        }
        //多选框
        else if (typeId === '5') {
          str += '<label class="ant-checkbox-wrapper requireCheck">'
            str += '<span class="ant-checkbox">'
              str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
            str += '</span>'
            str += '<span>必填</span>'
          str += '</label>'
          str += '<input type="text" value="标题名称'+TOTLENUM+'" class="labname"><i class="anticon anticon-delete"></i>'
          str += '<div class="checkbox-box" typeId="'+typeId+'">'
            str += '<div>'
              str += '<label class="ant-checkbox-wrapper">'
                str += '<span class="ant-checkbox"><input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span></span>'
              str += '</label><input type="text" class="labput" value="选项1"/><i class="anticon anticon-plus" style="margin-left:43px"></i><i class="anticon anticon-minus" style="display:none"></i>'
            str += '</div>'
          str += '</div>'
        }
        //附件
        else if (typeId === '6') {
          str += '<label class="ant-checkbox-wrapper requireCheck">'
            str += '<span class="ant-checkbox">'
              str += '<input type="checkbox" class="ant-checkbox-input" value=""><span class="ant-checkbox-inner"></span>'
            str += '</span>'
            str += '<span>必填</span>'
          str += '</label>'
          str += '<div class="ant-upload ant-upload-select ant-upload-select-text">'
              str += '<span tabindex="0" class="ant-upload" role="button">'
                str += '<input type="file" accept="" style="display: none;">'
                str += '<button type="button" class="ant-btn"><i class="anticon anticon-upload"></i><span>上传附件</span></button>'
              str += '</span>'
          str += '</div>'
          str += '<input type="text" readonly="" value="标题名称'+TOTLENUM+'" class="labname" style="display:none">'
          str += '<i class="anticon anticon-delete"></i>'
        }
        var oLi = document.createElement("li");
        oLi.setAttribute("typeId", typeId);
        oLi.setAttribute('custom', custom);
        oLi.innerHTML = str;
        oParentBox.replaceChild(oLi, target);
        //绑定点击事件
        var requireBox = oLi.getElementsByClassName("requireCheck");
        var checkbox = requireBox[0].getElementsByTagName('input')[0];
        checkbox.addEventListener("click", requiredlab);

        if(typeId === '4' || typeId === '5'){
          var name = (typeId === '4') ? "radio-box" : "checkbox-box";
          var oParent = oLi.getElementsByClassName(name)[0]
          bindEventOption(oParent)
        }

        //监听删除
        var removeIcon = oLi.getElementsByClassName('anticon-delete')[0];
        removeIcon.addEventListener("click", addIconRemove);

    }
  }
  }).disableSelection();
}
