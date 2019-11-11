import React from 'react';
import { BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import About from './About'; 
import Home from './Home'; 
import Blog from './Blog'; 
import User from './User'; 
import SignUp from './SignUp';
import SignIn from './SingIn';

function App() {
  return (
    <Router>
      <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/about" component={About} />
          <Route path="/blog" component={Blog} />
          <Route path="/createpost" component={User} />
          <Route path="/posts" component={User} />
          <Route path="/user" component={User} />
          <Route path="/signup" component={SignUp} />
          <Route path="/signin" component={SignIn} />
        </Switch>
    </Router>
  );
}

export default App;
