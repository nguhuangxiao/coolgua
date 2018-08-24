import React from 'react'
import './pagination.less'

class Pagination extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      pageNum: 1,
      pageSize: 10,
      total: 0,
      pages: 0
    }
  }
  componentWillReceiveProps(nextProps){
    this.setState({
      pageNum: nextProps.data.pageNum,
      pageSize: nextProps.data.pageSize,
      total: nextProps.data.total,
      pages: nextProps.data.pages
    })
  }
  renderPage() {
    const pages = this.state.pages
    if(!pages){
      return
    }
  }
  //跳转页面
  pageIndexChange(e) {
    const num = parseInt(e.target.text) || 0
    this.props.getPagination({"page": num, "size": this.state.pageSize})
    e.stopPropagation
  }
  preBtn(e) {
    this.props.getPagination({"page": this.state.pageNum-1, "size": this.state.pageSize})
    e.stopPropagation
  }
  nextBtn(e) {
    this.props.getPagination({"page": this.state.pageNum+1, "size": this.state.pageSize})
    e.stopPropagation
  }
  selectPage(e) {
    this.props.getPagination({"page": this.state.pageNum, "size": parseInt(e.target.value)})
    e.stopPropagation
  }
  render() {
    const pages = this.state.pages
    const pageNav = [] //数据条
    if(!pages){
      return null
    }
    const maxLen = 10
    let startIndex = Math.min(Math.max(this.state.pageNum-5, 1), Math.abs(this.state.pages-this.state.pageSize))
    let endIndex = Math.max(Math.min(this.state.pageNum+4, this.state.pages), maxLen)
    for(var i=startIndex; i<=endIndex; i++){
      pageNav.push(
        <li key={i} className={i == this.state.pageNum ? "active" : ""} onClick={this.pageIndexChange.bind(this)}><a href="javascript:;">{i}</a></li>
      )
    }
    //当结尾页小于页数时候出现...
    if(endIndex < this.state.pages){
      pageNav.push(
        <li key="00">...</li>
      )
    }
    return (
      <div className="progresspage">
        <label>
          总计<span>{this.state.total}</span>条&nbsp;&nbsp;
          共<span>{this.state.pages}</span>页&nbsp;&nbsp;
          <select defaultValue={this.state.pageSize} onChange={this.selectPage.bind(this)}>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="50">50</option>
            <option value="100">100</option>
            <option value="200">200</option>
            <option value="500">500</option>
            <option value="1000">1000</option>
          </select>
        </label>
        <ul>
          <a href="javascript:;" className={this.state.pageNum == 1 ? "navbtn disabled" : "navbtn"} onClick={this.preBtn.bind(this)}>上一页</a>
          {pageNav}
          <a href="javascript:;" className={this.state.pageNum == this.state.pages ? "navbtn disabled" : "navbtn"} onClick={this.nextBtn.bind(this)}>下一页</a>
        </ul>
      </div>
    )
  }
}


export default Pagination
