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

function getChapter() {
  getChapterById(id)
    .then(async res => {
      chapter.value = res.data;
    })
    .catch(console.error);
}

function getNextChapter() {
  getNextChapterById(id)
    .then(async res => {
      nextChapter.value = res.data;
    })
    .catch(console.error);
}

function getPreviousChapter() {
  getPreviousChapterById(id)
    .then(async res => {
      previousChapter.value = res.data;
    })
    .catch(console.error);
}

function getAllChapter() {
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

function goChapter(id) {
  router.push({
    name: "chapterDetail",
    params: { id },
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

      <div class="">
        <div class="button-group flex-rowed gap-2">
          <button @click="goPrevious(chapter)" class="btn btn-warning">Previous</button>

          <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">Chapters List</button>

          <button @click="goNext(chapter)" class="btn btn-warning">Next</button>
        </div>
        <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1"
          id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
          <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Current chapter: {{ chapter.title }}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body chapter-scroll">
            <div v-for="a in allChapters" :key="a.id" style="flex-grow: 0;">
              <button v-if="id == a.id" class="chapter-choose" @click="goChapter(a.id)"
                style="width: 100%; min-height: 60px; border: 2px orange solid;">
                {{ a.title }}
              </button>

              <button v-else class="chapter-choose" @click="goChapter(a.id)"
                style="width: 100%; min-height: 60px; border: 1px rgba(0, 0, 0, 0.1) solid;">
                {{ a.title }}
              </button>
            </div>
          </div>

        </div>
      </div>

      <div class="mt-2" style="font-size: 1.3em; font-weight: 700;">{{ chapter.title }}</div>
      <div style="white-space: pre-line;" class="chapter-content">{{ chapter.content }}</div>
      <div class="button-group flex-rowed gap-2">
        <button @click="goPrevious(chapter)" class="btn btn-warning">Previous</button>
        <button @click="goNext(chapter)" class="btn btn-warning">Next</button>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<style src="../css/style-chapterdetail.css"></style>
