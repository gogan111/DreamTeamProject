<template>
    <UserForm :addUser="editUser"/>

    <h1>List of Users</h1>

    <UserRow v-for="user in users"
             :key="user.id"
             :user="user"
             :editUser="editUser"
             :deleteUser="deleteUser"
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
        mounted() {
            fetch("rest/persons")
                .then(response =>
                    response.json()
                        .then(data =>
                            data.forEach(user => this.users.push(user))
                        )
                )
        },
        methods: {
            editUser(user) {

            },
            deleteUser(user) {

            }
        },
    }

</script>

<style scoped>

</style>