package com.jobPortal.Job.Portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
@EnableScheduling
public class JobPortalApplication {
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis((Predicate<RequestHandler>) RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths((Predicate<String>) PathSelectors.any()) // Adjust package as needed
//				.build()
//				.apiInfo(apiInfo()).useDefaultResponseMessages(false);
//	}
//
//	@Bean
//	public ApiInfo apiInfo() {
//		final ApiInfoBuilder builder = new ApiInfoBuilder();
//		return builder.build();
//
//	}

	public static void main(String[] args) {



		ApplicationContext context=SpringApplication.run(JobPortalApplication.class, args);
	}

}
