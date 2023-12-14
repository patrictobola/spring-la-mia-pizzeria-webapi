<template>
    <div>
        <h2>Pizza Details:</h2>

        <p>{{ pizza.name }}</p>
        <p>{{ pizza.description }}</p>
        <img :src="pizza.photo" alt="Pizza's photo">
        <ul>
            <li v-for="ingredient in pizza.ingredients">{{ ingredient.name }}</li>
        </ul>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            pizza: [],
        };
    },
    mounted() {
        this.fetchPizza();
    },
    methods: {
        async fetchPizza() {
            try {
                const pizzaId = this.$route.params.id;
                console.log(pizzaId)
                const response = await fetch('http://localhost:8080/api/pizzas/' + pizzaId);
                const pizza = await response.json();
                this.pizza = pizza;
            } catch (error) {
                console.error(error);
            }
        },
    },
};
</script>
  