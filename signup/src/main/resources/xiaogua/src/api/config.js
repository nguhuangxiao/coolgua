import { getCookie, setCookie, getUrlParam } from "common/js/dom"
import { getLocalStorage, setLocalStorage} from "common/js/util"
export const ERR_OK = 200
export let globalSatus
let userParams = userParams ? userParams : ""

export function ssoLink(){
  if(location.host === 'reg.coolgua.net' || location.hostname === 'localhost'){
    return 'http://sso.coolgua.com:202'
  }else{
    return 'https://home.coolgua.net'
  }
}


var ssoLink = (location.protocol == 'https:') ? 'https://home.coolgua.net' : 'http://sso.coolgua.com:202';


export function tokenParam (){
  let fp
  if(getUrlParam("fp")){
    fp = getUrlParam("fp")
    setLocalStorage("fp", fp)
  }else{
    fp = getLocalStorage("fp")
    if(fp == ''){
      fp = null
    }
  }
  return "/common/login/"+fp
}

export function interfaceToken() {
  let fp = getLocalStorage("fp")
  let orgId = getLocalStorage("orgId")
  let userId = getLocalStorage("userId")
  return fp+"/"+orgId+"/"+userId
}
