package com.springboot.point_of_sale.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8084");
        localServer.setDescription("Server URL in Local environment");

        Contact contact = new Contact();
        contact.setEmail("your.email@example.com");
        contact.setName("POS System Admin");
        contact.setUrl("https://www.example.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Point of Sale API Documentation")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints for the Point of Sale System.")
                .termsOfService("https://www.example.com/terms")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}