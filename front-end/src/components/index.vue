<template>
  <div>
    <h2>List of all pizzas:</h2>

    <div>
      <label for="search">Search by Name:</label>
      <input type="text" v-model="searchQuery" @input="searchPizzas">
    </div>

    <ul>
      <li v-for="pizza in filteredPizzas" :key="pizza.id">
        <router-link :to="{ name: 'pizza-detail', params: { id: pizza.id } }">
          {{ pizza.name }}
        </router-link>
        <button @click="deletePizza(pizza.id)">Delete</button>
      </li>
    </ul>

    <router-link :to="{ name: 'CreatePizza' }">Create a new Pizza bro!</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pizzas: [],
      searchQuery: '',
    };
  },
  computed: {
    filteredPizzas() {
      return this.pizzas.filter(pizza => pizza.name.toLowerCase().includes(this.searchQuery.toLowerCase()));
    }
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
    async deletePizza(pizzaId) {
      try {
        const response = await fetch(`http://localhost:8080/api/pizzas/${pizzaId}`, {
          method: 'DELETE',
        });

        if (response.ok) {
          this.fetchPizzas();
        } else {
          console.error('Failed to delete pizza.');
        }
      } catch (error) {
        console.error(error);
      }
    },
    searchPizzas() {
    },
  },
};
</script>
