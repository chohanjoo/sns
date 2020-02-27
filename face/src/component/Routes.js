import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import CardComponent from './PostComponent';
import SignIn from './SignIn';

export default () => (
    <Router>
        <Route path="/post" component={CardComponent}/>
        <Route path="/user/login" component={SignIn}/>
    </Router>
)