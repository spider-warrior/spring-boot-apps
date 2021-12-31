package kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * KafkaConsumerApp
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-11-24 19:37
 **/
@SpringBootApplication
public class KafkaConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApp.class, args);
    }

}
