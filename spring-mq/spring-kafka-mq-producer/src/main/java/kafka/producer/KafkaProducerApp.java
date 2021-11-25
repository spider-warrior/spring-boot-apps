package kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * KafkaProducerApp
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-11-24 19:37
 **/
@RestController
@SpringBootApplication
public class KafkaProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApp.class, args);
    }

}
