package com.fh.util;

import redis.clients.jedis.Jedis;

public class JedisUtil {

    private static Jedis getJedis(){
        return MyJedisPool.getJedis();
    }

    public static void set(String key,String value){
        Jedis jedis = getJedis();
        jedis.set(key,value);
    }

    public static String get(String key){
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        return value;
    }

    public static void del(String ... key){
        Jedis jedis = getJedis();
        jedis.del(key);
        jedis.close();
    }

    public static boolean exists(String key){
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        jedis.close();
        return exists;
    }

}
