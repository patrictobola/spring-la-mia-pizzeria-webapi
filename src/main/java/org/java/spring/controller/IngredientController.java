package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

	@Autowired IngredientRepository ingredientRepository;
	
	@GetMapping("/ingredients")
	public String ingredients(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("ingredient", new Ingredient());
		return "ingredients-list";
	}
	@PostMapping("/ingredients/save")
	public String addIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientRepository.save(ingredient);
		return "redirect:/ingredients";
	}
	
	@GetMapping("/ingredients/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {
		ingredientRepository.deleteById(id);
		return "redirect:/ingredients";
	}
}	
