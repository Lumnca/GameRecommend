package app.api;


import app.dao.GamesDao;
import app.model.Game;
import app.model.Response;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
@RestController
@CrossOrigin
public class GamesApi {

    @Autowired
    GamesDao gamesDao;
    @GetMapping("/getGames")
    public List<Game> getAllGames(){
        return gamesDao.getAllGames();
    }

    @PostMapping("/addGame")
    public Response addGame(@RequestBody Game game){

        if(gamesDao.addGame(game)>0){
            return new Response(200,"添加成功！");
        }
        else{
            return new Response(500,"添加失败！");
        }
    }

    @PostMapping("/delGame/{id}")
    public Response delGame(@PathVariable("id")Integer id){
        if(gamesDao.deleteGame(id)>0){
            return new Response(200,"删除成功！");
        }
        else{
            return new Response(500,"删除失败！");
        }
    }

    @PostMapping("/updateGame")
    public Response updateGame(@RequestBody Game game){
        if(gamesDao.updateGame(game)>0){
            return new Response(200,"修改成功!");
        }
        else{
            return new Response(500,"修改失败!");
        }
    }
    @GetMapping("/file")
    public Response file() throws UnsupportedEncodingException {
        String conf = this.getClass().getResource("/fdfs_client.properties").getPath();
        System.out.println(conf);
        if (conf.contains("classpath:")) {
            String path = URLDecoder.decode(getClass().getProtectionDomain().getCodeSource().getLocation().toString(),
                    "UTF-8");
            path = path.substring(6);
            conf = conf.replace("classpath:", URLDecoder.decode(path, "UTF-8"));
        }
        return new Response(200,conf);
    }
    @PostMapping("/addGood/{id}")
    public Response addGood(@PathVariable("id")Integer id){
        if(gamesDao.addGood(id)>0){
            return new Response(200,"success!");
        }
        else{
            return new Response(500,"error!");
        }
    }
}
