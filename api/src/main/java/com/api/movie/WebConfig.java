package com.api.movie;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
    {
        "classpath:/META-INF/resources/",
        "classpath:/resources/",
        "classpath:/static/", "classpath:/public/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        try {
            registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
        } catch (Exception e) {
          e.getStackTrace();
        }
        System.out.println("Application Running...");
    }

    @Override
    public void configureContentNegotiation( ContentNegotiationConfigurer configurer )
    {
        configurer.defaultContentType( MediaType.APPLICATION_JSON );
    }
}