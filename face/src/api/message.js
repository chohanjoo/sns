import {getToken, getUser} from "./storage";

const auth_server_url = "http://localhost:8000/api";
const user_server_url = "http://localhost:8000/api";
const post_server__url = "http://localhost:8000/api";

export function getRecommendFriendList(){
    const path = "/user/friend/recommend" + "?user_id=" + getUser();

    return getMethod(user_server(path), getHeader);
}

export function deleteFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return deleteMethod(user_server("/user/friend"), body, postHeaderInclToken);
}

export function createFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return postMethod(user_server("/user/friend"), body, postHeaderInclToken);
}

export function getUserFriendList() {
    const path = '/user/friend' + '?user_id='+ getUser();

    return getMethod(user_server(path),getHeader);
}

export function getUserList() {
    return getMethod(user_server('/user/all'),getHeader);
}

export function getFollowingPostList() {
    const body = {
        user_id: getUser()
    };

    return postMethod(post_server('/post'),body,postHeaderInclToken);
}

export function getPostList() {
    return getMethod(post_server('/post'),getHeader);
}

export function signUp(body) {
    return postMethod(auth_server("/auth/signup"), body,postHeaderExclToken);
}

export function signIn(id, password) {
    const body = {
        username: id,
        password: password
    };

    return postMethod(auth_server("/auth/signin"), body,postHeaderExclToken);
}

function auth_server(path) {
    return auth_server_url + path;
}

function user_server(path) {
    return user_server_url + path;
}

function post_server(path) {
    return post_server__url + path;
}

function getMethod(url,header) {
    return fetch(url, {
        headers: header
    })
}

function postMethod(url, body,header) {
    return fetch(url, {
        method : 'POST',
        body   : JSON.stringify(body),
        headers: header
    })
}

function deleteMethod(url, body,header) {
    return fetch(url, {
        method : 'DELETE',
        body   : JSON.stringify(body),
        headers: header
    })
}

const postHeaderInclToken = {
    'X-AUTH-TOKEN': getToken(),
    'Content-Type': 'application/json'
};

const postHeaderExclToken = {
    'Content-Type': 'application/json'
};

const getHeader = {
    'X-AUTH-TOKEN': getToken()
};