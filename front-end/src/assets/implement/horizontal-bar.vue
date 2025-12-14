<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted, watch, nextTick } from "vue";
import { getTagsByTop, getImageUrlByGenre } from "../../api/bookApi";

const props = defineProps({
  top: { type: Number, default: 3 },
});

const tags = ref([]);
const images = ref({});

async function loadTags() {
  const res = await getTagsByTop(props.top);
  tags.value = res.data;

  for (const tag of tags.value) {
    const img = await getImageUrlByGenre(tag.name);
    images.value[tag.name] = img.data; 
  }
}

const router = useRouter();

function goTags(id) {
  router.push({
    name: "listByTags",
    params: { id }
  });
}

function getBook(){
  alert(tags.value)
  console.log(tags.value)
}

onMounted(() => {
  loadTags();
});


</script>

<template>
  <div @click="goTags(t.id)" v-for="t in tags":key="t" class="featured-item" :style="
  {
      backgroundImage: `url(${images[t.name]})`,
      backgroundSize: 'cover',
      backgroundPosition: 'top'
    }"><p class="text-limit" v-bind="name">{{ t.name || t }}</p></div>
</template>
