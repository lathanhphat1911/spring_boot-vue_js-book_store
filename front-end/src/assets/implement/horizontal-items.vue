<script setup>
import { ref, onMounted, watch, nextTick } from "vue";
import { getBooksByTopMinMax } from "../../api/bookApi";
import { useRouter } from "vue-router";

const props = defineProps({
  itemCount: Number,
  min: {type: Number, default: 1},
  max: { type: Number, default: 3 },
  step: { type: Number, default: 150 }
});

const grid = ref(null);
const offset = ref(0);
let maxScroll = 0;

const books = ref([]);

function getTop() {
  getBooksByTopMinMax(props.min, props.max)
    .then(async res => {
      books.value = res.data;
      await nextTick();
    })
    .catch(console.error);
}


watch(
  () => [props.min, props.max],
  () => {
    getTop();
  },
  { immediate: true }   
);

const router = useRouter();

function goDetail(book) {
  router.push({
    name: "bookDetail",
    params: { id: book.id },
    state: { book } 
  });
}
</script>

<template>
  
  <div @click="goDetail(b)" v-for="b in books":key="b" class="featured-item" style="height: 300px; align-items: end; justify-content: start; padding-left: 15px;" :style="{
      backgroundImage: `linear-gradient(to top, rgba(0,0,0,1.7), rgba(0,0,0,0)), url(${b.imageUrl})`,
      backgroundSize: 'cover',
      backgroundPosition: 'top',
      backgroundColor: `rgba(0, 0, 0, 0.2)`
    }">
    <p style="max-width: 150px;" class="text-limit-2">{{ b.title || b }}</p>
  </div>
    
</template>
