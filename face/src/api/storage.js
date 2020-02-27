export const login = (token) => {
    localStorage.setItem('token', token['data'])
};

export const getUser = () => {
    const user = localStorage.getItem('USER')
    try {
        return JSON.parse(user)
    } catch (e) {
        return null
    }
};

export const logout = () => {
    localStorage.removeItem('USER')
    localStorage.removeItem('token')
};

export const getToken = () => {
    try {
        return localStorage.getItem('token')
    } catch (e) {
        //이거 토큰없으므로 처리 해 주기
        return null
    }
};