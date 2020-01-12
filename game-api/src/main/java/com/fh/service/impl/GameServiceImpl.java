package com.fh.service.impl;

import com.fh.mapper.GameMapper;
import com.fh.model.DataTableResult;
import com.fh.model.Game;
import com.fh.model.GameQuery;
import com.fh.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Override
    public DataTableResult queryGameList(GameQuery gameQuery) {

        //1.查询总条数
        Long count = gameMapper.queryGameCount(gameQuery);

        //2.查询当前页数据
        List<Game> gameList = gameMapper.queryGameList(gameQuery);

        DataTableResult dataTableResult = new DataTableResult(gameQuery.getDraw(),count,count,gameList);

        return dataTableResult;
    }

    @Override
    public void addGame(Game game) {
        gameMapper.addGame(game);
    }

    @Override
    public Game getGameById(Integer id) {
        return gameMapper.getGameById(id);
    }

    @Override
    public void updateGame(Game game) {
        gameMapper.updateGame(game);
    }

    @Override
    public void deleteGame(Integer id) {
        gameMapper.deleteGame(id);
    }

    public static void main(String[] args) {

    }
}
