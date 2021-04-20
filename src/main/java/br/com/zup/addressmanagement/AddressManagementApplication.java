package br.com.zup.addressmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AddressManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressManagementApplication.class, args);
	}

}
