import { getCookie, setCookie, getUrlParam } from "common/js/dom"
import { getLocalStorage, setLocalStorage} from "common/js/util"
export const ERR_OK = 200
let userParams = userParams ? userParams : ""

export function tokenParam (){
  let fp
  if(getUrlParam("fp")){
    fp = getUrlParam("fp")
    setLocalStorage("fp", fp)
  }else{
    fp = getLocalStorage("fp")
  }
  return "/common/login/"+fp
}

export function interfaceToken() {
  let fp = getLocalStorage("fp")
  let orgId = getLocalStorage("orgId")
  let userId = getLocalStorage("userId")
  return fp+"/"+orgId+"/"+userId
}
