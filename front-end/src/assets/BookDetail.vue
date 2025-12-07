<script setup>
import { useRoute } from "vue-router";
import { getBookById } from '../api/bookApi';
import { nextTick, onMounted, ref } from "vue";
import { getTagByBookId } from '../api/bookApi';
import { getChapterByBookId } from '../api/bookApi';
import { getBookFromSameAuthor } from '../api/bookApi';
import { getAllBooks } from '../api/bookApi';
import navBar from '../assets/implement/nav-bar.vue';
import { useRouter } from "vue-router";
import { Alert } from "bootstrap/dist/js/bootstrap.bundle.min";

const route = useRoute();

const id = route.params.id;
const book = ref([]);

const tags = ref([]);

const chapters = ref([]);

const authoredBooks = ref([]);

const allBooks = ref([]);

function getBook() {
  getBookById(id)
    .then(async res => {
      book.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}

function getTags() {
  getTagByBookId(id)
    .then(async res => {
      tags.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}

function getChapters() {
  getChapterByBookId(id)
    .then(async res => {
      chapters.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}

function getAuthorsBooks() {
  getBookFromSameAuthor(id)
    .then(async res => {
      authoredBooks.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}

function getAllBook() {
  getAllBooks()
    .then(async res => {
      allBooks.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}

onMounted(() => {
  getBook();
  getTags();
  getChapters();
  getAuthorsBooks();
  getAllBook();
});


const currentTab = ref("Episodes");

const router = useRouter();

function goDetail(book) {
  router.push({
    name: "bookDetail",
    params: { id: book.id },
    state: { book } 
  });
}

function goChapterDetail(chapter){
  router.push({
    name: "chapterDetail",
    params: { id: chapter.id },
    state: { chapter } 
  });
}

</script>

<template>
  <div class="page-container">
    <navBar></navBar>

    <div class="flex-rowed gap-6 p-6 bg-gray-100 min-h-screen" v-if="book">
      <!-- Left Section -->
      <div class="bg-white p-4 rounded-xl shadow left-section">
        <div class="blur-bg" :style="{
          backgroundImage: `url(${book.imageUrl})`,
        }"></div>
        <div class="cover" :style="{
          width: '10em',
          height: '15em',
          backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${book.imageUrl})`,
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          backgroundColor: `rgba(0, 0, 0, 0.2)`
        }"></div>
        <h2 class="text-m font-bold mb-2 mt-4 title info" v-text="`${book.title}`"></h2>
        <div class="text-gray-600 mb-3 author info" v-text="`${book.authorName} · Web Novel`"></div>
        <div class="flex-rowed" style="justify-content: start; gap: 1em;">
          <div v-for="t in tags" :key="t.id">
            <p>{{ t.name }}</p>
          </div>
        </div>

        <button class="w-100 p-3 font-semibold mb-4 btn btn-warning border rounded" @click="goChapterDetail(chapters[0])">
          Continue reading
        </button>

        <div class="flex justify-between text-sm mb-2">
          <span>rental rights</span>
          <span class="text-gray-500">charge</span>
        </div>
        <div class="flex justify-between text-sm">
          <span>possession rights</span>
          <span class="text-gray-500">Chapter 0</span>
        </div>

        <button class="w-full mt-4 border py-2 rounded-lg text-sm">
          1-day rental pass Chapter 1 Available now
        </button>
      </div>

      <!-- Right Section -->
      <div class="right-section">
        <div class="bg-white p-4 rounded-xl shadow mb-6 episodes">
          <div class="category-bar">
            <button v-for="c in categories" :key="c" @click="currentTab = c"
              :class="['tab-btn', currentTab === c ? 'active' : '']">
              {{ c }}
            </button>

          </div>
          <div v-if="currentTab === 'Episodes'">
            <h6 class="text-lg font-bold ps-3"><strong>Total: {{ chapters.length }}</strong></h6>
            <hr>
            <div class="chapter-scroll">
              <ul>
                <li v-for="ch in chapters" :key="ch.id" class="flex items-center justify-between py-3 border-b">


                  <div class="flex-rowed items-center gap-3" @click="goChapterDetail(ch)">
                    <img :src="book.imageUrl" class="rounded img_icon" />
                    <div>
                      <p class="font-bold">{{ ch.title }}</p>
                      <p style="color: gray; font-weight: 0; max-width: fit-content;" class="text-limit">{{ ch.content }}</p>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div v-else>
            <h6 class="text-lg font-bold ps-3"><Strong>Story</Strong></h6>
            <hr>
            <p style="white-space: pre-line;">{{ book.description }}</p>
          </div>
        </div>

        <div class="bg-white p-4 rounded-xl shadow mb-6">
          <h3 class="text-lg font-bold mb-4">Web novels to read along</h3>
          <div class="h-grid grid-cols-4 gap-4">
            <div @click="goDetail(a)" v-for="a in allBooks" :key="a.id" class="text-centered" :style="{
              backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${a.imageUrl})`,
              backgroundSize: 'cover',
              backgroundPosition: 'top',
              backgroundColor: `rgba(0, 0, 0, 0.2)`
            }">

              <p class="text-sm font-semibold text-limit">{{ a.title || a }}</p>
              
            </div>
          </div>
        </div>

        <div class="bg-white p-4 rounded-xl shadow">
          <h3 class="text-lg font-bold mb-4">Other works by this author</h3>
          <div class="h-grid grid-cols-4 gap-4">
            <div @click="goDetail(a)" v-for="a in authoredBooks" :key="a.id" class="text-centered" :style="{
              backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${a.imageUrl})`,
              backgroundSize: 'cover',
              backgroundPosition: 'top',
              backgroundColor: `rgba(0, 0, 0, 0.2)`
            }">

              <p class="text-sm font-semibold text-limit">{{ a.title || a }}</p>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<style scoped src="../css/style-bookdetail.css">
body {
  font-family: sans-serif;
}
</style>

<script>
export default {
  data() {
    return {
      categories: [
        "Episodes", "Description"
      ]
    };
  }
};
</script>
