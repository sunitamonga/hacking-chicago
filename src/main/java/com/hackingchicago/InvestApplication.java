package com.hackingchicago;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.hackingchicago.model.Donor;
import com.hackingchicago.repository.DonorRepository;

@SpringBootApplication
public class InvestApplication {

	@Resource
	DonorRepository donorRepo;;

	public static void main(String[] args) {
		SpringApplication.run(InvestApplication.class, args);
		System.out.println("*****Hello Invest Chicago****");

	}


//	@Bean
//	public CommandLineRunner demo(DonorRepository repository) {
//		return (args) -> {
//			// save a couple of donors
//			// repository.save(new Donor("Sunita", "Monga", null, null, 0, null, null, null, null, null));
//	
//
//			 List<Donor> donors = getDonors();
//			 System.out.println("donors length in csv="+donors.size());
//			// fetch all customers
//			System.out.println("Saving Donors");
//			System.out.println("-------------------------------");
//			for (Donor donor : donors) {
//				System.out.println(donor.toString());
//				repository.save(donor);
//			}
//			 System.out.println("number of donors in db = "+repository.count());
//			 System.out.println("");
//
//			// fetch an individual customer by ID
//			Donor customer = repository.findOne(1L);
//			if (customer != null) {
//				System.out.println("Customer found with findOne(1L):");
//				System.out.println("--------------------------------");
//				System.out.println(customer.toString());
//				System.out.println("");
//
//			}
//			// fetch customers by last name
//			System.out.println("Customer found with findByLastName('Bauer'):");
//			System.out.println("--------------------------------------------");
//			Donor specialDonor = repository.findByLastName("Matthews");
//			if (specialDonor != null)
//			System.out.println(specialDonor.toString());
//
//			System.out.println("");
//			 System.out.println("number of donors = "+repository.count());
//		};
//	}
	
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
		return loadObjectList(Donor.class, "donors.csv");
	}
}
