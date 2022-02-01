package app.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.Random;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(
            ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 获取例如 wss://localhost/websocket/1 订阅地址
        // 中的最后一个用户 id 参数作为用户的标识,
        // 为实现发送信息给指定用户做准备
        String uri = request.getURI().toString();
        String uid = String.valueOf(new Random().nextInt(1000));
        System.out.println(uid);
        return () -> uid;
        //以上自定义处理器用于设置用户唯一的标识为用户的 uid，用户只要在连接 websocket 时，
        // 在订阅地址 wss://localhost/websocket/ 后加上用户的 id，即可作为用户的唯一标识。
    }

}

