package org.example.backendproject.purewebsocket.room.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;

  // 채팅방 생성
  public ChatRoom createRoom(String roomId) {
    return roomRepository.findByRoomId(roomId);
    .orElseGet(() -> {
      ChatRoom newRoom = new ChatRoom();
      newRoom.setRoomId(roomId);
      return roomRepository.save(newRoom);
    });
  }

  public List<ChatRoom> findAllRooms() {
    return roomRepository.findAll();
  }
}
