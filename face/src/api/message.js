import {getToken} from "./storage";

const url = "http://localhost:8080";

export function getUserList() {
    const header = {
        'X-AUTH-TOKEN': getToken()
    };

    return get(header,'/users')
}

export function getPostList() {
    const header = {
        'X-AUTH-TOKEN': getToken()
    };

    return get(header,'/post')
}

export function signUp(body) {

    return post("/v1/signup", body);
}

export function signIn(id, password) {
    const body = {
        username: id,
        password: password
    };

    return post("/v1/signin", body);
}

export function get(header, path) {
    return fetch(url + path, {
        headers: header
    })
}

export function post(path, body) {
    console.log(url + path);
    return fetch(url + path, {
        method : 'POST',
        body   : JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    })
}