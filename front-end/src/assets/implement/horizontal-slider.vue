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
      calculateBounds();
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


watch(() => props.itemCount, () => nextTick(calculateBounds));

function calculateBounds() {
  if (!grid.value) return;

  const viewport = grid.value.parentElement;
  const contentWidth = grid.value.scrollWidth;
  const viewportWidth = viewport.offsetWidth;

  maxScroll = Math.min(0, viewportWidth - contentWidth);

  if (offset.value < maxScroll) offset.value = maxScroll;
}

function moveX(px) {
  let next = offset.value + px;

  if (next > 0) next = 0;
  if (next < maxScroll) next = maxScroll;

  offset.value = next;
  grid.value.style.transform = `translateX(${offset.value}px)`;
}

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
  <div class="horizontal-grid">
    <div class="h-grid" ref="grid">
      <div @click="goDetail(b)" v-for="b in books" :key="b.id" class="book-card" :style="{ backgroundImage: `url(${b.imageUrl || b})`}">
        <p class="text-limit">{{ b.title || b }}</p>
      </div>
    </div>
  </div>

  <div class="flex-row gapa-2">
    <button @click="moveX(props.step)" class="arrow-key"><</button>
    <button @click="moveX(-props.step)" class="arrow-key">></button>
  </div>
</template>
