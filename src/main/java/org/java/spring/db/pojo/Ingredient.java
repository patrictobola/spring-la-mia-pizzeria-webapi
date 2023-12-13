package org.java.spring.db.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredient")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "L'ingrediente non può essere vuoto")
	private String name;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizzas;

    @PreRemove
    private void removeIngredientFromPizzas() {
        for (Pizza pizza : pizzas) {
            pizza.getIngredients().remove(this);
        }
    }
	public Ingredient() {	
		
	}
	public Ingredient(String name) {
		setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pizza> getPizza() {
		return pizzas;
	}

	public void setPizza(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	

}
