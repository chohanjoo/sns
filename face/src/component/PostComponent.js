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
import {
    createFriend,
    createPostLike,
    deletePostLike,
    getFollowingPostList,
    getRecommendFriendList,
    getUserFriendList
} from '../api/message';
import withStyles from "@material-ui/core/styles/withStyles";
import Bar from "./Bar";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import Icon from '@material-ui/core/Icon';
import { green } from '@material-ui/core/colors';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import {getToken, logout} from "../api/storage";

var jwtDecode = require('jwt-decode');
class PostComponent extends Component {
    state = {
        expanded: false,
        expanded_id: "",
        postList: [],
        friendList: [],
        recommendUserList: [],
        items: 5,
        preItems: 0,
        heart: false,
        open: false
    };

    server_url = "api";

    handleExpandClick = (index,e) => {
        this.setState({
            expanded: !this.state.expanded,
            expanded_id: index
        })
    };

    componentDidMount() {
        this.checkJwtExp();
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
        window.addEventListener('scroll',this.infiniteScroll,true);
            // .catch(error => this.props.history.push("/user/login")) // TODO token 저장 실패시 오류 처리
    }

    checkJwtExp(){
        const date = new Date();
        try{
            const lens = date.getTime().toString().length - jwtDecode(getToken())['exp'].toString().length;
            if(date.getTime()/Math.pow(10,lens) > jwtDecode(getToken())['exp'] ){
                logout();
                this.props.history.push("/user/login")
            }
        }catch (e) {
            this.props.history.push("/user/login")
        }
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

    createUserFriend(friend_id){
        createFriend(friend_id)
            .then(response => {
                const result = response.status;
                if(result === 201){
                    this.setState({
                        message: "친구 추가를 완료했습니다.",
                        severity: "success"
                    },()=>this.snackbarHandler());
                    this.getRecommendFriends();
                } else{
                    this.setState({
                        message: "친구 추가를 실패했습니다.",
                        severity: "error"
                    },()=>this.snackbarHandler());
                }
            })
    }

    infiniteScroll = () => {
        let scrollHeight = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);

        let scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);

        let clientHeight = document.documentElement.clientHeight;

        if(scrollTop + clientHeight === scrollHeight) {
            this.setState({
                // preItems: this.state.items,
                items: this.state.items + 5
            });
        }
    };

    createPostLike = (postId) => {
        createPostLike(postId);
        this.componentDidMount();
    };

    deletePostLike = (postId) => {
        deletePostLike(postId);
        this.componentDidMount();
    };

    snackbarHandler = (props) => {
        this.setState({
            open: !this.state.open
        })
    }

    snackbar(){
        return(
            <Snackbar open={this.state.open} autoHideDuration={3000} onClose={this.snackbarHandler}>
                <Alert onClose={this.snackbarHandler} severity={this.state.severity}>
                    {this.state.message}
                </Alert>
            </Snackbar>
        );
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
            <Bar/>
            {this.snackbar()}
            <MuiThemeProvider>
                <Container maxWidth="md">
                    <Grid container spacing={2}>
                        <Grid item xs={12} md={8}>
                    {this.state.postList.slice(this.state.preItems,this.state.items).map((post, index) => (
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
                                {post.like ?
                                    <IconButton aria-label="add to favorites" onClick={() => this.deletePostLike(post.id)}>
                                        {/*{post.love === 0 ? <FavoriteBorderIcon/> : <FavoriteIcon/>}*/}
                                        <FavoriteIcon style={{ color: red[500] }}/>
                                    </IconButton> :
                                    <IconButton aria-label="add to favorites" onClick={() => this.createPostLike(post.id)}>
                                        {/*{post.love === 0 ? <FavoriteBorderIcon/> : <FavoriteIcon/>}*/}
                                        <FavoriteBorderIcon/>
                                    </IconButton>}

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

                        {window.innerWidth > 960 ?
                        <Grid item xs={6} md={4}>
                            <div className={classes.sidebar}>
                                <div className={classes.scroll}>
                            <Card>
                            {this.state.friendList.slice(0,10).map((friend, index) => (
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
                                    {this.state.recommendUserList.slice(0,10).map((friend, index) => (
                                        <CardHeader
                                            avatar={
                                                <Avatar className={classes.small} src="/broken-image.jpg"/>
                                            }
                                            action={
                                                <Button className={classes.sideButton} onClick={() => this.createUserFriend(friend.friend_id)}><Icon style={{ color: green[500] }}>add_circle</Icon></Button>
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
                            :<div></div> }
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
