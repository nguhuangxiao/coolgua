import React from 'react'
import './tab.less'

class Tab extends React.Component {
  constructor(props, context) {
    super(props, context)
    this.state = {
      currentIndex: 0
    }
  }
  render() {
    const data = this.props.tab.slice(0).split(',')
    return (
      <div className="task-nav">
        <ul>
          {
            data.map((item, index) => (
              <li key={index} className={index === this.state.currentIndex ? "active" : ""} onClick={this.selectItem.bind(this, index)} >{item}</li>
            ))
          }
          <div className="task-line"></div>
        </ul>
      </div>
    )
  }
  selectItem(index) {
    this.setState({currentIndex: index}, ()=> {
      this.props.selectItem(index)
    })
  }
}
export default Tab
