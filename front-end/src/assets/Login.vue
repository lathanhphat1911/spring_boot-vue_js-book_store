<template>
  <form @submit.prevent="handleLogin">
    <input v-model="username" placeholder="Username" />
    <input v-model="password" type="password" placeholder="Password" />
    <button>Login</button>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { getToken, login } from "../api/authSevice";
import { useRouter } from "vue-router";

const username = ref("");
const password = ref("");
const router = useRouter();

async function handleLogin() {
  alert(username.value)
  alert(password.value)
  try {
    await login(username.value, password.value);
    alert("Succeed")
    router.push("/");
  } catch (e) {
    console.log(e)
    alert("Login failed");
  }
}
</script>
