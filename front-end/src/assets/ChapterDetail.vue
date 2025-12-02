<script setup>
import { useRoute, useRouter } from "vue-router";
import { getChapterById } from '../api/bookApi';
import { onMounted, ref } from "vue";
import navBar from "./implement/nav-bar.vue";
import Footer from "./implement/footer.vue"

const route = useRoute();

const chapter = ref([]);

const id = route.params.id;

function getChapter(){
    getChapterById(id)
    .then(async res => {
      chapter.value = res.data;
    })
    .catch(console.error);
}

onMounted(() => {
    getChapter();
});

</script>

<template>
    <div class="page-container">
        <navBar></navBar>
        <div class="container">
            <div class="mt-2" style="font-size: 1.2em;">{{chapter.title}}</div>
            <div style="white-space: pre-line;" class="chapter-content">{{ chapter.content }}</div>
        </div>
        <Footer></Footer>
    </div>
</template>

<style src="../css/style-chapterdetail.css">
</style>

