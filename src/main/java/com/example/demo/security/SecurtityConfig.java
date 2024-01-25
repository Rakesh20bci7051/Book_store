package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;

@Configuration
//@EnableWebMvc
@EnableWebSecurity
public class SecurtityConfig {

	
	
	

    @Autowired
    private JwtAuthenticationEntryPoint point;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
  
    
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/auth/**").permitAll().
                		requestMatchers(HttpMethod.GET,"/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       
       http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
   

   
    
    @Bean
    public FilterRegistrationBean coresFilter()
    {
    	UrlBasedCorsConfigurationSource obj= new UrlBasedCorsConfigurationSource();
    	CorsConfiguration  corsCofiguration=new CorsConfiguration();
    	corsCofiguration.setAllowCredentials(true);
    	corsCofiguration.addAllowedOriginPattern("*");
    	corsCofiguration.addAllowedHeader("Authorization");
    	corsCofiguration.addAllowedHeader("Content-Type");
    	corsCofiguration.addAllowedHeader("Accept");
        corsCofiguration.addAllowedMethod("POST");
        corsCofiguration.addAllowedMethod("GET");
        corsCofiguration.addAllowedMethod("DELETE");
        corsCofiguration.addAllowedMethod("PUT");
        corsCofiguration.addAllowedMethod("OPTIONS");
        corsCofiguration.setMaxAge(3600L);
    	obj.registerCorsConfiguration("/**", corsCofiguration);
    	FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(obj));
    	bean.setOrder(-110);
    	return bean;
    	
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
}
