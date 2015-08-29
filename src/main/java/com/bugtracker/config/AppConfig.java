package com.bugtracker.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.bugtracker.*" })
@Import({ WebConfig.class, PersistenceConfig.class })
public class AppConfig {

	@Autowired
	ServletContext servletContext;
	
	@Bean(name = "multipartResolver")
	 public CommonsMultipartResolver getMultipartResolver() {
	  CommonsMultipartResolver fileViewResolver = new CommonsMultipartResolver();
	  fileViewResolver.setMaxUploadSize(1000000);
	  return fileViewResolver;
	 }
//	 
//	 @Bean(name = "fileValidator")
//	 public FileValidator getFileValidator() {
//	  return new FileValidator();
//	 }
	 
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}