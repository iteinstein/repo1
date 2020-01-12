package com.fh.util;


public class RedisTest {

    public static void main(String[] args) {
        for(int i = 0 ; i < 6 ; i ++){
            JedisUtil.set("name",i+"");
        }

    }

}
