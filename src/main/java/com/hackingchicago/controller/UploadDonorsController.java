package com.hackingchicago.controller;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.hackingchicago.model.Donor;
import com.hackingchicago.repository.DonorRepository;

@RestController
@RequestMapping("/loadDonors")
public class UploadDonorsController {
	@Autowired
	DonorRepository repository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Donor> loadDonors() {
		 List<Donor> donors = getDonors();
		 System.out.println("donors length in csv="+donors.size());
		// fetch all customers
		System.out.println("Saving Donors");
		System.out.println("-------------------------------");
		for (Donor donor : donors) {
			System.out.println(donor.toString());
			repository.save(donor);
		}
		 System.out.println("number of donors in db = "+repository.count());
		 System.out.println("");	
		 return donors;
	}
	
	public <T> List<T> loadObjectList(Class<T> type, String fileName) {
	    try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource(fileName).getFile();
	        MappingIterator<T> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        System.err.println("Error occurred while loading object list from file " + fileName);
	        return Collections.emptyList();
	    }
	}
	
	@Bean
	public List<Donor> getDonors() {
		return loadObjectList(Donor.class, "/donors.csv");
	}
}
