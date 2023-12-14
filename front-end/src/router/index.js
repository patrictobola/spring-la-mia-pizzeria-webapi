import { createRouter, createWebHistory } from 'vue-router';
import index from '@/components/index.vue';
import PizzaDetail from '@/components/PizzaDetail.vue';

const routes = [
  { path: '/', component: index },
  { path: '/pizzas/:id', name: 'pizza-detail', component: PizzaDetail, props: true },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
