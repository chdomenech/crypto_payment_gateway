package ec.com.cryptogateway.ws.security;

/*
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity*/
public class CryptoGatewayWebfluxSecurityConfig {

    /*@Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("user")
            .roles("USER")
            .build();
        return new MapReactiveUserDetailsService(user);
    }
    
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
    	return http.authorizeExchange()
    		      .anyExchange().authenticated()
    		      .and().formLogin()
    		      .and().build();
    }*/
}