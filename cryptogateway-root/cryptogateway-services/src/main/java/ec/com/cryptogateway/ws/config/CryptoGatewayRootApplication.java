package ec.com.cryptogateway.ws.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {
        "ec.com.cryptogateway"
})
@ComponentScan(basePackages = {"ec.com.cryptogateway"})
@EnableJpaRepositories("ec.com.cryptogateway.repository")   
@EntityScan("ec.com.cryptogateway.entity")  
public class CryptoGatewayRootApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CryptoGatewayRootApplication.class);
        springApplication.run(args);
    }
}