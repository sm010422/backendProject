package org.example.backendproject.purewebsocket.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.example.backendproject.purewebsocket.dto.ChatMessage;

public class ChatWebSocketHandler extends TextWebSocketHandler {

  private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

  private final ObjectMapper=new ObjectMapper();

  private final Map<String, WebSocketSession> rooms = new ConcurrentHashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);

    sessions.add(session);

    System.out.println("New WebSocket connection established: " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // super.handleTextMessage(session, message);

    ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

    String roomID= chatMessage.getRoomId();

    if (!rooms.containsKey(roomID) {
      rooms.put(roomID, ConcurrentHashMap.newKeySet()); //없으면 새로운 방을 생성
    }
    rooms.get(roomID.add(session); //해당 방에 세션 추가

    for(WebSocketSession s : rooms.get(roomID) {
      if (s.isOpen()) {
        // 자바 객체 -> json 문자열
        s.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));

        System.out.println("전송된 메세지 = " + chatMessage.getContent());
      }
    }
    // // for (WebSocketSession s : sessions) {
    //     if (s.isOpen()){
    //         //자바 객체 -> json 문자열
    //         s.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
    //
    //         System.out.println("전송된 메세지 = "+chatMessage.getMessage());
    //     }
    // }

  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    // super.afterConnectionClosed(session, status);

    sessions.remove(session);

    // 연결이 해제되면 소속되어있는 방에서 제거
    for (Set<WebSocketSession> room : rooms.values()) {
      room.remove(session);
    }
  }
}
