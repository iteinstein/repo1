package com.fh.util;

import com.fh.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testSet(){
        Game game = new Game();
        game.setGname("大多数");
        game.setGprice(new BigDecimal(11.11));
        redisTemplate.opsForValue().set("name",game);
        Object o = redisTemplate.opsForValue().get("name");
        System.out.println(o);
        redisTemplate.opsForValue().set("name2","ddsdsd");
        Object name2 = redisTemplate.opsForValue().get("name2");
        System.out.println(name2);
        redisTemplate.opsForHash().put("hash","student1",game);
        System.out.println(redisTemplate.opsForHash().get("hash","student1"));

        List<Game> gameList = new ArrayList<>();
        for(int i = 0 ; i < 3; i ++ ){
            Game game1 = new Game();
            game1.setGname("是是是"+i);
            game1.setLogin("ddd" + i);
            gameList.add(game1);
        }
        redisTemplate.opsForValue().set("name",gameList);
        List<Game> games = (List<Game>) redisTemplate.opsForValue().get("name");
        games.forEach(x-> System.out.println(x));
    }

}
