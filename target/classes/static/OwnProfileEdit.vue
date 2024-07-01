<template>
  <div>
    <h1>{{ profile.displayName }}</h1>
    <p />
    <img width="100%" :src="imagePath">
    <p />


    <b-form @submit.prevent="handleSaveRequest">

      <b-form-group label="displayName" label-for="displayName"><b-form-input id="displayName"
          v-model="profile.displayName" type="text"></b-form-input></b-form-group>
      <b-form-group label="age" label-for="age"><b-form-input id="age" v-model="profile.age"
          type="text"></b-form-input></b-form-group>
      <b-form-group label="location" label-for="location"><b-form-input id="location" v-model="profile.location"
          type="text"></b-form-input></b-form-group>
      <b-form-group label="occupation" label-for="occupation"><b-form-input id="occupation" v-model="profile.occupation"
          type="text"></b-form-input></b-form-group>
      <b-form-group label="biography" label-for="biography"><b-form-input id="biography" v-model="profile.biography"
          type="text"></b-form-input></b-form-group>
      <b-form-group label="interests" label-for="interests"><b-form-input id="interests" v-model="profile.interests"
          type="text"></b-form-input></b-form-group>
      <b-form-group label="personalityTraits" label-for="personalityTraits"><b-form-input id="personalityTraits"
          v-model="profile.personalityTraits" type="text"></b-form-input></b-form-group>
      <b-form-group label="lifestyle" label-for="lifestyle"><b-form-input id="lifestyle" v-model="profile.lifestyle"
          type="text"></b-form-input></b-form-group>
      <b-button type="submit" variant="primary">Save</b-button>
    </b-form>



    
</div></template>
  
<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'router';

const router = useRouter();

const profile = ref({

});
const imagePath = ref("");


// Fetch data from APIs on component mount
onMounted(() => {
  axios.get('/api/profile/me').then(response => {
    profile.value = response.data;
    imagePath.value = '/api/profile/' + profile.value.uuid + '/profileImage'
  });
});

const handleSaveRequest = () => {
  axios.post('/api/profile/me', profile.value).then(response => {
    router.push({ name: 'home' });
  });
}

</script>