package us.devguild.rxfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by vcarvalho on 4/6/15.
 */
@SpringBootApplication()
@ImportResource("cache-context.xml")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerApplication.class);
        app.run(args);
    }



}
