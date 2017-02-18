package com.hackingchicago.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackingchicago.model.Donor;
 
@RepositoryRestResource
public interface DonorRepository extends CrudRepository<Donor, Long> {

	Donor findByLastName(String string);
 
}
