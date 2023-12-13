package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(){
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
}
