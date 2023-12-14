import { createRouter, createWebHistory } from 'vue-router';
import index from '@/components/index.vue';
import PizzaDetail from '@/components/PizzaDetail.vue';
import CreatePizza from '@/components/CreatePizza.vue';

const routes = [
  { path: '/', name: 'index', component: index },
  { path: '/pizzas/:id', name: 'pizza-detail', component: PizzaDetail},
  { path: '/pizzas/create', name: 'CreatePizza', component: CreatePizza},
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
