package ec.com.cryptogateway.ws.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "ec.com.cryptogateway.ws.controller",
        "ec.com.cryptogateway.repository",
        "ec.com.cryptogateway.service"
})
@EnableJpaRepositories
@EntityScan("ec.com.cryptogateway.entity.*")   
public class CryptoGatewayRootApplication {
	

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CryptoGatewayRootApplication.class);
        springApplication.run(args);
    }
}
