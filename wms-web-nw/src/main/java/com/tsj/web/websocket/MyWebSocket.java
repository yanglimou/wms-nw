package com.tsj.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket.ws")
@Slf4j
public class MyWebSocket {
    ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<>();

    @OnMessage
    public void message(String message, Session session) {
        String id = getId(session);
        if (StringUtils.isNotEmpty(id)) {
            log.info("收到客户端【{}】的消息:{}", id, message);
            session.getAsyncRemote().sendText(message);
        }
    }

    @OnClose
    public void close(Session session) {
        log.info("有客户端关闭");
        String id = getId(session);
        if (StringUtils.isNotEmpty(id)) {
            map.remove(id);
        }
    }

    String body = "{\"epc\":\"474B48540000000000000001\",\"textList\":[{\"type\":1,\"value\":\"一次性无菌导尿包（硅处理型）\",\"x\":5,\"y\":5,\"width\":100,\"font\":10,\"bond\":1},{\"type\":1,\"value\":\"规格：16Fr双腔成人型\",\"x\":15,\"y\":5,\"width\":100,\"font\":10,\"bond\":2},{\"type\":1,\"value\":\"厂 家：广州维力医疗器械股份有限公司（原韦士泰）\",\"x\":30,\"y\":5,\"width\":100,\"font\":10,\"bond\":1},{\"type\":3,\"value\":\"GZWSL0001B\",\"x\":30,\"y\":200,\"width\":100,\"font\":10,\"bond\":2}]}";

    @OnOpen
    public void open(Session session) throws IOException {
        String id = getId(session);
        if (StringUtils.isNotEmpty(id)) {
            log.info("客户端【{}】连接", id);
            map.put(id, session);
            session.getAsyncRemote().sendText(body);
        } else {
            session.close();
        }
    }


    @OnError
    public void error(Session session, Throwable throwable) throws IOException {
        log.info("有客户端错误");
        String id = getId(session);
        if (StringUtils.isNotEmpty(id)) {
            session.close();
        }
    }

    private String getId(Session session) {
        List<String> client_id = session.getRequestParameterMap().get("client_id");
        if (CollectionUtils.isNotEmpty(client_id)) {
            return client_id.get(0);
        }
        return null;
    }
}
