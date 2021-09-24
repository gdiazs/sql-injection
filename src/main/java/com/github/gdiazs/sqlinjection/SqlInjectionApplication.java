package com.github.gdiazs.sqlinjection;

import com.github.gdiazs.sqlinjection.filters.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqlInjectionApplication {

	@Bean
	public FilterRegistrationBean<SecurityFilter> loggingFilter(){
		var securityFilter = new FilterRegistrationBean<SecurityFilter>();
		securityFilter.setFilter(new SecurityFilter());
		securityFilter.addUrlPatterns("/dashboard/*");
		return securityFilter;
	}

	public static void main(String[] args) {
		SpringApplication.run(SqlInjectionApplication.class, args);
	}

}
