<template>
  <div class="row row-cols-3 g-3">
    <div class="card mb-3" v-for="profile in profiles" v-bind:key="profile.uuid">
      <img width="100%" :src="'/api/profile/' + profile.uuid + '/profileImage'">

      <div class="card-body">
        <h2>{{ profile.displayName }}</h2>
        <div class="row">
          <div class="col-sm-8">{{ profile.age }}</div>
        </div>
        <div class="row">
          <div class="col-sm-8">{{ profile.gender }}</div>
        </div>
        <p />
        <div class="row">
          <div class="col-sm-12">{{ profile.biography }}</div>
        </div>
        <RouterLink :to="{ name: 'profileDetails', params: { uuid: profile.uuid } }"
          class="btn btn-primary stretched-link">View Details</RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

// Initialize reactive variables
const profiles = ref([]);

// Fetch data from APIs on component mount
onMounted(() => {
  axios.get('/api/profile').then(response => {
    profiles.value = response.data;
  });
});

</script>

<style scoped>
/* Add your styles here */
</style>