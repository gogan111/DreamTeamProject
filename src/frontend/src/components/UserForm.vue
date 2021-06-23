<template>
    <div>Name:
        <input @keyup.enter="save"
               type="text"
               v-model="user.name"
               placeholder="Enter name"
        />
        <i> {{ validator.nameError }}</i>
    </div>
    <div>Surname:
        <input @keyup.enter="save"
               type="text"
               v-model="user.surname"
               placeholder="Enter surname"
        />
        <i> {{ validator.surnameError }}</i>
    </div>
    <div>Age:
        <input @keyup.enter="save"
               type="number"
               v-model="user.age"
               placeholder="Enter age"
        />
        <i> {{ validator.ageError }}</i>
    </div>
    <div>Email:
        <input @keyup.enter="save"
               type="email"
               v-model="user.email"
               placeholder="example@email.com"
        />
        <i> {{ validator.emailError }}</i>
    </div>

    <div>
        <button @keyup.enter="save" v-on:click="save">Save</button>
    </div>

</template>

<script>
    export default {
        name: "UserForm",
        components: {},
        props: ['users', 'userAttr', 'validatorAttr', 'saveUser'],
        data() {
            return {
                validator: {
                    nameError: '',
                    surnameError: '',
                    ageError: '',
                    emailError: '',
                },

                user: {
                    name: '',
                    surname: '',
                    age: '',
                    email: '',
                    id: ''
                }
            }
        },
        watch: {
            userAttr(newVal) {
                this.user.id = newVal.id
                this.user.name = newVal.name
                this.user.surname = newVal.surname
                this.user.age = newVal.age
                this.user.email = newVal.email
            },

            validatorAttr(newVal) {
                this.validator.nameError = newVal.nameError
                this.validator.surnameError = newVal.surnameError
                this.validator.ageError = newVal.ageError
                this.validator.emailError = newVal.emailError
            }
        },
        methods: {
            save() {
                this.saveUser(this.user)
            },
        }
    }
</script>

<style scoped>
    i {
        color: #c12127;
        font-size: large;
    }
</style>