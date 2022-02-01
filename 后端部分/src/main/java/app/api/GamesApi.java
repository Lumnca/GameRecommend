package app.api;


import app.dao.GamesDao;
import app.model.Game;
import app.model.Response;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
