// src/router/index.js

import { createRouter, createWebHistory } from "vue-router";

const routes = [{
        path: "/",
        name: "home",
        component: () =>
            import ("../assets/HomePage.vue")
    },

    {
        path: "/book/:id",
        name: "bookDetail",
        component: () =>
            import ("../assets/BookDetail.vue"),
        props: true,
        meta: { requiresAuth: false }
    },

    {
        path: "/chapter/:id",
        name: "chapterDetail",
        component: () =>
            import ("../assets/ChapterDetail.vue"),
        props: true
    },

    {
        path: "/tags/:id",
        name: "listByTags",
        component: () =>
            import ("../assets/ListByTags.vue"),
        props: true
    },
    {
        path: "/login",
        name: "login",
        component: () =>
            import("../assets/Login.vue"),
        
    },
    {
        path: "/author_workspace",
        name: "author_workspace",
        component: () =>
            import("../assets/AuthorWorkspace.vue"),
    },
    {
        path: "/author_story_details/:id",
        name: "author_story_details",
        component: () =>
            import("../assets/AuthorStoryDetails.vue")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { top: 0 }; // luôn scroll lên top khi đổi route
    }
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem("jwt")
    if (to.name === "login" && token) {
    next("/")
    return
  }

  if (to.meta.requiresAuth && !localStorage.getItem("jwt")) {
    next("/login")
  } else {
    next()
  }
});

export default router;