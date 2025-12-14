import axios from "axios";

const api = axios.create({
  baseURL: ""
});

api.interceptors.response.use(
  res => res,
  err => {
    return Promise.reject(err)
  }
)

api.interceptors.request.use(config => {
  const token = localStorage.getItem("jwt");

   if (token && token !== "undefined" && !config.url.includes("/api/authenticate")) {
    config.headers.Authorization = `Bearer ${token}`;
  } else {
    delete config.headers.Authorization;
  }

  return config;
});


export default api;
