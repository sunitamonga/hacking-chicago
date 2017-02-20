package com.hackingchicago;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.hackingchicago.repository.DonorRepository;

@SpringBootApplication
public class InvestApplication {
	private static String bucketName = "https://s3.amazonaws.com/hackchicago/donors.csv";
	private static String key = "donors.csv";

	@Resource
	DonorRepository donorRepo;;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(InvestApplication.class, args);
		System.out.println("*****Hello Invest Chicago****");
		InvestApplication app = new InvestApplication();
		//app.loadfromS3();


	}
	
	
	public void loadfromS3() throws IOException {
		//AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
			AmazonS3Client s3Client = new AmazonS3Client();        
			
		try {
			System.out.println("Downloading an object");
//			S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));
//			System.out.println("Content-Type: " + s3object.getObjectMetadata().getContentType());
//			displayTextInputStream(s3object.getObjectContent());
//
//			// Get a range of bytes from an object.
//
//			GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, key);
//			rangeObjectRequest.setRange(0, 10);
//			S3Object objectPortion = s3Client.getObject(rangeObjectRequest);

			String s3Url = s3Client.getResourceUrl("hackchicago", "donors.csv");
			System.out.println("s3Url = "+s3Url);
			System.out.println("Printing bytes retrieved.");
			//displayTextInputStream(objectPortion.getObjectContent());

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which" + " means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means" + " the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

	private static void displayTextInputStream(InputStream input) throws IOException {
		// Read one text line at a time and display.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;

			System.out.println("    " + line);
		}
		System.out.println();
	}
	// @Bean
	// public CommandLineRunner demo(DonorRepository repository) {
	// return (args) -> {
	// // save a couple of donors
	// // repository.save(new Donor("Sunita", "Monga", null, null, 0, null,
	// null, null, null, null));
	//
	//
	// List<Donor> donors = getDonors();
	// System.out.println("donors length in csv="+donors.size());
	// // fetch all customers
	// System.out.println("Saving Donors");
	// System.out.println("-------------------------------");
	// for (Donor donor : donors) {
	// System.out.println(donor.toString());
	// repository.save(donor);
	// }
	// System.out.println("number of donors in db = "+repository.count());
	// System.out.println("");
	//
	// // fetch an individual customer by ID
	// Donor customer = repository.findOne(1L);
	// if (customer != null) {
	// System.out.println("Customer found with findOne(1L):");
	// System.out.println("--------------------------------");
	// System.out.println(customer.toString());
	// System.out.println("");
	//
	// }
	// // fetch customers by last name
	// System.out.println("Customer found with findByLastName('Bauer'):");
	// System.out.println("--------------------------------------------");
	// Donor specialDonor = repository.findByLastName("Matthews");
	// if (specialDonor != null)
	// System.out.println(specialDonor.toString());
	//
	// System.out.println("");
	// System.out.println("number of donors = "+repository.count());
	// };
	// }

	// public <T> List<T> loadObjectList(Class<T> type, String fileName) {
	// try {
	// CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	// CsvMapper mapper = new CsvMapper();
	// File file = new ClassPathResource(fileName).getFile();
	// MappingIterator<T> readValues =
	// mapper.reader(type).with(bootstrapSchema).readValues(file);
	// return readValues.readAll();
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.err.println("Error occurred while loading object list from file "
	// + fileName);
	// return Collections.emptyList();
	// }
	// }
	//
	// @Bean
	// public List<Donor> getDonors() {
	// return loadObjectList(Donor.class, "donors.csv");
	// }
}
