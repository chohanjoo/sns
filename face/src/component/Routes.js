import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import SignIn from './SignIn';
import SignUp from "./SignUp";
import {PrivateRoute} from "./PrivateRoute";
import PostComponent from "./PostComponent";
import Bar from "./Bar";

export default () => (
    <Router>
        <Route path="/user/login" exact component={SignIn}/>
        <Route path="/user/signup" component={SignUp}/>
        <PrivateRoute path='/' exact component={PostComponent} />
    </Router>
)