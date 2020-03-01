import React, {Component} from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import {getUser, logout} from "../api/storage";
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import withStyles from "@material-ui/core/styles/withStyles";
import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';
import { createBrowserHistory } from 'history'

class Bar extends Component {

    state = {
        anchorEl: null,
        username: ""
    };

    history = createBrowserHistory();

    componentDidMount() {
        this.setState({
            username: getUser()
        });
    };

    handleClick = (event) => {
        this.setState({
            anchorEl: event.currentTarget
        });
    };

    handleClose = () => {
        this.setState({
            anchorEl: null
        });
    };

    logoutHandler = () => {
        this.handleClose();
        logout();
        this.setState({
            username: ""
        });
        this.history.push("/user/login");
        window.location.reload();
        // browserHistory.push("/user/login");
    };

    profileHandler = () => {
        this.handleClose();
        this.history.push("/user/profile");
        window.location.reload();
        // this.props.history.push("/user/profile");
    };

    render() {
        const {classes} = this.props;

        return (
            <div className={classes.root}>
                <ThemeProvider theme={theme}>
                <AppBar position="static" color="primary">
                    <Toolbar>
                        {/*<IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">*/}
                            {/*<MenuIcon/>*/}
                        {/*</IconButton>*/}
                        <Typography variant="h6" className={classes.title}>
                            <Button href="/" color="inherit">Message</Button>

                        </Typography>
                        {this.state.username
                            ? <Button href="" color="inherit" aria-controls="simple-menu" aria-haspopup="true" onClick={this.handleClick}>{this.state.username}</Button>
                            : <Button href="/user/login" color="inherit">Login</Button>
                        }
                        <Menu
                            id="simple-menu"
                            anchorEl={this.state.anchorEl}
                            keepMounted
                            open={Boolean(this.state.anchorEl)}
                            onClose={this.handleClose}
                        >
                            <MenuItem onClick={this.profileHandler}>Profile</MenuItem>
                            <MenuItem onClick={this.handleClose}>My account</MenuItem>
                            <MenuItem onClick={this.logoutHandler}>Logout</MenuItem>
                        </Menu>
                    </Toolbar>
                </AppBar>
                </ThemeProvider>
            </div>
        );
    }
}

const theme = createMuiTheme({
    palette: {
        primary: {
            light: '#6fbf73',
            main: '#4caf50',
            dark: '#357a38',
            contrastText: '#fff',
        },
        secondary: {
            light: '#ff7961',
            main: '#f44336',
            dark: '#ba000d',
            contrastText: '#000',
        },
    },
});

const useStyles = theme => ({
    root      : {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title     : {
        flexGrow: 1,
    },
});

export default
    withStyles(useStyles)(Bar);