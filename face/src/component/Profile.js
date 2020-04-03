import React, {Component} from 'react';
import {createMuiTheme} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import FolderIcon from '@material-ui/icons/Folder';
import Container from "@material-ui/core/Container";
import Bar from "./Bar";
import Button from "@material-ui/core/Button";
import ThemeProvider from "@material-ui/styles/ThemeProvider";
import withStyles from "@material-ui/core/styles/withStyles";
import {createFriend, deleteFriend, getRecommendFriendList, getUserFriendList} from "../api/message";
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import {getUser} from "../api/storage";
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import RestoreIcon from '@material-ui/icons/Restore';
import FavoriteIcon from '@material-ui/icons/Favorite';
import PeopleIcon from '@material-ui/icons/People';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

class Profile extends Component {

    constructor(props){
        super(props);

        this.snackbarHandler = this.snackbarHandler.bind(this);
    }

    state = {
        secondary: false,
        userList: [],
        userFriendList: [],
        open: false,
        message: "",
        severity: "",
        value: "recents"
    };

    componentDidMount() {
        this.updateUserProfile();
    }

    updateUserProfile(){
        this.getUserFriends();
        this.getRecommendFriends();
    }

    getRecommendFriends(){
        getRecommendFriendList()
            .then(response => {
                const result = response.status;
                if(result === 200){
                    response.json()
                        .then(users => {
                            this.setState({
                                userList: users['list']
                            })
                        })
                }
            });
    }

    getUserFriends(){
        getUserFriendList()
            .then(response => {
                const result = response.status;
                if(result === 200){
                    response.json()
                        .then(users => {
                            this.setState({
                                userFriendList: users['list']
                            })
                        })
                }
            })
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
                    this.updateUserProfile();
                } else{
                    this.setState({
                        message: "친구 추가를 실패했습니다.",
                        severity: "error"
                    },()=>this.snackbarHandler());
                }
            })
    }

    deleteUserFriend(friend_id){
        deleteFriend(friend_id)
            .then(response => {
                const result = response.status;
                if(result === 200){
                    this.setState({
                        message: "친구 삭제를 완료했습니다.",
                        severity: "success"
                    },()=>this.snackbarHandler());
                    this.updateUserProfile();
                }else{
                    this.setState({
                        message: "친구 삭를 실패했습니다.",
                        severity: "error"
                    },()=>this.snackbarHandler());
                }
            })
    }

    snackbarHandler(){
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

    handleChange = (event, newValue) => {
        this.setState({
            value: newValue
        })
    };


    render() {
        const {classes} = this.props;
        const {value} = this.state;

        return(
            <div>
                <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
                <Bar/>
                {this.snackbar()}
                <ThemeProvider theme={theme}>
                    <Container maxWidth="md">
                        <div className={classes.root}>
                            <div>
                                <Grid container spacing={4}>
                                    <Grid item xs={12} md={4}>
                            <Avatar src="/broken-image.jpg" className={classes.large} />
                                    </Grid>
                                    <Grid item xs={12} md={8}>
                                        <Typography variant="h4" gutterBottom className={classes.section}>
                                            {getUser()}
                                        </Typography>
                                        <Typography variant="h6" gutterBottom className={classes.section}>
                                            게시글 &nbsp; &nbsp; 친구
                                        </Typography>
                                    </Grid>
                                </Grid>
                            </div>
                            <BottomNavigation value={value} onChange={this.handleChange} className={classes.navigation}>
                                <BottomNavigationAction label="Recents" value="recents" icon={<RestoreIcon />} />
                                <BottomNavigationAction label="Favorites" value="favorites" icon={<FavoriteIcon />} />
                                <BottomNavigationAction label="Friends" value="friends" icon={<PeopleIcon />} />
                                <BottomNavigationAction label="Folder" value="folder" icon={<FolderIcon />} />
                            </BottomNavigation>
                            {this.state.value === 'friends' ?                             <Grid container spacing={4}>
                                <Grid item xs={12} md={6}>
                                    <Typography variant="h5" className={classes.title}>
                                        친구 리스트
                                    </Typography>
                                    <div className={classes.demo}>
                                        <List>
                                            {this.state.userFriendList.map( (user,index) => (
                                                <ListItem key={index}>
                                                    <ListItemAvatar>
                                                        <Avatar>
                                                            <FolderIcon />
                                                        </Avatar>
                                                    </ListItemAvatar>
                                                    <ListItemText
                                                        primary={user.friend_id}
                                                        secondary={this.state.secondary ? 'Secondary text' : null}
                                                    />
                                                    <ListItemSecondaryAction>
                                                        <Button onClick={() => this.deleteUserFriend(user.friend_id)} variant="contained" color={"secondary"}>삭제</Button>
                                                    </ListItemSecondaryAction>
                                                </ListItem>
                                            ))}
                                        </List>
                                    </div>
                                </Grid>
                                <Grid item xs={12} md={6}>
                                    <Typography variant="h5" className={classes.title}>
                                        추천 친구 리스트
                                    </Typography>
                                    <div className={classes.demo}>
                                        <List>
                                            {this.state.userList.map( (user,index) => (
                                                <ListItem key={index}>
                                                    <ListItemAvatar>
                                                        <Avatar>
                                                            <FolderIcon />
                                                        </Avatar>
                                                    </ListItemAvatar>
                                                    <ListItemText
                                                        primary={user.friend_id}
                                                        secondary={this.state.secondary ? 'Secondary text' : null}
                                                    />
                                                    <ListItemSecondaryAction>
                                                        <Button onClick={() => this.createUserFriend(user.friend_id)} className={classes.submit} variant="contained" color={"primary"}>친구 추가</Button>
                                                        <Button variant="contained" color={"secondary"}>삭제</Button>
                                                    </ListItemSecondaryAction>
                                                </ListItem>
                                            ))}
                                        </List>
                                    </div>
                                </Grid>
                            </Grid> : <div></div>}
                        </div>
                    </Container>
                </ThemeProvider>
            </div>
        );
    }
}

const useStyles = theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 952,
    },
    demo: {
        backgroundColor: theme.palette.background.paper,
    },
    title: {
        margin: theme.spacing(4, 0, 2),
    },
    submit: {
        margin: theme.spacing(0, 1, 0),
    },
    large: {
        marginTop: "10%",
        width: theme.spacing(15),
        height: theme.spacing(15),
    },
    section: {
        marginTop: "5%"
    },
    navigation: {
        flexGrow: 1,
        maxWidth: 952,
        marginTop:"10%"
    }
});

const theme = createMuiTheme({
    palette: {
        primary: {
            light: '#6fbf73',
            main: '#4caf50',
            dark: '#357a38',
            contrastText: '#fff',
        },
        secondary: {
            light: '#a2cf6e',
            main: '#8bc34a',
            dark: '#618833',
            contrastText: '#fff',
        },
    },
});

export default withStyles(useStyles)(Profile);