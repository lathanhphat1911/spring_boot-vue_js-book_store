<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import {searchBookByTitleLike} from '../../api/bookApi'

const router = useRouter();

function goHome() {
  router.push({
    name: "home",
  });
}

const books = ref([]);
let title = ref(null);

function searchBook(){
  if (!title.value || title.value.trim() === "") {
    books.value = null;
    return;
  }
  searchBookByTitleLike(title.value)
  .then(async res => {
      books.value = res.data;
    })
    .catch(console.error);
}

function alertaa(){
  books.value = null;
}

onMounted(() =>{
  alertaa();
});

function goDetail(book) {
  router.push({
    name: "bookDetail",
    params: { id: book.id },
    state: { book } 
  });
}
</script>

<template>
    <header class="header">
      <div @click="goHome()" class="logo">BookStore</div>
      <nav class="nav">
        <a href="#">Home</a>
        <a href="#">웹툰</a>
        <a href="#">웹소설</a>
        <a href="#">책</a>
        <a href="#">바로가기</a>
      </nav>
      <div class="header-icons">
        <input class="search" type="text" placeholder="Tìm kiếm tên truyện" v-model="title" @change="searchBook()" @input="searchBook()"/>
        <div v-if="books" class="search_container">
          <div class="search_value" v-for="b, index in books" :key="b.id" @click="goDetail(b)">
              <p>{{ index + 1 }}</p>
              <div class="search_image" :style="{
                width: '4em',
                height: '5em',
                backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${b.imageUrl})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                backgroundColor: `rgba(0, 0, 0, 0.2)`,
              }">
              </div>
              <p class="text-limit">{{ b.title || b }}</p>
          </div>
        </div>
        <button @click="alertaa()" class="btn btn-warning">Tìm kiếm</button>
        <span class="icon">🛒</span>
        <span class="icon">😊</span>
      </div>
    </header>
    
</template>




<style src="../../css/style-homepage.css"></style>