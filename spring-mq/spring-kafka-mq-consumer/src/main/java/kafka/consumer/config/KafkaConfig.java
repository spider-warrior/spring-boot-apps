package kafka.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

/**
 * KafkaConfig
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-11-25 14:17
 **/
@Configuration
public class KafkaConfig {

    /**
     * 将json字符串转换为对象
     * 不支持将字符串转换为字符串(即消息内容为字符串, listener入参为listener)
     */
    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }

    /**
     * 当backoff尝试完后会继续抓取后面可处理的消息，处理提交offset成功后将丢失问题数据
     * 如果想在处理逻辑不丢失数据就设置无限期尝试消费
     *
     * 当BackOff为BackOffExecution.STOP状态时执行DeadLetterPublishingRecoverer#accept方法然后从map中移除问题分区
     * 如果map大小为0则从ThreadLocal中移除map
     */
    @Bean
    public SeekToCurrentErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        return new SeekToCurrentErrorHandler(
            new DeadLetterPublishingRecoverer(template), new FixedBackOff(5000, FixedBackOff.UNLIMITED_ATTEMPTS));
    }

}
