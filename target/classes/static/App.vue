<template>
    <div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <RouterLink class="nav-link active" to="/">FSWD PowerDate</RouterLink>
                <b>Welcome {{ loginName }}</b>
                <RouterLink class="nav-link" to="ownProfileEdit">Edit Profile</RouterLink>

                <b-button @click="handleLogout">Logout</b-button>
            </div>
        </nav>
        <RouterView />
    </div>
</template>

<script setup>

import { getCurrentInstance, ref } from 'vue';
import { createRouter, createWebHashHistory } from 'router';

import Home from './Home.vue'
import Login from './Login.vue'
import ProfileDetails from './ProfileDetails.vue'
import OwnProfileEdit from './OwnProfileEdit.vue'


const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/ownProfileEdit',
        name: 'ownProfileEdit',
        component: OwnProfileEdit
    },
    {
        path: '/profileDetails/:uuid',
        name: 'profileDetails',
        component: ProfileDetails,
        props: true                 // important as we pass the uuid of the profile 
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

const loginName = ref("");

const handleLogout = () => {
    // Call logout endpoint. Whatever happens, forward to login
    axios.post('/auth/logout', '{}')
        .then(response => {
            router.push({ name: 'login' });
        }).catch(error => {
            router.push({ name: 'login' });
        });
}

getCurrentInstance().appContext.app.use(router);

// Load current login name. Will fail with no session and then redirect to login page 
axios.get('/auth/current', '{}')
    .then(response => {
        loginName.value = response.data;
    }).catch(error => {
        router.push({ name: 'login' });
        loginName.value = "";
    });

</script>