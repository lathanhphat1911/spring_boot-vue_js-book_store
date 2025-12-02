<script>
import { getAllBooks, getBookByTitle } from "../api/bookApi";

export default {
  data() {
    return {
      books: [],
      booksFind: [],
      title: ""
    };
  },
  created() {
    getAllBooks()
      .then(res => {
        this.books = res.data;
      })
      .catch(err => console.error(err));
  },
  methods: {
    searchBook() {
        getBookByTitle(this.title)
            .then(res => {
                this.booksFind = res.data;
            })
            .catch(err => console.error(err))
    }
  }
};

</script>

<template>
<div>
    <h1>Book List</h1>
    <input type="text" class="form-control" v-model="title">
    <button @click="searchBook" class="btn btn-success">Search</button>

    <div v-for="b in booksFind" :key="b.id">
      <h3>{{ b.title }}</h3>
      <p>Author: {{ b.authorName }}</p>
      <p>Chapters: {{ b.chapterCount }}</p>
    </div>


  </div>
</template>