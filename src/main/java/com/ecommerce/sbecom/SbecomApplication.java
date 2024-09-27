package com.ecommerce.sbecom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.io.File;

@SpringBootApplication
public class SbecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbecomApplication.class, args);
	}

}

@Component
class StartUpRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		String imageFolderPath = "src/main/resources/images";
		File imageFolder = new File(imageFolderPath);
		if(!imageFolder.exists()) {
			boolean success = imageFolder.mkdir();
			if(success) {
				System.out.println("Directory created: " + imageFolder.getAbsolutePath());
			}else {
				System.out.println("Failed to create directory: " + imageFolder.getAbsolutePath());
			}
		}else {
			System.out.println("Directory already exists: " + imageFolder.getAbsolutePath());
		}
	}
}
