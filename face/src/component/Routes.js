import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import CardComponent from './PostComponent';
import SignIn from './SignIn';
import SignUp from "./SignUp";

export default () => (
    <Router>
        <Route path="/post" component={CardComponent}/>
        <Route path="/user/login" component={SignIn}/>
        <Route path="/user/signup" component={SignUp}/>
    </Router>
)