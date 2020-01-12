package com.fh.controller;

import com.fh.model.DataTableResult;
import com.fh.model.Game;
import com.fh.model.GameQuery;
import com.fh.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    //分页条件查询游戏信息
    @RequestMapping(value = "game/query",method = RequestMethod.POST)
    public Map<String,Object> queryGameList(@RequestBody GameQuery gameQuery){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            DataTableResult dataTableResult = gameService.queryGameList(gameQuery);
            resultMap.put("code",200);
            resultMap.put("data",dataTableResult);
            resultMap.put("message","请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",500);
            resultMap.put("data",null);
            resultMap.put("message","运行异常");
        }
        return resultMap;
    }

    //新增游戏
    @RequestMapping(value = "game",method = RequestMethod.POST)
    public Map<String,Object> addGame(@RequestBody Game game){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            gameService.addGame(game);
            resultMap.put("code",200);
            resultMap.put("data",null);
            resultMap.put("message","请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",500);
            resultMap.put("data",null);
            resultMap.put("message","运行异常");
        }
        return resultMap;
    }

    //通过游戏id获取单个游戏信息
    @RequestMapping(value = "game/{id}",method = RequestMethod.GET)
    public Map<String,Object> getGameById(@PathVariable("id") Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            Game game = gameService.getGameById(id);
            resultMap.put("code",200);
            resultMap.put("data",game);
            resultMap.put("message","请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",500);
            resultMap.put("data",null);
            resultMap.put("message","运行异常");
        }
        return resultMap;
    }

    //修改游戏
    @RequestMapping(value = "game",method = RequestMethod.PUT)
    public Map<String,Object> updateGame(@RequestBody Game game){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            gameService.updateGame(game);
            resultMap.put("code",200);
            resultMap.put("data",null);
            resultMap.put("message","请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",500);
            resultMap.put("data",null);
            resultMap.put("message","运行异常");
        }
        return resultMap;
    }

    //通过游戏id删除游戏
    @RequestMapping(value = "game/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteGame(@PathVariable("id") Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            gameService.deleteGame(id);
            resultMap.put("code",200);
            resultMap.put("data",null);
            resultMap.put("message","请求成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code",500);
            resultMap.put("data",null);
            resultMap.put("message","运行异常");
        }
        return resultMap;
    }


}
