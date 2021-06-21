<template>
    <h3>{{user.id}}){{user.name}} {{user.surname}} {{user.age}} {{user.email}}
        <button v-on:click="edit">Edit</button>
        <button v-on:click="del">X</button>
    </h3>
</template>

<script>
    export default {
        name: "UserRow",
        props: ['user'],
        methods: {
            edit() {

            },
            del() {

            }
        }
    }
</script>

<style scoped>

</style>