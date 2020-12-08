package com.study.redis.test;

import cn.t.util.common.RandomUtil;
import com.study.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2020-12-07 21:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockTest.class);

    private static final AtomicInteger INDEX = new AtomicInteger(0);
    private static final AtomicInteger THREAD_INDEX = new AtomicInteger(0);
    @Autowired
    private RedisLock redisLock;

    @Test
    public void lockTest() throws Exception {
        int tryTimes = 10;
        for(int i=0; i<tryTimes; i++) {
            Thread testThread = new Thread(() -> {
                System.out.println("============================================================================================================");
                int workerCount = 20;
                CountDownLatch mainCountDownLatch = new CountDownLatch(1);
                CountDownLatch workerCountDownLatch = new CountDownLatch(workerCount);
                CountDownLatch taskCountDownLatch = new CountDownLatch(workerCount);
                for(int j=0; j<workerCount; j++) {
                    new Thread(new UpdateTask(redisLock, mainCountDownLatch, workerCountDownLatch, taskCountDownLatch), generateWorkerThreadName()).start();
                }
                //主线程等待所有任务线程就绪
                logger.info("等待所有任务线程就绪");
                try {workerCountDownLatch.await();} catch (InterruptedException e) {e.printStackTrace();}
                //开始所有任务线程
                logger.info("开始所有任务线程, 并等待所有任务执行完毕");
                mainCountDownLatch.countDown();
                try {taskCountDownLatch.await();} catch (InterruptedException e) {e.printStackTrace();}
                LockSupport.parkNanos(TimeUnit.NANOSECONDS.convert(2,TimeUnit.SECONDS));
            }, "test-thread-" + i);
            testThread.start();
            testThread.join();
        }
        System.out.println("main exit");
    }

    private static String generateWorkerThreadName() {
        return "redis-worker:" + THREAD_INDEX.getAndIncrement();
    }
    static class UpdateTask implements Runnable {
        private final RedisLock redisLock;
        private final CountDownLatch mainCountDownLatch;
        private final CountDownLatch workerCountDownLatch;
        private final CountDownLatch taskCountDownLatch;
        @Override
        public void run() {
            workerCountDownLatch.countDown();
            try { mainCountDownLatch.await();} catch (InterruptedException e) {e.printStackTrace();}
            String key = "abc";
            int expireInMills = 1000;
            int lockExpireInMills = 500;
            String value = redisLock.computeIfAbsent(key, expireInMills, lockExpireInMills,  () -> {
                //模拟加载延迟
                LockSupport.parkNanos(TimeUnit.NANOSECONDS.convert(RandomUtil.randomInt(0, 1000),TimeUnit.MILLISECONDS));
                return String.valueOf(INDEX.getAndIncrement());
            });
            logger.info("结果: key: {}, value: {}", key, value);
            taskCountDownLatch.countDown();
        }

        public UpdateTask(RedisLock redisLock, CountDownLatch mainCountDownLatch, CountDownLatch workerCountDownLatch, CountDownLatch taskCountDownLatch) {
            this.redisLock = redisLock;
            this.mainCountDownLatch = mainCountDownLatch;
            this.workerCountDownLatch = workerCountDownLatch;
            this.taskCountDownLatch = taskCountDownLatch;
        }
    }


}
