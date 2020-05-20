import {getToken, getUser} from "./storage";

const server_url = "http://localhost:8000/api";

export function deletePostLike(postId) {
    const body = {
        user_id: getUser(),
        post_id: postId
    };

    return deleteMethod(gateway_server("/post/like"), body, postHeaderInclToken);
}
export function getPostLikes() {
    const path = "/post/like?userId=" + getUser();
    return getMethod(gateway_server(path), getHeader);
}

export function createPostLike(postId) {
    const body = {
        user_id: getUser(),
        post_id: postId,
        is_love: true
    };

    return postMethod(gateway_server("/post/like"), body, postHeaderInclToken);
}

export function getRecommendFriendList(){
    const path = "/user/friend/recommend" + "?user_id=" + getUser();

    return getMethod(gateway_server(path), getHeader);
}

export function deleteFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return deleteMethod(gateway_server("/user/friend"), body, postHeaderInclToken);
}

export function createFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return postMethod(gateway_server("/user/friend"), body, postHeaderInclToken);
}

export function getUserFriendList() {
    const path = '/user/friend' + '?user_id='+ getUser();

    return getMethod(gateway_server(path),getHeader);
}

export function getUserList() {
    return getMethod(gateway_server('/user/all'),getHeader);
}

export function getFollowingPostList() {
    const body = {
        user_id: getUser()
    };

    return postMethod(gateway_server('/post'),body,postHeaderInclToken);
}

export function getPostList() {
    return getMethod(gateway_server('/post'),getHeader);
}

export function signUp(body) {
    return postMethod(gateway_server("/auth/signup"), body,postHeaderExclToken);
}

export function signIn(id, password) {
    const body = {
        username: id,
        password: password
    };

    return postMethod(gateway_server("/auth/signin"), body,postHeaderExclToken);
}

function gateway_server(path) {
    return server_url + path;
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