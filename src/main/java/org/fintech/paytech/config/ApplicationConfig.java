package org.fintech.paytech.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UserDetailService userDetailService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //private final UserRepository userRepository;

    /*public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    public ApplicationConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    /*@Bean // No need after custom UserDetailService
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }*/

    // Used authentication provider in project must be initialized in the security config
    /*@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // DaoAuthenticationProvider is used to authenticate users with a database
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bcryptPasswordEncoder());
        return provider;
    }*/

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager is used to authenticate users with a database
    /*@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }*/
    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
        AuthenticationManagerBuilder auth = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder);
        return auth.build();
    }*/
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
        AuthenticationManagerBuilder auth = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
        return auth.build();
    }
}
