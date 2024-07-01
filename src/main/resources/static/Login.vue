<template>
  <div>
    <b-form @submit.prevent="handleLogin">
      <b-form-group label="Email" label-for="email">
        <b-form-input id="email" v-model="email" type="text" required></b-form-input>
      </b-form-group>
      <b-form-group label="Password" label-for="password">
        <b-form-input id="password" v-model="password" type="password" required></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Login</b-button>
      <b-button variant="link" @click="navigateToRegister">Register</b-button>
    </b-form>
  </div>
</template>

<script setup>
import { ref, inject } from 'vue'
import { useRouter } from 'router'

const router = useRouter();

const email = ref('');
const password = ref('');

const handleLogin = () => {

  axios.post('/auth/login', `username=${email.value}&password=${password.value}`,
    { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }
  ).then(result => {
    router.push({ name: 'home' });
  }).catch(error => {});

};

const navigateToRegister = () => {
  router.push({ name: 'Register' });
};
</script>

<style scoped></style>