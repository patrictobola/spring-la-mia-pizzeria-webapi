package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.repo.IngredientRepository;
import org.java.spring.db.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping("/")
	public String homepage(Model model, @RequestParam(required = false) String q) {
		List<Pizza> result = q == null ? pizzaRepository.findByDeleted(false) : pizzaRepository.findByNameContainingIgnoreCase(q);
		model.addAttribute("pizzas", result);
		model.addAttribute("q", q == null ? "" : q);
		return "index";
	}

	@GetMapping("/pizza/{id}")
	public String showPizzaDetails(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
		Pizza selectedPizza = getPizzaById(id);
		model.addAttribute("pizza", selectedPizza);
	    model.addAttribute("offers", selectedPizza.getOffers());
	    
	    redirectAttributes.addFlashAttribute("selectedPizza", selectedPizza);

	   
		return "pizzaDetails";
	}

	private Pizza getPizzaById(int id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", new Pizza());
		return "create-edit";
	}

	@PostMapping("/pizza/create")
	public String newPizza(Model model, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Errori di validazione:");
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println("Campo: " + error.getField() + ". Messaggio: " + error.getDefaultMessage());
			}
			model.addAttribute("pizza", formPizza);
			return "create-edit";
		}

		pizzaRepository.save(formPizza);
		return "redirect:/";
	}

	@GetMapping("/pizza/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		model.addAttribute("ingredients", ingredients);
		Pizza selectedPizza = getPizzaById(id);
		model.addAttribute("pizza", selectedPizza);
		return "create-edit";
	}

	@PostMapping("/pizza/edit/{id}")
	public String storePizza(Model model, @Valid @ModelAttribute("pizza") Pizza editPizza,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("Errori di validazione:");
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println("Campo: " + error.getField() + ", messaggio: " + error.getDefaultMessage());
			}
			model.addAttribute("pizza", editPizza);
			return "create-edit";
		}
		pizzaRepository.save(editPizza);
		return "redirect:/";
	}
	
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		pizzaRepository.softDeleteById(id);
		return "redirect:/";
	}

}
