<template>
    <UserForm/>

    <h1>List of Users</h1>

    <UserRow v-for="user in users"
             :key="user.id"
             :user="user"
    />
</template>

<script>
    import UserForm from "./UserForm";
    import UserRow from "./UserRow";

    export default {
        name: "UserList",
        components: {UserRow, UserForm},
        data() {
            return {
                users: [],
            }
        },
    }

</script>

<style scoped>

</style>