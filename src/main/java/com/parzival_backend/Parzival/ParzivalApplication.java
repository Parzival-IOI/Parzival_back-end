package com.parzival_backend.Parzival;

import com.parzival_backend.Parzival.config.RsaKeyProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ParzivalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParzivalApplication.class, args);
	}

}
