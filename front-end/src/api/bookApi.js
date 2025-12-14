import axios from "axios";
import api from "./interceptor";

const API_BASE = "http://localhost:8080/api/store";

export function getAllBooks() {
    return api.get(API_BASE);
}

export function getBookByTitle(title) {
    return api.get(`${API_BASE}/${title}`);
}

export function getBookByTop(topNumber) {
    return api.get(`${API_BASE}/top/${topNumber}`);
}

export function getTagsByTop(topNumber) {
    return api.get(`${API_BASE}/top/tags/${topNumber}`);
}

export function getBooksByTopMinMax(min, max) {
    return api.get(`${API_BASE}/top/${min}/${max}`);
}

export function getImageUrlByGenre(name) {
    return api.get(`${API_BASE}/top/tags/image/${name}`);
}

export function getBookById(id) {
    return api.get(`${API_BASE}/books/${id}`);
}

export function getTagByBookId(id) {
    return api.get(`${API_BASE}/tags/by/${id}`);
}

export function getChapterByBookId(id) {
    return api.get(`${API_BASE}/chapter/by/${id}`);
}

export function getBookFromSameAuthor(id) {
    return api.get(`${API_BASE}/book/author/${id}`);
}

export function getChapterById(id) {
    return api.get(`${API_BASE}/book/chapter/${id}`);
}

export function getChapterFromSameBookById(id) {
    return api.get(`${API_BASE}/book/chapter/byBook/${id}`);
}

export function getNextChapterById(id){
    return api.get(`${API_BASE}/book/chapter/next/${id}`);
}

export function getPreviousChapterById(id){
    return api.get(`${API_BASE}/book/chapter/previous/${id}`);
}

export function getBookByTag(id){
    return api.get(`${API_BASE}/books/tags/${id}`);
}

export function searchBookByTitleLike(title){
    return api.get(`${API_BASE}/book/search/${title}`);
}

export function scrapeChapterContent(id){
    return api.get(`${API_BASE}/scrape/${id}`);
}

export function checkOwnership(id){
    return api.get(`http://localhost:8080/api/ownership/${id}`)
}

export async function login(username, password) {
  const response = await axios.post(
    "http://localhost:8080/api/authenticate",
    {},
    {
      auth: {
        username,
        password
      }
    }
  );

  const token = response.data.token;

  // Lưu token
  localStorage.setItem("jwt", token);

  return token;
}