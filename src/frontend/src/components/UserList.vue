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
                fetch("rest/persons/" + user.id, {method: "DELETE"})
                    .then(response => {
                            if (response.ok) {
                                alert(response.ok + " deleted");
                                this.users.splice(this.users.indexOf(user), 1);
                            } else {
                                alert('not deleted')
                            }
                        }
                    )

                this.user = user
            }
        },
    }

</script>

<style scoped>

</style>