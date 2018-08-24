import React from 'react'
import { Route, Switch, Link } from 'react-router-dom'

import activity from 'containers/activity/activity'
import NotFoundPage from 'containers/404/404'


// 按路由拆分代码
import Loadable from 'react-loadable';
const loadingComponent = ({ isLoading, error }) => {
  // Handle the loading state
  if (isLoading) {
      return null;
  }
  // Handle the error state
  else if (error) {
      return <div>Sorry, there was a problem loading the page.</div>;
  }
  else {
      return null;
  }
};

const ActivityAdd = Loadable({
  loader: () => import('containers/activity_add/activity_add'),
  loading: loadingComponent
});

const ActivityUpdate = Loadable({
  loader: () => import('containers/activity_update/activity_update'),
  loading: loadingComponent
})

const entrance = Loadable({
  loader: () => import('containers/entrance/entrance'),
  loading: loadingComponent
})

const statistics = Loadable({
  loader: () => import('containers/statistics/statistics'),
  loading: loadingComponent
})

const signlist = Loadable({
  loader: () => import('containers/signlist/signlist'),
  loading: loadingComponent
})

const galaxy = Loadable({
  loader: () => import('containers/galaxy/galaxy'),
  loading: loadingComponent
})

const galaxydetail = Loadable({
  loader: () => import('containers/galaxydetail/galaxydetail'),
  loading: loadingComponent
})

const signdetail = Loadable({
  loader: () => import('containers/signdetail/signdetail'),
  loading: loadingComponent
})


class Wrapper extends React.Component {
  constructor(props, context) {
    super(props, context)
  }
  render() {
    return (
      <div>
        <Switch>
          <Route exact path='/' component={activity}/>
          <Route exact path='/activity/add' component={ActivityAdd}/>
          <Route path='/entrance/:id' component={entrance}/>
          <Route path='/activity/update/:id' component={ActivityUpdate}/>
          <Route path='/statistics/:id' component={statistics}/>
          <Route path='/signlist/:id' component={signlist}/>
          <Route exact path='/galaxy/:id' component={galaxy}/>
          <Route path='/galaxy/:id/:openId' component={galaxydetail}/>
          <Route path='/signdetail/:id/:openId' component={signdetail}/>
          <Route path="*" component={NotFoundPage} />
        </Switch>
      </div>
    )
  }
}


export default Wrapper
