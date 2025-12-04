<script setup>
import { useRoute, useRouter } from "vue-router";
import { getChapterById, getNextChapterById, getPreviousChapterById, getChapterFromSameBookById } from '../api/bookApi';
import { onMounted, ref } from "vue";
import navBar from "./implement/nav-bar.vue";
import Footer from "./implement/footer.vue"

const route = useRoute();

const chapter = ref([]);

const nextChapter = ref([]);

const previousChapter = ref([]);

const allChapters = ref([]);

const id = route.params.id;

function getChapter(){
    getChapterById(id)
    .then(async res => {
      chapter.value = res.data;
    })
    .catch(console.error);
}

function getNextChapter(){
    getNextChapterById(id)
    .then(async res => {
      nextChapter.value = res.data;
    })
    .catch(console.error);
}

function getPreviousChapter(){
    getPreviousChapterById(id)
    .then(async res => {
      previousChapter.value = res.data;
    })
    .catch(console.error);
}

function getAllChapter(){
    getChapterFromSameBookById(id)
    .then(async res => {
      allChapters.value = res.data;
    })
    .catch(console.error);
}


const router = useRouter();

function goNext(chapter) {
  router.push({
    name: "chapterDetail",
    params: { id: nextChapter.value.id },
    state: { nextChapter } 
  });
}

function goPrevious(chapter) {
  router.push({
    name: "chapterDetail",
    params: { id: previousChapter.value.id },
    state: { previousChapter } 
  });
}

function goChapter(chapter) {
  const selectedId = event.target.value;
  router.push({
    name: "chapterDetail",
    params: { id: selectedId },
    state: { goChapter } 
  });
}

onMounted(() => {
    getChapter();
    getNextChapter();
    getPreviousChapter();
    getAllChapter();
});

</script>

<template>
    <div class="page-container">
        <navBar></navBar>
        <div class="container">
            <select @change="goChapter($event)">
              <option v-for="c in allChapters" :key="c.id" :value="c.id">{{ c.title }}</option>
            </select>
            <div class="mt-2" style="font-size: 1.3em; font-weight: 700;">{{chapter.title}}</div>
            <div style="white-space: pre-line;" class="chapter-content">{{ chapter.content }}</div>
            <div class="button-group flex-rowed gap-2">
                <button @click="goPrevious(chapter)" class="btn btn-warning">Previous</button>
                <button @click="goNext(chapter)" class="btn btn-warning">Next</button>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>

<style src="../css/style-chapterdetail.css">
</style>

