package com.liang;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RedisStudy {

    public  final static Jedis JEDIS = new Jedis("101.43.65.207",6379);

    @Test
    public void demo(){
        JEDIS.ping();
    }

    @Test
    public void demo1(){
        try {
            JEDIS.set("name","lucy");
            Random random = new Random(6);
            System.out.println(random.ints());

        }finally {
            JEDIS.flushDB();
        }

    }

}
