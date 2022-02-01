package app.api;



import app.model.Game;
import app.model.Message;
import app.model.Player;
import app.server.PlayerOnline;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class WebTestApi {

































    @Autowired
    SimpMessagingTemplate messagingTemplate;

    private PlayerOnline playerOnline = new PlayerOnline();

    @MessageMapping("/all")
    @SendTo("/topic/greetings")
    public Message greeting(Message message)throws Exception{
        return message;
    }
    @MessageMapping("/user")
    public void userChat(Message message)throws Exception {
        messagingTemplate.convertAndSend("/topic/chat/"+message.getTo(),message);
    }

    /**
     * 新连接
     * @param object
     * @return
     * @throws Exception
     */
    @MessageMapping("/into")
    @SendTo("/topic/new_add")
    public Player newPlayerInto(Player object)throws Exception{
        playerOnline.allPlayerOffOnline();
        playerOnline.addPlayer(object);
        return object;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/connect")
    public String playerConnect(Player player){
        System.out.println(player.getId()+"  update！");
        playerOnline.getPlayerById(player.getId()).setState(1);
        return JSONObject.toJSONString(playerOnline.getPlayers());
    }

    @MessageMapping("/move")
    @SendTo("/topic/move")
    public String playerMove(Player player){
        playerOnline.setPotision(player);
        System.out.println(player.getId()+" move!");
        return JSONObject.toJSONString(playerOnline.getPlayerById(player.getId()));
    }
}
