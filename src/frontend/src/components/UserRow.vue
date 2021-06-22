<template>
    <h3>{{user.id}}){{user.name}} {{user.surname}} {{user.age}} {{user.email}}
        <button v-on:click="edit">Edit</button>
        <button v-on:click="del">X</button>
    </h3>
</template>

<script>
    export default {
        name: "UserRow",
        props: ['user', 'editUser', 'deleteUser'],
        methods: {
            edit() {
                alert("row " + this.user.name)
                this.editUser(this.user)
            },
            del() {
                this.deleteUser(this.user)
            }
        }
    }
</script>

<style scoped>

</style>