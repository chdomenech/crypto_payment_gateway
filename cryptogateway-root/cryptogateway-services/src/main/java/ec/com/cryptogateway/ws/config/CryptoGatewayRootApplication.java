package ec.com.cryptogateway.ws.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "ec.com.cryptogateway.ws.controller",
        "ec.com.cryptogateway.repository",
        "ec.com.cryptogateway.service"
})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ec.com.cryptogateway"})
@EnableJpaRepositories("ec.com.cryptogateway.repository")   
@EntityScan("ec.com.cryptogateway.entity")   
public class CryptoGatewayRootApplication {
	

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CryptoGatewayRootApplication.class);
        springApplication.run(args);
    }
}