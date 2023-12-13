package org.java.spring;

import java.util.List;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaSecurityApplication implements CommandLineRunner{

	private static final String IMG_URL = "https://picsum.photos/seed/picsum/50";
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaSecurityApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		roleService.save(new Role("ADMIN"));
		roleService.save(new Role("USER"));
		
		List<Role> roles = roleService.findAll();
		
		String psw = AuthConf.passwordEncoder().encode("sama");
		
		userService.save(new User("admin", psw , roles.get(0)));
		userService.save(new User("user", psw , roles.get(1)));
		
		
		ingredientService.save(new Ingredient("Mozzarella"));
		ingredientService.save(new Ingredient("Pomodoro"));
		ingredientService.save(new Ingredient("Salame"));
		ingredientService.save(new Ingredient("Cicoria"));
		ingredientService.save(new Ingredient("Roba bella"));
		ingredientService.save(new Ingredient("Calamari"));
		
		List<Ingredient> ingredients = ingredientService.findAll();

		
		pizzaService.save(new Pizza("Margherita", "Pomodoro, mozzarella", IMG_URL, 9.90f, ingredients.get(1),ingredients.get(2),ingredients.get(4)));
		pizzaService.save(new Pizza("Diavola", "Pomodoro, mozzarella, salami", IMG_URL, 9.90f));
		pizzaService.save(new Pizza("Verdure", "Mozzarella, verdure (ma dai?)", IMG_URL, 9.90f));
		pizzaService.save(new Pizza("Insalata di pollo", "Non ci crederai mai.... INSALATA DI POLLO!", IMG_URL, 9.90f));
		pizzaService.save(new Pizza("Pizza X", "Pomodoro, mozzarella e altri ingredienti segreti", IMG_URL, 9.90f));
		pizzaService.save(new Pizza("Napoli", "Pomodoro, Alici, Prezzemolo", IMG_URL, 9.90f));
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		discountService.save(new Discount("2023-10-15", "2024-01-10", "offertona numero 1!" , pizzas.get(0)));
		discountService.save(new Discount("2023-11-15", "2024-01-10", "offertona numero 2!" , pizzas.get(1)));
		discountService.save(new Discount("2023-11-15", "2024-01-10", "offertona numero 2bis!" , pizzas.get(1)));
		discountService.save(new Discount("2023-12-15", "2024-01-20", "offertona numero 3!" , pizzas.get(2)));
		discountService.save(new Discount("2023-10-20", "2024-01-30", "offertona numero 4!" , pizzas.get(3)));
		
	}

}
