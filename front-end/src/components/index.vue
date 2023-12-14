<template>
  <div>
    <h2>List of all pizzas:</h2>
    <ul>
      <li v-for="pizza in pizzas" :key="pizza.id">
        <router-link :to="{ name: 'pizza-detail', params: { id: pizza.id } }">
          {{ pizza.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pizzas: [],
    };
  },
  mounted() {
    this.fetchPizzas();
  },
  methods: {
    async fetchPizzas() {
      try {
        const response = await fetch('http://localhost:8080/api/pizzas');
        const pizzas = await response.json();
        this.pizzas = pizzas;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
