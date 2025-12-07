<script setup>
import { ref, onMounted } from "vue";
import { getBooksByTopMinMax, getTagsByTop, getBookByTag } from "../api/bookApi";
import { useRoute, useRouter } from "vue-router";
import navBar from "./implement/nav-bar.vue";
import Footer from "./implement/footer.vue";
import { Alert } from "bootstrap/dist/js/bootstrap.bundle.min";

const tags = ref([]);
const books = ref([]);
const route = useRoute();
const id = route.params.id;


async function loadTags() {
  const res = await getTagsByTop(300);
  tags.value = res.data;
}

async function loadBooks() {
  const res = await getBookByTag(id);
  books.value = res.data;
}


onMounted(() => {
  loadTags();
  loadBooks();
});

const router = useRouter();

function goDetail(book) {
  router.push({
    name: "bookDetail",
    params: { id: book.id },
    state: { book } 
  });
}

function goTags(id) {
  router.push({
    name: "listByTags",
    params: { id },
    state: {tags}
  });
}

</script>

<template>
  <div class="page-container">
    <navBar></navBar>

    <!-- ----------------------- TAG FILTER ------------------------------ -->
    <div class="flex-rowed" style="justify-content: start; gap: 0.3em;">
      <button style="border-radius: 25px;"
        v-for="t in tags"
        :key="t.id"
        class="px-4 py-2 rounded-full border text-sm"
        :class="Number(id) === t.id ? 'bg-black text-white' : ''"
        @click="goTags(t.id)"
      >
        {{ t.name }}
      </button>
    </div>

    <!-- ----------------------- TITLE ------------------------------ -->
    <h2 class="text-2xl font-bold mb-4"></h2>

    <!-- ----------------------- GRID LIST ------------------------------ -->
    <div class="grid grid-cols-6 gap-4">

      <!-- Single Book Card -->
       <div @click="goDetail(book)" class="flex-colled" v-for="(book, index) in books"
          :key="book.id">
        <div class="item-images" :style="
          {
              backgroundImage: `url(${book.imageUrl})`,
              backgroundSize: 'cover',
              backgroundPosition: 'top',
            }"
        ></div>
              

        <div class="">
          <div class="index-surround">
            <div class="">
              {{ index + 1 }}
            </div>
            <p>-</p>
          </div>
          <div class="" style="padding-top: 10px;">
              <p class="text-limit_2">{{ book.title }}</p>
            </div>
          </div>
        
      </div>

      
    </div>
    <Footer></Footer>
  </div>
</template>

<style src="../css/style-tagdetail.css"></style>

<style>

.grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
}

.truncate {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
