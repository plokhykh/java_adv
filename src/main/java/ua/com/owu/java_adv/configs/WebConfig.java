package ua.com.owu.java_adv.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry){
        String folderWithAvatars = "File:///"+System.getProperty("user.home")+ File.separator+"avatars"+File.separator;
        registry.addResourceHandler("/user/avatar/**")
                .addResourceLocations(folderWithAvatars);

    }
}
