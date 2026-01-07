<script setup>
import { onMounted, ref } from "vue";
import { getToken, login } from "../api/authSevice";
import navBar from "./implement/nav-bar.vue";
import { useRouter } from "vue-router";

const username = ref("");
const password = ref("");
const router = useRouter();

async function handleLogin() {
  try {
    await login(username.value, password.value);
    router.push("/");
  } catch (e) {
    console.log(e)
    alert("Login failed");
  }
}

function loginCheck(){
  
}

onMounted(() =>{

});

</script>

<template>
  <div class="page-container">
    <navBar></navBar>
    <div class="page-container" style="display: flex; align-items: center; justify-content: center;">
      
      <div class="login-container">
        <form @submit.prevent="handleLogin" class="login-form">
          <h5>Đăng nhập</h5>
          <input v-model="username" class="form-control" placeholder="Username" />
          <input v-model="password" class="form-control" type="password" placeholder="Password" />
          <button class="btn btn-success">Login</button>
        </form>
      </div>
    </div>
  </div>
</template>




