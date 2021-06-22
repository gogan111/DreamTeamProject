<template>
    <div>Name:
        <input type="text" v-model="user.name"
               placeholder="Enter name">
        <i> {{ validator.nameError }}</i>
    </div>
    <div>Surname:
        <input type="text" v-model="user.surname"
               placeholder="Enter surname">
        <i> {{ validator.surnameError }}</i>
    </div>
    <div>Age:
        <input type="number" v-model="user.age"
               placeholder="Enter age">
        <i> {{ validator.ageError }}</i>
    </div>
    <div>Email:
        <input type="email" v-model="user.email"
               placeholder="example@email.com">
        <i> {{ validator.emailError }}</i>
    </div>

    <div>
        <button v-on:click="save">Save</button>
    </div>

    <h1>List of Users</h1>

    <UserRow v-for="user in users"
             :key="user.id"
             :user="user"
             :edit-user="updateForm"
             :delete-user="deleteUser"
    />

</template>

<script>
    import UserRow from "./UserRow";

    export default {
        name: "UserForm",
        components: {UserRow},

        data() {
            return {
                validator: {
                    nameError: '',
                    surnameError: '',
                    ageError: '',
                    emailError: '',
                },
                users: [],
                user: {
                    name: '',
                    surname: '',
                    age: '',
                    email: '',
                    id: ''
                }
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
            save() {
                this.addUser(this.user)

                this.user.id = ''
                this.user.name = ''
                this.user.surname = ''
                this.user.age = ''
                this.user.email = ''
            },
            updateForm(user) {

                this.user.id = user.id
                this.user.name = user.name
                this.user.surname = user.surname
                this.user.age = user.age
                this.user.email = user.email
            },
            addUser(user) {
                if (user.id) {
                    fetch("rest/persons/" + user.id, {
                        body: JSON.stringify(user),
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        }
                    }).then(response => {
                            if (response.ok) {
                                this.users.splice(this.users.indexOf(user), 1);
                                response.json()
                                    .then(
                                        data => this.users.push(data)
                                    )

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
                            } else {
                                alert('error')
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
                this.user.id = ''
                this.user.name = ''
                this.user.surname = ''
                this.user.age = ''
                this.user.email = ''
            }
        }
    }
</script>

<style scoped>
    i {
        color: #c12127;
        font-size: large ;
    }
</style>