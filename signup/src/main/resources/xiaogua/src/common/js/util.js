import { getCookie } from "common/js/dom"

export function checkParams(fn, args) {
	var timer = setInterval(function(){
		if(getCookie("orgId") && getCookie("userId")){
			clearInterval(timer)
			return fn.apply(this, args);
		}
	},200)
}

export function setLocalStorage(key, value) {
	let ret
	if(key){
		ret = localStorage.setItem(key, value);
	}
	return
}

export function getChairLength(str){
	return str.replace(/[^\u0000-\u00ff]/g,"aa").length
}

export function getLocalStorage(key) {
	let ret = ""
	if(key){
		ret = localStorage.getItem(key) || "";
	}
	return ret
}

export function eachFindOne(arr, id){
	for(let i=0; i<arr.length; i++){
		if(arr[i].open_id === id){
			return arr[i]
		}
	}
}

export function formatDate(time) {
	var date = new Date(time);
  let Y = date.getFullYear()
  let M = (date.getMonth()+1)
  let D = date.getDate()

  let h = date.getHours()
  let m = date.getMinutes()
  //let s = date.getSeconds()
  return Y+"-"+getzf(M)+"-"+getzf(D)+" "+getzf(h)+":"+getzf(m)

  function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
  }
}

export function handleCountryData(data) {
	let ret = []
	for(let key in data){
		let index = data[key].search(/\|/)
		let val = data[key].substring(0, index)
			ret.push(val)
	}
	return ret
}

/**
·缩放背景
**/
function scaleCanvasBg(e, target){
	if(target){
		let x = e.x;
		let y = e.y;
		var zoom = parseInt(target.style.zoom, 10)||100;
		zoom += e.wheelDelta/12;
		if (zoom > 40 && zoom < 250) {
			target.style.zoom=zoom+'%'
			return false;
		}
	}
}

function scrollFunc(e){
		var target = document.getElementById("chartBox")
    e = e || window.event;
    if(e.wheelDelta){
    	scaleCanvasBg(e, target)
    }else if(e.detail){//Firefox
    	scaleCanvasBg(e, target)
    }
}

export function dragCanvasMove(obj){
	var canMove = false
	var relx
	var rely
	obj.onmousedown = function(event){
		var ev = ev||event;
		canMove = true
		relx = ev.pageX - obj.offsetLeft
		rely = ev.pageY - obj.offsetTop
		event.preventDefault()
		document.onmousemove = function(ev){
			var ev = ev||event;
			if(canMove){
				var left =  ev.pageX - relx
				var top = ev.pageY - rely
				obj.style.left = left + 'px'
				obj.style.top = top + 'px'
			}
		}
	}
	document.onmouseup = function(){
		document.onmousemove = null;
	}
	if(document.addEventListener){
      document.addEventListener('DOMMouseScroll',scrollFunc,false);
  }
  window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome
}
