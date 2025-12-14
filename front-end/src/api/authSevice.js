import axios from "axios";

const AUTH_URL = "http://localhost:8080/api/authenticate";

export async function login(username, password) {
  const response = await axios.post(
    AUTH_URL,
    {},
    {
      auth: {
        username,
        password,
      },
    }
  );

  const token = response.data.token;
  localStorage.setItem("jwt", token);
  return token;
}

export function logout() {
  localStorage.removeItem("jwt");
}

export function getToken() {
  return localStorage.getItem("jwt");
}
