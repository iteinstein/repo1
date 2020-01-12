package com.fh.service;

import com.fh.model.DataTableResult;
import com.fh.model.Game;
import com.fh.model.GameQuery;

import java.util.List;

public interface GameService {

    //分页条件查询游戏信息
    DataTableResult queryGameList(GameQuery gameQuery);

    //新增游戏
    void addGame(Game game);

    //通过id获取单个游戏对象
    Game getGameById(Integer id);

    //修改游戏
    void updateGame(Game game);

    //删除游戏
    void deleteGame(Integer id);

}
