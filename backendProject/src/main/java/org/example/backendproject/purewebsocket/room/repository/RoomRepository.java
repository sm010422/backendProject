package org.example.backendproject.purewebsocket.room.repository;

import java.util.List;
import java.util.Optional;

import org.example.backendproject.purewebsocket.room.entity.ChatRoom;

public interface RoomRepository extends JpaRepository<ChatRoom, Long> {

  private final RoomRepository roomRepository;

  // 채팅방 ID로 채팅방을 찾는 메소드
  Optional<ChatRoom> findByRoomId(String roomId);

  // 채팅방 이름으로 채팅방을 찾는 메소드
  Optional<ChatRoom> findByRoomId(String name);

  // 모든 채팅방을 조회하는 메소드
  List<ChatRoom> findAll();
}
