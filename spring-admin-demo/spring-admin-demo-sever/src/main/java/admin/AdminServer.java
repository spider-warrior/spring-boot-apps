package admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAdminServer
@SpringBootApplication
public class AdminServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminServer.class)
            .web(WebApplicationType.REACTIVE)
            .run(args);
    }

}
