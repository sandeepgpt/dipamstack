package com.example.demo.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (uploadDir != null && !uploadDir.isEmpty()) {
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:" + uploadDir);
        } else {
            throw new IllegalStateException("Upload directory is not configured properly.");
        }
    }
}
