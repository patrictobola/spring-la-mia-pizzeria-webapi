<script>
import { ref } from 'vue'
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

// defineProps({
//   msg: String,

// })
</script>

<template>
  <!-- <h1>{{ msg }}</h1> -->
  <div>
    <h2>List of all pizzas:</h2>
    <ul>
      <li v-for="pizza in pizzas" :key="pizza.id">
        {{ pizza.name }}
      </li>
    </ul>
  </div>
</template>

<style scoped></style>
