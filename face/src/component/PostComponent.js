import React, {Component} from 'react';
import clsx from 'clsx';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import {red} from '@material-ui/core/colors';
import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import Container from '@material-ui/core/Container';
import {getFollowingPostList, getRecommendFriendList, getUserFriendList} from '../api/message';
import withStyles from "@material-ui/core/styles/withStyles";
import Bar from "./Bar";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import Icon from '@material-ui/core/Icon';
import { green } from '@material-ui/core/colors';

class PostComponent extends Component {
    state = {
        expanded: false,
        expanded_id: "",
        postList: [],
        friendList: [],
        recommendUserList: []
    };

    handleExpandClick = (index,e) => {
        this.setState({
            expanded: !this.state.expanded,
            expanded_id: index
        })
    };

    componentDidMount() {
        getFollowingPostList()
            .then(res => res.json())
            .then(data => {
                const result = data.status;
                if(result !== 403){
                    this.setState({
                        postList: data
                    })
                }
            });
        this.getUserFriends();
        this.getRecommendFriends()
            // .catch(error => this.props.history.push("/user/login")) // TODO token 저장 실패시 오류 처리
    }

    getUserFriends(){
        getUserFriendList()
            .then(res => res.json())
            .then(data => {
                const result = data.status;
                if(result !== 403){
                    this.setState({
                        friendList: data.list
                    })
                }
            })
    }

    getRecommendFriends(){
        getRecommendFriendList()
            .then(response => {
                const result = response.status;
                if(result === 200){
                    response.json()
                        .then(users => {
                            this.setState({
                                recommendUserList: users['list']
                            })
                        })
                }
            });
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
            <Bar/>
            <MuiThemeProvider>
                <Container maxWidth="md">
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={8}>
                    {this.state.postList.map((post, index) => (
                        <Card className={classes.root} key={index}>
                            <CardHeader
                                avatar={
                                    <Avatar aria-label="recipe" className={classes.avatar}>
                                        R
                                    </Avatar>
                                }
                                action={
                                    <IconButton aria-label="settings">
                                        <MoreVertIcon/>
                                    </IconButton>
                                }
                                title={post.writer}
                                subheader={post.update_date}
                            />
                            {/*<CardMedia*/}
                                {/*className={classes.media}*/}
                                {/*image="/static/images/cards/paella.jpg"*/}
                                {/*title={post.title}*/}
                            {/*/>*/}
                            <CardContent>
                                <Typography variant="body2" color="textSecondary" component="p">
                                    {post.contents}
                                </Typography>
                            </CardContent>
                            <CardActions disableSpacing>
                                <IconButton aria-label="add to favorites">
                                    <FavoriteIcon/>
                                </IconButton>
                                <IconButton aria-label="share">
                                    <ShareIcon/>
                                </IconButton>
                                <IconButton
                                    className={clsx(classes.expand, {
                                        [classes.expandOpen]: this.state.expanded,
                                    })}
                                    onClick={(e) =>this.handleExpandClick(index,e)}
                                    aria-expanded={this.state.expanded}
                                    aria-label="show more"
                                >
                                    <ExpandMoreIcon/>
                                </IconButton>
                            </CardActions>
                            {this.state.expanded_id === index ? <Collapse in={this.state.expanded} timeout="auto" unmountOnExit>
                                <CardContent>
                                    <Typography paragraph>
                                        {post.contents}
                                    </Typography>
                                </CardContent>
                            </Collapse>: <div></div>}
                        </Card>
                    ))}
                        </Grid>

                        <Grid item xs={6} md={4}>
                            <div className={classes.sidebar}>
                                <div className={classes.scroll}>
                            <Card>
                            {this.state.friendList.map((friend, index) => (
                                    <CardHeader
                                        avatar={
                                            <Avatar className={classes.small} src="/broken-image.jpg"/>
                                        }
                                        title={friend.friend_id}
                                        key={index}
                                        className={classes.sideContext}
                                    />
                            ))}
                            </Card>
                                </div>
                                <br/>
                                <div className={classes.scroll}>
                                <Card>
                                    {this.state.recommendUserList.map((friend, index) => (
                                        <CardHeader
                                            avatar={
                                                <Avatar className={classes.small} src="/broken-image.jpg"/>
                                            }
                                            action={
                                                <Button className={classes.sideButton}><Icon style={{ color: green[500] }}>add_circle</Icon></Button>
                                            }
                                            title={friend.friend_id}
                                            key={index}
                                            className={classes.sideContext}
                                        />
                                    ))}
                                </Card>
                                </div>
                            </div>
                        </Grid>
                    </Grid>
                </Container>
            </MuiThemeProvider>
            </div>
        );
    }
}

const useStyles = theme => ({
    root      : {
        maxWidth : 500,
        margin   : 'auto',
        marginTop: '5%'
    },
    media     : {
        height    : 0,
        paddingTop: '56.25%', // 16:9
    },
    expand    : {
        transform : 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
        }),
    },
    expandOpen: {
        transform: 'rotate(180deg)',
    },
    avatar    : {
        backgroundColor: red[500],
    },
    sidebar:{
        position: 'sticky',
        top: '10%',
    },
    sideContext: {
        padding: '1.5%',
    },
    sideButton: {
        marginTop: '15%'
    },
    small: {
        width: theme.spacing(4),
        height: theme.spacing(4),
    },
    scroll: {
        maxHeight: '250px',
        overflowY: 'auto',
        boxShadow: '0px 5px 15px rgba(0, 0, 0, .3)',
        borderRadius: '10px'
    }
});

export default withStyles(useStyles)(PostComponent)
