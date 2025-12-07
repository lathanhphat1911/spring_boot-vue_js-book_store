<script setup>
import navBar from './implement/nav-bar.vue';
import Footer from './implement/footer.vue';
import horizontalSlider from './implement/horizontal-slider.vue';
import horizontalBar from './implement/horizontal-bar.vue';
import { ref, onMounted } from 'vue';
import HorizontalSliderTaller from './implement/horizontal-slider-taller.vue';
import HorizontalItems from './implement/horizontal-items.vue';

</script>

<template>

    <div class="page-container flex-col">

        <navBar></navBar>

        <!-- Category Buttons -->
        <div class="category-bar">
            <button v-for="c in categories" :key="c">{{ c }}</button>
        </div>

        <!-- Banner Section -->
        <div class="banner-section">
            <div class="banner big-banner"></div>
            <div class="banner side-banner"></div>
            <div class="banner side-banner"></div>
        </div>

        <!-- Featured List -->
        <h2 class="section-title">Genre</h2>
        <div class="featured-list">
            <horizontal-bar :top="8"></horizontal-bar>
        </div>

        <!-- Normal List -->
         <h2 class="section-title">Most Viewed Novels</h2>
        <HorizontalSliderTaller :itemCount="13" :top="13" :itemWidth="150" :step="142" />

        <br>
        <h2 class="section-title">You also might like</h2>
        <horizontalSlider  :itemCount="20" :min="14" :max="23" :itemWidth="200" :step="142.5" />

        <br>
        <h2 class="section-title">Featured Items</h2>
        <div class="featured-list" style="grid-template-columns: repeat(6, 1fr)">
            <horizontal-items :min="1" :max="12"></horizontal-items>
        </div>
        <Footer></Footer>
    </div>
</template>

<script>
export default {
    data() {
        return {
            categories: [
                "Category 1", "Category 1", "Category 1", "Category 1", "Category 1", "완결작",
                "여성인기", "TV&책", "브랜드"
            ]
        };
    }
};
</script>

<style src="../css/style-homepage.css"></style>