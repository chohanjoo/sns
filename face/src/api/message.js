const url = "http://localhost:8080/"

export function signIn(id,password){
    const body = {
        id : id,
        pw : password
    }

    return post(body);
}
export function get() {
    return fetch(url + 'post/')
}

export function post(_body) {
    return fetch(url +'login/', {
        method: 'POST',
        body: JSON.stringify(_body),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
}