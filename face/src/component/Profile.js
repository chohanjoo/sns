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
import {createFriend, getUserFriendList, getUserList} from "../api/message";

class Profile extends Component {
    state = {
        secondary: false,
        userList: [],
        userFriendList: []
    };

    componentDidMount() {
        getUserList()
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

        this.getUserFriends();

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
        console.log(friend_id);
        createFriend(friend_id)
            .then(response => {
                const result = response.status;
                if(result === 201){
                    this.getUserFriends();
                }
            })
    }

    render() {
        const {classes} = this.props;

        return(
            <div>
                <Bar/>
                <ThemeProvider theme={theme}>
                    <Container maxWidth="md">
                        <div className={classes.root}>
                            <Grid container spacing={4}>
                                <Grid item xs={12} md={6}>
                                    <Typography variant="h6" className={classes.title}>
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
                                                        <Button variant="contained" color={"secondary"}>삭제</Button>
                                                    </ListItemSecondaryAction>
                                                </ListItem>
                                            ))}
                                        </List>
                                    </div>
                                </Grid>
                                <Grid item xs={12} md={6}>
                                    <Typography variant="h6" className={classes.title}>
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
                                                        primary={user.id}
                                                        secondary={this.state.secondary ? 'Secondary text' : null}
                                                    />
                                                    <ListItemSecondaryAction>
                                                        <Button onClick={() => this.createUserFriend(user.id)} className={classes.submit} variant="contained" color={"primary"}>친구 추가</Button>
                                                        <Button variant="contained" color={"secondary"}>삭제</Button>
                                                    </ListItemSecondaryAction>
                                                </ListItem>
                                            ))}
                                        </List>
                                    </div>
                                </Grid>
                            </Grid>
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
        maxWidth: 752,
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