<template>
  <div>
    <h1>{{ profile.displayName }}</h1>
    <p/>
    <img width="100%" :src="imagePath">
    <p/>
    <div class="row">
      <div class="col-sm-4">Biography: </div>
      <div class="col-sm-8">{{ profile.biography }}</div>
    </div>
    <div class="row">
      <div class="col-sm-4">Location: </div>
      <div class="col-sm-8">{{ profile.location }}</div>
    </div>
    <div class="row">
      <div class="col-sm-4">Occupation: </div>
      <div class="col-sm-8">{{ profile.occupation }}</div>
    </div>
    <div class="row">
      <div class="col-sm-4">Interests: </div>
      <div class="col-sm-8">{{ profile.interests }}</div>
    </div>
    <div class="row">
      <div class="col-sm-4">Personality: </div>
      <div class="col-sm-8">{{ profile.personalityTraits }}</div>
    </div>
    <div class="row">
      <div class="col-sm-4">Lifestyle: </div>
      <div class="col-sm-8">{{ profile.lifestyle }}</div>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted } from 'vue';

const props = defineProps({
  uuid: String
});

const profile = ref({});
const imagePath = ref("");


// Fetch data from APIs on component mount
onMounted(() => {
  console.log(props);

  axios.get('/api/profile/' + props.uuid).then(response => {
    profile.value = response.data;
    imagePath.value = '/api/profile/' + profile.value.uuid + '/profileImage'
  });

});

</script>