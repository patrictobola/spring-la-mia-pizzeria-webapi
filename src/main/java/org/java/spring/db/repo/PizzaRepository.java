package org.java.spring.db.repo;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
	List<Pizza> findByNameContainingIgnoreCase(String searchTerm);
	
	
	
//	Soft Delete
	List<Pizza> findByDeleted(boolean deletedValue);
	@Modifying
	@Transactional
	@Query("UPDATE Pizza p SET p.deleted = true WHERE p.id = :id")
	void softDeleteById(@Param("id") int id);

}
