package com.study.springcloud.serviceprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ServiceProviderServer {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderServer.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ServiceProviderServer.class);
        Map<String, Object> defProperties =  new HashMap<>();
        defProperties.put("spring.profiles.default", "test");
        app.setDefaultProperties(defProperties);
        ConfigurableApplicationContext configurableApplicationContext = app.run(args);
        Environment env = configurableApplicationContext.getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        ServerProperties serverProperties = configurableApplicationContext.getBean(ServerProperties.class);
        logger.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverProperties.getPort(),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            serverProperties.getPort(),
            env.getActiveProfiles());
    }
}
