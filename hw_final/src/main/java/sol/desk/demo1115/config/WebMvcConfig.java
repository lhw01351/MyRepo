package sol.desk.demo1115.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;



@Configuration
public class WebMvcConfig {

    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("customer/login");
        registry.addViewController("/inquiry").setViewName("inquiry/index");
        registry.addViewController("/notice").setViewName("notice/index");
        registry.addViewController("/rent").setViewName("rent/index");
        registry.addViewController("/bicycle").setViewName("bicycle/index");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}