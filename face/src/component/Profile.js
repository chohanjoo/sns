import React, {Component} from 'react';
import {createMuiTheme, makeStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import FolderIcon from '@material-ui/icons/Folder';
import DeleteIcon from '@material-ui/icons/Delete';
import Container from "@material-ui/core/Container";
import Bar from "./Bar";
import Button from "@material-ui/core/Button";
import ThemeProvider from "@material-ui/styles/ThemeProvider";
import withStyles from "@material-ui/core/styles/withStyles";
import {getUserList} from "../api/message";

class Profile extends Component {
    state = {
        secondary: false,
        userList: []
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
            })
    }

    render() {
        const {classes} = this.props;

        return(
            <div>
                <Bar/>
                <ThemeProvider theme={theme}>
                    <Container maxWidth="sm">
                        <div className={classes.root}>
                            <Grid container spacing={4}>
                                <Grid item xs={12} md={12}>
                                    <Typography variant="h6" className={classes.title}>
                                        친구 리스트
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
                                                        <Button className={classes.submit} variant="contained" color={"primary"}>친구 추가</Button>
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

function generate(element) {
    return [0, 1, 2].map(value =>
        React.cloneElement(element, {
            key: value,
        }),
    );
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