package com.study.redis;

import cn.t.util.common.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

/**
 * redis lock
 *
 * @version V1.0
 * @since 2020-12-07 16:29
 **/
@Component
public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private final StringRedisTemplate redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public String computeIfAbsent(String key, long expireInMills, long lockExpireInMills, Supplier<String> supplier) {
        return this.computeIfAbsent(key, generateLockKey(key, lockExpireInMills), getCurrentInstanceId(), expireInMills, lockExpireInMills, supplier);
    }
    /**
     * set if not exist
     * @param key key
     * @param lockKey lockKey
     * @param lockExpireInMills 锁过期时间
     * @param supplier 数据提供方
     * @return true 是否为当前线程设置的值
     */
    private  String computeIfAbsent(String key, String lockKey, String instanceId, long expireInMills, long lockExpireInMills, Supplier<String> supplier) {
        String value = valueOperations.get(key);
        if(StringUtils.isEmpty(value)) {
            logger.info("key: {}, 数据为空, 即将抢占锁资源, lockKey: {}", key, lockKey);
            Boolean success = valueOperations.setIfAbsent(lockKey, instanceId, lockExpireInMills, TimeUnit.MILLISECONDS);
            //获取锁成功
            if(Objects.equals(Boolean.TRUE, success)) {
                redisTemplate.delete(key);
                logger.info("抢占锁成功, key:{}, lockKey: {}, 即将加载数据", key, lockKey);
                value = supplier.get();
                success = valueOperations.setIfAbsent(key, value, expireInMills, TimeUnit.MILLISECONDS);
                if(Objects.equals(Boolean.TRUE, success)) {
                    logger.info("抢占锁成功, 加载数据并更新redis成功, key:{}, lockKey: {}, data: {}", key, lockKey, value);
                    redisTemplate.delete(lockKey);
                    return value;
                } else {
                    logger.info("抢占锁成功, 但数据已被其他线程加载, key:{}, value:{}, lockKey: {}", key, value, lockKey);
                    return valueOperations.get(key);
                }
            } else {
                logger.info("抢占锁失败, key:{}, lockKey: {}, 即将重新尝试", key, lockKey);
                return computeIfAbsentRetry(key, lockKey, instanceId, expireInMills, lockExpireInMills, supplier);
            }
        } else {
            return value;
        }
    }

    private String computeIfAbsentRetry(String key, String lockKey, String instanceId, long expireInMills, long lockExpireInMills, Supplier<String> supplier) {
        //sleep 200 mills
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(Math.min(lockExpireInMills, 200)));
        String value = valueOperations.get(key);
        if(value != null) {
            logger.info("抢占锁失败重试, 等待一下, 数据已被其他线程加载, key:{}, lockKey: {}, data: {}", key, lockKey, value);
            return value;
        }
        if(Objects.equals(Boolean.FALSE, redisTemplate.hasKey(lockKey))) {
            logger.info("抢占锁失败重试, 锁已被释放, 重新抢占锁, key:{}, lockKey: {}", key, lockKey);
            //锁未被占用，重来
            return computeIfAbsent(key, lockKey, instanceId, expireInMills, lockExpireInMills, supplier);
        } else {
            logger.info("抢占锁失败重试, 锁未被释放, 继续重试, key:{}, lockKey: {}", key, lockKey);
            return computeIfAbsentRetry(key, lockKey, instanceId, expireInMills, lockExpireInMills, supplier);
        }
    }

    private String generateLockKey(String key, long expireInMills) {
        return String.format("%s:lock-key:%d", key, expireInMills);
    }

    private static String getCurrentInstanceId() {
        String ip = SystemUtil.getLocalIpV4(true);
        String pid = SystemUtil.getPid();
        String threadName = Thread.currentThread().getName();
        return String.format("ip:%s,pid:%s,threadName:%s", ip, pid, threadName);
    }

    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }
}
