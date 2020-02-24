import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import Container from '@material-ui/core/Container';
import { get } from '../api/message';

class PostComponent extends Component {
  state = {
    postList : []
  }

  componentWillMount(){
    get()
    .then(res => res.json())
    .then(data => 
      this.setState({
        postList : data
      })
      )
  }

  createPostComponent(){
    return (
      this.state.postList.map(post => (
        <Card>
          <CardHeader
            title={post.title}
            subtitle="Subtitle"
            actAsExpander={true}
            showExpandableButton={true}
          />
          <CardActions>
            <FlatButton label="Action1" />
            <FlatButton label="Action2" />
          </CardActions>
          <CardText expandable={true}>
            {post.contents}
          </CardText>
        </Card>
    )))
  }

  render(){
    // console.log(this.state.postList);
    return(
      <MuiThemeProvider>
        <Container maxWidth="100%">
        {this.createPostComponent()}
  </Container>
  </MuiThemeProvider>
    );
  }
}


export default PostComponent;