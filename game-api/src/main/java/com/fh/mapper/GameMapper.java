package com.fh.mapper;

import com.fh.model.Game;
import com.fh.model.GameQuery;

import java.util.List;

public interface GameMapper {

    //查询总条数
    Long queryGameCount(GameQuery gameQuery);

    //查询当前页数据
    List<Game> queryGameList(GameQuery gameQuery);

    //新增游戏
    void addGame(Game game);

    //通过id获取单个游戏对象
    Game getGameById(Integer id);

    //修改游戏
    void updateGame(Game game);

    //删除游戏
    void deleteGame(Integer id);

}
