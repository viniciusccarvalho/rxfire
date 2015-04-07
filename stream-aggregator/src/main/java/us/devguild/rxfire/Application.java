package us.devguild.rxfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

/**
 * Created by vcarvalho on 12/28/14.
 */

@SpringBootApplication
@ImportResource("/cache-context.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }


    @Bean
    CacheFactoryBean cacheFactoryBean() {
        return new CacheFactoryBean();
    }

    
    


}
