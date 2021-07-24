package com.alkemy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findById(long id);
	
	Category findByNombre(String nombre);

}
