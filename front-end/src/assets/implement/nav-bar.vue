<template>
    <header class="header">
      <div @click="goHome()" class="logo">BookStore</div>
      <nav class="nav">
        <a href="#">추천</a>
        <a href="#">웹툰</a>
        <a href="#">웹소설</a>
        <a href="#">책</a>
        <a href="#">바로가기</a>
      </nav>
      <div class="header-icons">
        <input class="search" type="text" placeholder="제목, 작가를 입력하세요." />
        <span class="icon">🛒</span>
        <span class="icon">😊</span>
      </div>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function goHome() {
  router.push({
    name: "home",
  });
}
</script>

<style src="../../css/style-homepage.css"></style>