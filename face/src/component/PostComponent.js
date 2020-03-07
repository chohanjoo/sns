import React, {Component} from 'react';
import clsx from 'clsx';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
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
import {getFollowingPostList, getPostList} from '../api/message';
import withStyles from "@material-ui/core/styles/withStyles";
import Bar from "./Bar";

class PostComponent extends Component {
    state = {
        expanded: false,
        postList: []
    };

    handleExpandClick = () => {
        this.setState({
            expanded: !this.state.expanded
        })
    };

    componentDidMount() {
        getFollowingPostList()
            .then(res => res.json())
            .then(data => {
                // console.log("data : ",data)
                const result = data.status;
                if(result !== 403){
                    this.setState({
                        postList: data
                    })
                }
            })
            // .catch(error => this.props.history.push("/user/login")) // TODO token 저장 실패시 오류 처리
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
            <Bar/>
            <MuiThemeProvider>
                <Container maxWidth="sm">
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
                            <CardMedia
                                className={classes.media}
                                image="/static/images/cards/paella.jpg"
                                title={post.title}
                            />
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
                                    onClick={this.handleExpandClick}
                                    aria-expanded={this.state.expanded}
                                    aria-label="show more"
                                >
                                    <ExpandMoreIcon/>
                                </IconButton>
                            </CardActions>
                            <Collapse in={this.state.expanded} timeout="auto" unmountOnExit>
                                <CardContent>
                                    <Typography paragraph>
                                        {post.contents}
                                    </Typography>
                                </CardContent>
                            </Collapse>
                        </Card>
                    ))}
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
});

export default withStyles(useStyles)(PostComponent)
