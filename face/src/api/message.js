const url = "http://localhost:8080/"

export function getPostList(){

    return get()
}
export function signIn(id,password){
    const body = {
        username : id,
        password : password
    };

    return post("user/login",body);
}
export function get() {
    return fetch(url + 'post/')
}

export function post(path,body) {
    console.log(url+path);
    return fetch(url + path, {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
}