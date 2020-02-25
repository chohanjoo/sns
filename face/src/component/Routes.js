import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import PostComponent from './PostComponent';
import CardComponent from './CardComponent';
import SignIn from './SignIn';
// App.js에 있던 Aladin, LionKing, SpiderMan을
// Components/Routes.js 로 이동
export default () => (
  <Router>
    <Route path="/post" component={CardComponent} />
    <Route path="/user/login" component={SignIn} />
  </Router>
)