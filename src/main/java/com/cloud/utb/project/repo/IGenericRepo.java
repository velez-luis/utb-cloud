package com.cloud.utb.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID>{
	
	List<T> findByIsDeleted(boolean id);
	
}
