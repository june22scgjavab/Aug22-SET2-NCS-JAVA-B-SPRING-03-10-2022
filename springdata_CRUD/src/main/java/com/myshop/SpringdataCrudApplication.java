package com.myshop;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.myshop.dto.CustomerDTO;
import com.myshop.exception.CustomerException;
import com.myshop.service.CustomerService;

@SpringBootApplication
public class SpringdataCrudApplication implements CommandLineRunner {
	@Autowired
	private CustomerService customerService;
	private static final Log LOGGER = LogFactory.getLog(SpringdataCrudApplication.class);
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringdataCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//addCustomer();
	 getCustomer();
	// updateCustomer();
	//deleteCustomer();
	// getAllCustomers();
	}

	private void getAllCustomers() {
	List<CustomerDTO> customerDTOList=customerService.getAllCustomers();
	customerDTOList.forEach(customerDTO->{
		System.out.println(customerDTO.getId()+"\t"+customerDTO.getName()+"\t"+customerDTO.getEmailId());
		
	});
	}

	private void deleteCustomer() {
		try {
			customerService.deleteCustomer(1);
			LOGGER.info(environment.getProperty("Interface.RECORD_DELETE_MESSAGE"));
		} catch (CustomerException e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}

	}

	private void updateCustomer() {
		try {
			customerService.updateCustomer(1, "rama@yahoo.com");
			LOGGER.info(environment.getProperty("Interface.RECORD_UPDATED_MESSAGE"));

		} catch (CustomerException e) {

			LOGGER.error(environment.getProperty(e.getMessage()));
		}

	}

	private void getCustomer() {
		try {
			CustomerDTO customerDTO = customerService.getCustomer(100);
			LOGGER.info(customerDTO);
		} catch (CustomerException e) {

			LOGGER.error(environment.getProperty(e.getMessage()));
		}

	}

	private void addCustomer() {
		CustomerDTO customerDTO = new CustomerDTO(1, "Ram", "ram@gmail.com");
		LOGGER.info("Customer got added with the id " + customerDTO.getId());
		try {
			customerService.addCustomer(customerDTO);
		} catch (CustomerException e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}

	}

}
