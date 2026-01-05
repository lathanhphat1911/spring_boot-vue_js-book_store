<template>
  <div class="fixed inset-0 bg-black/50 z-50 flex items-center justify-center p-4">
    <div class="bg-white w-full max-w-2xl rounded-2xl shadow-2xl overflow-hidden">
      
      <div class="bg-yellow-400 p-4 flex justify-between items-center text-white">
        <h2 class="font-bold text-lg">Post New Work</h2>
        <button @click="$emit('close')" class="text-white hover:bg-yellow-500 p-1 rounded">✕</button>
      </div>

      <div class="p-6 space-y-5">
        
        <div class="grid grid-cols-2 gap-4">
          <div>
            <label class="block text-xs font-bold text-gray-500 uppercase mb-1">Novel Title</label>
            <input v-model="form.title" placeholder="e.g. My Infinite System" class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg focus:ring-2 focus:ring-yellow-400 focus:outline-none"/>
          </div>
          <div>
            <label class="block text-xs font-bold text-gray-500 uppercase mb-1">Author Alias</label>
            <input v-model="form.author" placeholder="e.g. Charlie Le" class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg focus:ring-2 focus:ring-yellow-400 focus:outline-none"/>
          </div>
        </div>

        <div>
          <div class="flex justify-between mb-1">
             <label class="block text-xs font-bold text-gray-500 uppercase">Synopsis</label>
             <span class="text-xs text-purple-400 font-bold cursor-pointer">✨ AI GENERATE SYNOPSIS</span>
          </div>
          <textarea v-model="form.synopsis" placeholder="Tell your readers what the story is about..." class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg h-24 resize-none focus:ring-2 focus:ring-yellow-400 focus:outline-none"></textarea>
        </div>

        <div>
           <label class="block text-xs font-bold text-gray-500 uppercase mb-2">Select Genres</label>
           <div class="flex flex-wrap gap-2">
             <button 
                v-for="genre in genres" 
                :key="genre"
                @click="toggleGenre(genre)"
                class="px-4 py-1.5 rounded-full text-sm font-medium transition"
                :class="form.genres.includes(genre) ? 'bg-yellow-400 text-white' : 'bg-gray-100 text-gray-600 hover:bg-gray-200'"
             >
               {{ genre }}
             </button>
           </div>
        </div>

        <div class="grid grid-cols-2 gap-4">
           <div>
             <label class="block text-xs font-bold text-gray-500 uppercase mb-1">Release Status</label>
             <select v-model="form.status" class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg">
               <option value="Ongoing">Ongoing</option>
               <option value="Completed">Completed</option>
             </select>
           </div>
           <div>
             <label class="block text-xs font-bold text-gray-500 uppercase mb-1">Cover Image</label>
             <div class="w-full p-3 border-2 border-dashed border-gray-200 rounded-lg text-gray-400 text-sm flex items-center justify-center cursor-pointer hover:bg-gray-50">
               🖼️ Randomly generated on submit
             </div>
           </div>
        </div>

      </div>

      <div class="p-4 border-t border-gray-100 flex gap-3 bg-gray-50">
        <button @click="submit" class="flex-1 bg-yellow-400 hover:bg-yellow-500 text-white font-bold py-3 rounded-lg shadow-md transition">
          Create Work
        </button>
        <button @click="$emit('close')" class="px-6 py-3 bg-gray-200 hover:bg-gray-300 text-gray-700 font-bold rounded-lg transition">
          Discard
        </button>
      </div>

    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

const emit = defineEmits(['close', 'create']);

const genres = ['Romance', 'Fantasy', 'Adventure', 'Isekai', 'Comedy', 'Drama', 'Horror', 'Mystery'];

const form = reactive({
  title: '',
  author: '',
  synopsis: '',
  genres: ['Romance'], // Default select based on image
  status: 'Ongoing'
});

const toggleGenre = (genre) => {
  if (form.genres.includes(genre)) {
    form.genres = form.genres.filter(g => g !== genre);
  } else {
    form.genres.push(genre);
  }
};

const submit = () => {
  // Simple validation could go here
  emit('create', { ...form, cover: 'https://source.unsplash.com/random/300x450?book', id: Date.now(), chapters: [], updatedAt: 'Just now' });
};
</script>