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
        props: true
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
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { top: 0 }; // luôn scroll lên top khi đổi route
    }
});

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("jwt");
  if (to.meta.requiresAuth && !token) {
    next("/login");
  } else {
    next();
  }
});

export default router;