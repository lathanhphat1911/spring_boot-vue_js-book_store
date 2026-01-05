<template>
    <div class="page-container">
        <div class="min-h-screen font-sans">
            <navBar></navBar>

            <main class="max-w-7xl mx-auto w-full">
                <div v-if="!currentStory">
                    <div class="p-8">
                        <div class="mb-8">
                            <h1 class="text-3xl font-bold text-gray-800">Author Workspace</h1>
                            <p class="text-gray-500 mt-2">Manage your web novels and track chapter updates.</p>
                        </div>

                        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                            <div @click="isCreateModalOpen = true"
                                class="border-2 border-dashed border-gray-300 rounded-2xl flex flex-col items-center justify-center h-80 cursor-pointer hover:bg-gray-100 transition p-6 bg-white">
                                <div
                                    class="w-12 h-12 bg-gray-100 rounded-full flex items-center justify-center mb-4 text-2xl text-gray-400">
                                    +</div>
                                <span class="font-medium text-gray-600">Post New Work</span>
                            </div>

                            <div v-for="story in stories" :key="story.id"
                                class="bg-white border border-gray-200 rounded-2xl overflow-hidden shadow-sm hover:shadow-md transition cursor-pointer flex flex-col">
                                <div class="relative h-48 bg-gray-100">
                                    <img :src="story.cover" class="w-full h-full object-cover" />
                                    <span
                                        class="absolute top-3 right-3 px-2 py-1 bg-white/90 text-[10px] font-black rounded uppercase shadow-sm border border-gray-100">
                                        {{ story.status }}
                                    </span>
                                </div>

                                <div class="p-5 flex-1">
                                    <h3 class="font-bold text-lg text-gray-800">{{ story.title }}</h3>
                                    <div class="flex items-center text-gray-400 text-xs mt-2 space-x-4 font-medium">
                                        <span>📚 {{ story.chapters.length }} Chapters</span>
                                        <span>🕒 {{ story.updatedAt }}</span>
                                    </div>

                                    <button @click.stop="goStoryDetail(story.id)"
                                        class="mt-4 w-full py-2 bg-gray-50 hover:bg-gray-100 text-gray-600 font-bold rounded-lg text-sm border border-gray-100 transition">
                                        Edit Content >
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <StoryDetail v-else :story="currentStory" @back="currentStory = null" />
            </main>

            <div v-if="isCreateModalOpen" class="fixed inset-0 bg-black/60 flex items-center justify-center p-4 z-50">
                <div
                    class="bg-white w-full max-w-2xl rounded-2xl shadow-2xl overflow-hidden animate-in fade-in zoom-in duration-200">
                    <div class="bg-yellow-400 p-4 flex justify-between items-center text-white">
                        <h2 class="font-bold text-lg">Post New Work</h2>
                        <button @click="isCreateModalOpen = false">✕</button>
                    </div>
                    <div class="p-6 space-y-4">
                        <div class="grid grid-cols-2 gap-4">
                            <div>
                                <label class="text-[10px] font-black text-gray-400 uppercase">Novel Title</label>
                                <input v-model="newStory.title" placeholder="e.g. My Infinite System"
                                    class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg outline-none focus:border-yellow-400" />
                            </div>
                            <div>
                                <label class="text-[10px] font-black text-gray-400 uppercase">Author Alias</label>
                                <input v-model="newStory.author" placeholder="e.g. Charlie Le"
                                    class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg outline-none focus:border-yellow-400" />
                            </div>
                        </div>
                        <div>
                            <label class="text-[10px] font-black text-gray-400 uppercase">Synopsis</label>
                            <textarea v-model="newStory.synopsis" rows="3"
                                class="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg outline-none focus:border-yellow-400"></textarea>
                        </div>
                        <div class="flex gap-4">
                            <button @click="addNewStory"
                                class="flex-1 bg-yellow-400 text-white font-bold py-3 rounded-lg">Create Work</button>
                            <button @click="isCreateModalOpen = false"
                                class="px-8 bg-gray-100 text-gray-500 font-bold rounded-lg">Discard</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import AuthorStoryDetails from './AuthorStoryDetails.vue'
import navBar from './implement/nav-bar.vue'
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'

const isCreateModalOpen = ref(false)
const currentStory = ref(null)

const stories = reactive([
    { id: 1, title: 'Thanh mai trúc mã của đệ nhất thiên hạ', author: 'Unknown', cover: 'https://picsum.photos/seed/1/400/250', status: 'Ongoing', updatedAt: '1/15/2024', chapters: [{ title: 'Chapter 1', date: '1/15/2024' }] },
    { id: 2, title: 'Vĩnh thoại hiệp sĩ', author: 'M. Night', cover: 'https://picsum.photos/seed/2/400/250', status: 'Completed', updatedAt: '11/20/2023', chapters: [{ title: 'Prologue', date: '11/20/2023' }] }
])

const newStory = reactive({ title: '', author: '', synopsis: '' })
const router = useRouter();
function goStoryDetail(id) {
    router.push({
        name: "author_story_details",
        params: { id: id },
    });
}

const addNewStory = () => {
    stories.unshift({
        ...newStory,
        id: Date.now(),
        cover: `https://picsum.photos/seed/${Date.now()}/400/250`,
        status: 'Ongoing',
        updatedAt: 'Just now',
        chapters: []
    })
    isCreateModalOpen.value = false
}


</script>