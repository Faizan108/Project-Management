package com.project.management.AppConfig;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AppConfiguration {
	
	@Bean
	SecurityFilterChain requestChain(HttpSecurity http) throws Exception {
		http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/auth/**").permitAll())
				.authorizeHttpRequests((request-> request.requestMatchers(new AntPathRequestMatcher("/auth/hello")).permitAll().anyRequest().authenticated()))
		.addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
		.csrf(csrf -> csrf.disable());
//		.cors(cors ->cors.configurationSource((CorsConfigurationSource) corsConfigurationSource()));
		
		return http.build();
	}
	
	private CorsConfiguration corsConfigurationSource()
	{
		return new CorsConfiguration() {
			public CorsConfiguration corsConfiguration(HttpServletRequest request) {
				CorsConfiguration cors = new CorsConfiguration();
				cors.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000",
						"http://localhost:5173"
						));
				cors.setAllowedMethods(Collections.singletonList("*"));
				cors.setAllowedHeaders(Collections.singletonList("*"));
				cors.setAllowCredentials(true);
				cors.setExposedHeaders(Arrays.asList("Authorization"));
				cors.setMaxAge(3600L);
				
				return cors;
			}
		};
	}

}
