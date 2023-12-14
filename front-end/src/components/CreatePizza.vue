<template>
    <div>
        <h2>Create a New Pizza</h2>
        <form @submit.prevent="createPizza">
            <label for="name">Name:</label>
            <input type="text" v-model="pizzaData.name" required>

            <label for="description">Description:</label>
            <textarea v-model="pizzaData.description" required></textarea>

            <label for="price">Price:</label>
            <input type="number" v-model="pizzaData.price" required>

            <label for="photo">Photo URL:</label>
            <input type="text" v-model="pizzaData.photo" required>

            <button type="submit">Create Pizza</button>
        </form>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            pizzaData: {
                name: '',
                description: '',
                price: '',
                photo: '',
            },
        };
    },
    methods: {
        async createPizza() {
            try {
                const response = await fetch('http://localhost:8080/api/pizzas', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(this.pizzaData),
                });
                const responseData = await response.json();

                console.log(responseData);

                this.pizzaData = {
                    name: '',
                    description: '',
                    price: '',
                    photo: '',
                };
                this.$router.push({ name: 'index' });
            } catch (error) {
                console.error('Error creating pizza:', error);
            }
        },
    },
};
</script>