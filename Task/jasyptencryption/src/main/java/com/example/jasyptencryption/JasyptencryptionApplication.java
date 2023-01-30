package com.example.jasyptencryption;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class JasyptencryptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasyptencryptionApplication.class, args);
	}

}
