const url = "http://localhost:8080/"

export function getPostList(){

    return get()
}
export function signIn(id,password){
    const body = {
        username : id,
        password : password
    };

    return post("v1/signin",body);
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
            'Content-Type': 'application/json'
        }
    })
}