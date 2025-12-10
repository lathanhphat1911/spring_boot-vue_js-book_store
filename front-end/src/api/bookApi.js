import axios from "axios";

const API_BASE = "http://localhost:8080/api/store";

export function getAllBooks() {
    return axios.get(API_BASE);
}

export function getBookByTitle(title) {
    return axios.get(`${API_BASE}/${title}`);
}

export function getBookByTop(topNumber) {
    return axios.get(`${API_BASE}/top/${topNumber}`);
}

export function getTagsByTop(topNumber) {
    return axios.get(`${API_BASE}/top/tags/${topNumber}`);
}

export function getBooksByTopMinMax(min, max) {
    return axios.get(`${API_BASE}/top/${min}/${max}`);
}

export function getImageUrlByGenre(name) {
    return axios.get(`${API_BASE}/top/tags/image/${name}`);
}

export function getBookById(id) {
    return axios.get(`${API_BASE}/books/${id}`);
}

export function getTagByBookId(id) {
    return axios.get(`${API_BASE}/tags/by/${id}`);
}

export function getChapterByBookId(id) {
    return axios.get(`${API_BASE}/chapter/by/${id}`);
}

export function getBookFromSameAuthor(id) {
    return axios.get(`${API_BASE}/book/author/${id}`);
}

export function getChapterById(id) {
    return axios.get(`${API_BASE}/book/chapter/${id}`);
}

export function getChapterFromSameBookById(id) {
    return axios.get(`${API_BASE}/book/chapter/byBook/${id}`);
}

export function getNextChapterById(id){
    return axios.get(`${API_BASE}/book/chapter/next/${id}`);
}

export function getPreviousChapterById(id){
    return axios.get(`${API_BASE}/book/chapter/previous/${id}`);
}

export function getBookByTag(id){
    return axios.get(`${API_BASE}/books/tags/${id}`);
}

export function searchBookByTitleLike(title){
    return axios.get(`${API_BASE}/book/search/${title}`);
}

export function scrapeChapterContent(id){
    return axios.get(`${API_BASE}/scrape/${id}`);
}