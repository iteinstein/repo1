package com.fh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJedisPool {

    //最大连接数
    private static final int MAX_TOTAL = 5;

    //最小空闲连接数
    private static final int MIN_IDLE = 3;

    //最大空闲连接数
    private static final int MAX_IDLE = 4;

    //从连接池中获取连接时是否检测该连接是否可用
    private static final boolean TEST_ON_BORROW = true;

    //向连接池归还连接时是否检测该连接是否可用
    private static final boolean TEST_ON_RETURN = true;

    private static final String REDIS_SERVER_IP = "192.168.241.129";

    private static final int REDIS_SERVER_PORT = 6379;

    private static JedisPool jedisPool;

    static{
        initPool();
    }

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIN_IDLE);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setTestOnReturn(TEST_ON_RETURN);
        config.setMaxWaitMillis(100L);
        jedisPool = new JedisPool(config,REDIS_SERVER_IP,REDIS_SERVER_PORT);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
