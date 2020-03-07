import {getToken, getUser} from "./storage";

const url = "http://localhost:8080";

export function getRecommendFriendList(){
    const path = "/user/friend/recommend" + "?user_id=" + getUser();

    return get(path, getHeader)
}

export function deleteFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return deleteMethod("/user/friend", body, postHeaderInclToken)
}

export function createFriend(friend_id) {
    const body = {
        user_id: getUser(),
        friend_id: friend_id
    };

    return post("/user/friend", body, postHeaderInclToken)
}

export function getUserFriendList() {
    const path = '/user/friend' + '?user_id='+ getUser();

    return get(path,getHeader)
}

export function getUserList() {
    return get('/users',getHeader)
}

export function getFollowingPostList() {
    const body = {
        user_id: getUser()
    };

    return post('/post',body,postHeaderInclToken);
}

export function getPostList() {
    return get('/post',getHeader)
}

export function signUp(body) {
    return post("/v1/signup", body,postHeaderExclToken);
}

export function signIn(id, password) {
    const body = {
        username: id,
        password: password
    };

    return post("/v1/signin", body,postHeaderExclToken);
}

function get(path,header) {
    return fetch(url + path, {
        headers: header
    })
}

function post(path, body,header) {
    console.log(url + path);
    return fetch(url + path, {
        method : 'POST',
        body   : JSON.stringify(body),
        headers: header
    })
}

function deleteMethod(path, body,header) {
    console.log(url + path);
    return fetch(url + path, {
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