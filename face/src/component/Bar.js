import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import {getUser, logout} from "../api/storage";
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import {Redirect, useHistory, withRouter} from "react-router-dom";
import withStyles from "@material-ui/core/styles/withStyles";
import { createHashHistory } from "history";

import { createMuiTheme } from '@material-ui/core/styles';
import { ThemeProvider } from '@material-ui/styles';

class Bar extends Component {

    state = {
        anchorEl: null,
        username: ""
    };

    history = createHashHistory();

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
                            <MenuItem onClick={this.handleClose}>Profile</MenuItem>
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