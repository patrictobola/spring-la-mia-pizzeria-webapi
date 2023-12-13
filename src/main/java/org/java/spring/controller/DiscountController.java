package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.DiscountPizzaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiscountController {

	
	@Autowired
	private PizzaService pizzaServ;
	
	@Autowired
	private DiscountService discountServ;
	
	
	@GetMapping("/pizza/discount")
    public String getDiscountForm(Model model, @ModelAttribute("selectedPizza") Pizza selectedPizza) {
        List<Pizza> pizzas = pizzaServ.findAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("selectedPizza", selectedPizza);
        model.addAttribute("discountPizzaDTO", new DiscountPizzaDTO());
        return "create-offer";
    }
    @PostMapping("/pizza/discount")
    public String createDiscount(@ModelAttribute("discountPizzaDTO") DiscountPizzaDTO discountPizzaDTO) {

        int pizzaId = discountPizzaDTO.getPizza_id();
        Pizza pizza = pizzaServ.findById(pizzaId);

        Discount discount = new Discount(discountPizzaDTO.getStartingDate(), discountPizzaDTO.getEndingDate(),
            discountPizzaDTO.getName(), pizza);
        
        discountServ.save(discount);

        return "redirect:/";
    }
    
    @GetMapping("/pizza/discount/edit/{discountId}")
    public String getEditDiscountForm(@PathVariable("discountId") int id, Model model) {
        try {
            Discount discount = discountServ.findById(id);
            List<Pizza> pizzas = pizzaServ.findAll();
            Pizza selectedPizza = discount.getPizza();
            
            DiscountPizzaDTO discountPizzaDTO = new DiscountPizzaDTO();
            discountPizzaDTO.setPizza_id(selectedPizza.getId());
            discountPizzaDTO.setName(discount.getName());
            discountPizzaDTO.setStartingDate(discount.getStartingDate());
            discountPizzaDTO.setEndingDate(discount.getEndingDate());

            model.addAttribute("discount", discount);
            model.addAttribute("pizzas", pizzas);
            model.addAttribute("selectedPizza", selectedPizza);
            model.addAttribute("discountPizzaDTO", discountPizzaDTO);

        } catch (Exception e) {
            
            e.printStackTrace(); 
        }

        return "create-offer";
    }

    @PostMapping("/pizza/discount/edit/{discountId}")
    public String editDiscount(
            @PathVariable("discountId") int id,
            @ModelAttribute("discountPizzaDTO") DiscountPizzaDTO discountPizzaDTO) {
        try {
            Discount existingDiscount = discountServ.findById(id);

            existingDiscount.setStartingDate(discountPizzaDTO.getStartingDate());
            existingDiscount.setEndingDate(discountPizzaDTO.getEndingDate());
            existingDiscount.setName(discountPizzaDTO.getName());

            int pizzaId = discountPizzaDTO.getPizza_id();
            Pizza pizza = pizzaServ.findById(pizzaId);
            existingDiscount.setPizza(pizza);

            discountServ.save(existingDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    
}
