package org.java.spring.db.repo;

import org.java.spring.db.pojo.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer>{

}
