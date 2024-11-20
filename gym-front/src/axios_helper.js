import axios from 'axios'

// axios.defaults.baseURL = 'http://localhost:8080'
// axios.defaults.headers.post["Content-Type"] = 'application/json'

export const getAuthToken = () => {
    return window.localStorage.getItem("auth_token");
};

export const setAuthToken = (token) => {
    window.localStorage.setItem("auth_token", token);
};

export const getLoggedCoach = () => {
   
    const loggedCoach = localStorage.getItem("logged_coach");
    // console.log("getlogged: " + loggedCoach);
    return JSON.parse(loggedCoach);
};

export const setLoggedCoach = (coach) => {
    localStorage.setItem("logged_coach", JSON.stringify(coach));
};

export const isAuthenticated =() =>{
    console.log("Logovan?")
    console.log(window.localStorage.getItem("isAuthenticated"));

    return window.localStorage.getItem("isAuthenticated");
}

export const setIsAuthenticated =(isAuth) =>{
    window.localStorage.setItem("isAuthenticated", isAuth);
}

export const isOwner =() =>{
    return window.localStorage.getItem("isOwner");
}

export const setIsOwner =(isOwner) =>{
    window.localStorage.setItem("isOwner", isOwner);
}

export const request = (method,url,data) => {
    let headers = {};
    if(getAuthToken !==null && getAuthToken !=="null"){
        headers = {"Authorization": `Bearer ${getAuthToken()}`};
    }

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    });
}