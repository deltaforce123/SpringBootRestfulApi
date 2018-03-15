package assessment.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

@Component
public class ApplicationConfig {

    public static final int MAX_FILE_SIZE_MB = 512;
    public static final int MAX_REQUEST_SIZE_MB = 512;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(MAX_FILE_SIZE_MB + "MB");
        factory.setMaxRequestSize(MAX_REQUEST_SIZE_MB + "MB");
        return factory.createMultipartConfig();
    }

}
