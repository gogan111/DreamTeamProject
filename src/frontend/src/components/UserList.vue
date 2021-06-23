<template>
    <UserForm :users="users" :userAttr="user" :validatorAttr="validator" :saveUser="saveUser"></UserForm>

    <h2>List of Heroes</h2>

    <UserRow v-for="user in users"
             :key="user.id"
             :user="user"
             :users="users"
             :editUser="editUser"
             :deleteUser="deleteUser"
    />
</template>

<script>
    import UserRow from "./UserRow";
    import UserForm from "./UserForm";

    export default {
        name: "UserList",
        components: {UserForm, UserRow},
        data() {
            return {
                validator: null,
                users: [],
                user: null
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
            this.sortUsers()
        },
        methods: {
            editUser(user) {
                this.user = user
            },

            saveUser(user) {
                if (user.id) {
                    fetch("rest/persons/" + user.id, {
                        body: JSON.stringify(user),
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        }
                    }).then(response => {
                            if (response.ok) {
                                const index = this.users.findIndex(item => item.id === user.id)

                                response.json()
                                    .then(
                                        data => this.users.set(index, data)
                                    )
                                this.cleanForm()
                                this.sortUsers()
                            } else {
                                alert('error')
                            }
                        }
                    );

                } else {
                    fetch("rest/persons/", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(user)
                    }).then(response => {
                            if (response.ok) {
                                response.json()
                                    .then(
                                        data => this.users.push(data)
                                    )
                                this.cleanForm()
                                this.sortUsers()
                            } else {
                                response.json()
                                    .then(
                                        data => {
                                            this.validator = data
                                        }
                                    )
                            }
                        }
                    );
                }
            },
            deleteUser(user) {
                fetch("rest/persons/" + user.id, {method: "DELETE"})
                    .then(response => {
                            if (response.ok) {
                                this.users.splice(this.users.indexOf(user), 1);
                            } else {
                                alert('not deleted')
                            }
                        }
                    )
                this.cleanForm()
            },
            cleanForm() {
                this.user = {}
                this.validator = {}
            },
            sortUsers() {
                this.users.sort((a, b) => (a.id - b.id));
            }
        },
    }
</script>

<style scoped>

</style>