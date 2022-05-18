package com.tsj.web.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket.ws")
@Slf4j
public class MyWebSocket {
    class Printer {
        private String clientId;
        private String highFlag;
        private Session session;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getHighFlag() {
            return highFlag;
        }

        public void setHighFlag(String highFlag) {
            this.highFlag = highFlag;
        }

        public Session getSession() {
            return session;
        }

        public void setSession(Session session) {
            this.session = session;
        }
    }


    private static final ConcurrentHashMap<String, Printer> map = new ConcurrentHashMap<>();

    public static void send(String id, String body) {
        Printer printer = map.get(id);
        if (printer != null && printer.session.isOpen()) {
            try {
                printer.session.getBasicRemote().sendText(body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map getPrinter() {
        Map result = new HashMap();
        List high = new ArrayList<>();
        List low = new ArrayList<>();
        map.values().stream().forEach(printer -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientId", printer.clientId);
            jsonObject.put("highFlag", printer.highFlag);
            if ("1".equals(printer.highFlag)) {
                high.add(jsonObject);
            } else {
                low.add(jsonObject);
            }
        });
        result.put("high", high);
        result.put("low", low);
        return result;
    }

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


    @OnOpen
    public void open(Session session) throws IOException {
        String id = getId(session);
        String highFlag = getHighFlag(session);
        if (StringUtils.isNotEmpty(id)) {
            log.info("客户端【{}】连接", id);
            Printer printer = new Printer();
            printer.setClientId(id);
            printer.setHighFlag(highFlag);
            printer.setSession(session);
            map.put(id, printer);
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
        List<String> client_ids = session.getRequestParameterMap().get("client_id");
        if (CollectionUtils.isNotEmpty(client_ids)) {
            return client_ids.get(0);
        }
        return null;
    }

    private String getHighFlag(Session session) {
        List<String> high_flags = session.getRequestParameterMap().get("high_flag");
        if (CollectionUtils.isNotEmpty(high_flags)) {
            return high_flags.get(0);
        }
        return null;
    }
}
