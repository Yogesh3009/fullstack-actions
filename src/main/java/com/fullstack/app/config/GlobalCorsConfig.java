package com.fullstack.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

	
	@Value("${allowed.origin.url}")
	private String origins;

	@Bean
	public CorsFilter corsFilter() {
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		
		String[] originsList = origins.split(",");
		
		config.setAllowCredentials(true);

		config.setAllowedOrigins(Arrays.asList(originsList));

		config.setAllowedHeaders(Arrays.asList(
				"Origin",
				"Content-Type",
				"Accept"));

		config.setAllowedMethods(Arrays.asList(
				"GET",
				"POST",
				"PUT",
				"OPTIONS",
				"DELETE",
				"PATCH"));

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
