<template>
    <div>Name: <input type="text" v-model="name" placeholder="Enter name"></div>
    <div>Surname: <input type="text" v-model="surname" placeholder="Enter surname"></div>
    <div>Age: <input type="number" v-model="age" placeholder="Enter age"></div>
    <div>Email: <input type="email" v-model="email" placeholder="example@email.com"></div>

    <div>
        <button v-on:click="save">Save</button>
    </div>
</template>

<script>
    export default {
        name: "UserForm",
        data() {
            return {
                name: '',
                surname: '',
                age: '',
                email: '',
                id: ''
            }
        },

        methods: {
            save() {

            }
        }
    }
</script>

<style scoped>

</style>