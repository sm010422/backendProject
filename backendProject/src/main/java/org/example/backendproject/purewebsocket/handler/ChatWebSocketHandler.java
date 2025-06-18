package org.example.backendproject.purewebsocket.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

  private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);

    sessions.add(session);

    System.out.println("New WebSocket connection established: " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);

    ChatMessage chatMessage = new ObjectMapper().readValue(message.getPayload(), ChatMessage.class);

    for (WEbSocketSession s : sessions) {
      if (s.isOpen() && !s.getId().equals(session.getId())) {
        // 자바 객체 -> json 문자열
        s.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(chatMessage)));
      }
    }

  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);

    sessions.remove(session);
  }
}
