<template>
    <div class="page-container">
    <navBar></navBar>
  <div class="p-6 max-w-7xl mx-auto text-left">
    <button style="margin-bottom: 1em;" @click="$router.back()" class="mb-6 text-gray-500 hover:text-gray-800 flex items-center gap-2 return">
      ← Back to Dashboard
    </button>

    <div class="flex flex-col lg:flex-row gap-8">
      <div class="bg-white p-4 rounded-xl shadow left-section relative overflow-hidden flex flex-col" style="width: 17em;">
  
  <div class="blur-bg" :style="{
    backgroundImage: `url(${book.imageUrl})`,
  }"></div>

  <div class="relative z-10 flex flex-col w-full">
      
      <div class="cover mx-auto mb-4" :style="{
        width: '10em',
        height: '15em',
        backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${book.imageUrl})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundColor: `rgba(0, 0, 0, 0.2)`
      }"></div>

      <div class="text-center mb-4">
        <h2 class="text-lg font-bold leading-tight mb-1 title" v-text="`${book.title}`"></h2>
        <div class="text-gray-600 text-sm" v-text="`${book.authorName} · Web Novel`"></div>
      </div>

      <div class="flex flex-wrap justify-center gap-2 mb-4">
        <span v-for="t in tags" :key="t.id" class="px-2 py-1 bg-gray-100 rounded text-xs">
          {{ t.name }}
        </span>
      </div>

      <button v-if="ownership" class="w-full p-3 font-semibold mb-3 bg-yellow-400 text-white rounded-lg hover:bg-yellow-500 transition" @click="goChapterDetail(chapters[0])">
        Continue reading
      </button>
      
      <button v-else class="w-full p-3 font-semibold mb-3 bg-gray-200 text-gray-400 border rounded-lg cursor-not-allowed">
        Not owned
      </button>

      <button class="w-full p-3 font-semibold mb-3 border border-yellow-400 text-yellow-500 rounded-lg hover:bg-yellow-50 transition" @click="scrapeChapter()">
        Scrape
      </button>

      <div class="space-y-2 text-sm bg-white/80 p-3 rounded-lg backdrop-blur-sm">
        <div class="flex justify-between">
          <span>Rental rights</span>
          <span class="text-gray-500 font-medium">Charge</span>
        </div>
        <div class="flex justify-between">
          <span>Possession</span>
          <span class="text-gray-500 font-medium">Chapter 0</span>
        </div>
      </div>

      <button class="w-full mt-3 border border-gray-200 py-2 rounded-lg text-xs text-gray-500 bg-white hover:bg-gray-50">
        1-day rental pass Chapter 1 Available now
      </button>

  </div> </div>

      <div class="w-full lg:w-3/4">
        <div class="bg-white rounded-2xl border border-gray-100 shadow-sm p-6 min-h-[600px]">
          <div class="flex justify-between items-center mb-6">
            <div class="flex gap-6 border-b border-gray-200 pb-2">
              <button class="font-bold text-yellow-500 border-b-2 border-yellow-500 pb-2">Episodes</button>
              <button class="text-gray-500 hover:text-gray-800 pb-2">Description</button>
            </div>
            <button @click="showChapterForm = true" class="bg-yellow-400 hover:bg-yellow-500 text-white px-6 py-2 rounded-full font-bold shadow-md transition">
              + Publish Chapter
            </button>
          </div>

          <div v-if="showChapterForm" class="mb-8 bg-gray-50 p-6 rounded-xl border border-gray-200">
            <input v-model="newChapter.title" placeholder="Chapter Title" class="w-full p-3 bg-gray-700 text-white rounded-t-lg border-b border-gray-600 outline-none" />
            <textarea v-model="newChapter.content" placeholder="Write content..." class="w-full p-3 bg-gray-700 text-white h-40 rounded-b-lg outline-none resize-none"></textarea>
            <div class="flex gap-3 mt-4">
              <button @click="publishChapter" class="flex-1 bg-yellow-400 text-white font-bold py-3 rounded-lg">Publish Now</button>
              <button @click="showChapterForm = false" class="px-6 py-3 bg-white border border-gray-300 rounded-lg text-gray-600">Cancel</button>
            </div>
          </div>

          <div class="text-gray-700 font-bold mb-4">Total: {{ story.chapters.length }}</div>
          <div v-for="(chapter, index) in story.chapters" :key="index" class="p-4 bg-gray-50 mb-2 rounded-lg flex items-center gap-4">
            <span class="w-8 h-8 bg-gray-200 rounded flex items-center justify-center text-gray-500 text-xs font-bold">{{ index + 1 }}</span>
            <h4 class="font-bold text-gray-800">{{ chapter.title }}</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script setup>
import navBar from './implement/nav-bar.vue';
import { ref, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getBookById, getChapterByBookId } from '../api/bookApi';
import { onMounted, nextTick } from 'vue';

const book = ref([]);
const chapters = ref([]);
const router = useRouter();
const route = useRoute();
const story = reactive({
  title: 'Vĩnh thoại hiệp sĩ',
  author: 'M. Night',
  cover: 'https://picsum.photos/seed/2/400/250',
  status: 'Completed',
  chapters: [{ title: 'Prologue', date: '11/20/2023' }]
});

const id = route.params.id;

function getBook() {
  getBookById(id)
    .then(async res => {
      book.value = res.data;
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

const showChapterForm = ref(false);
const newChapter = reactive({ title: '', content: '' });

const publishChapter = () => {
  story.chapters.unshift({ title: newChapter.title, date: new Date().toLocaleDateString() });
  showChapterForm.value = false;
  newChapter.title = '';
  newChapter.content = '';
};

onMounted(() => {
  getBook();
  getChapters();
});
</script>

<style scoped>
/* Lớp nền mờ */
.blur-bg {
  position: absolute;
  inset: 0; /* Thay thế cho top:0, left:0, right:0, bottom:0 */
  width: 100%;
  height: 30%;
  
  background-size: cover;
  background-position: center;
  
  /* Hiệu ứng mờ */
  filter: blur(25px); 
  -webkit-filter: blur(25px);
  
  /* Scale để xóa viền trắng do blur */
  transform: scale(1.2); 
  
  /* Quan trọng: Nằm dưới cùng */
  z-index: 0; 
  opacity: 0.4; /* Độ mờ vừa phải để chữ vẫn đọc được */
}

/* Ảnh bìa chính */
.cover {
  position: relative;
  border-radius: 12px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.25);
  /* Không cần margin auto ở đây nữa vì đã dùng class mx-auto của Tailwind */
}

/* Các tiện ích hỗ trợ (nếu Tailwind chưa nhận diện) */
.z-10 {
  z-index: 10;
}
.relative {
  position: relative;
}

.title {
    font-size: 18px;
    font-weight: 700;
}

.return{
  transition: 0.4s;
}

.return:hover{
  color: black;
}
</style>