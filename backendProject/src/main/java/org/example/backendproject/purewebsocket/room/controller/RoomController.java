package org.example.backendproject.purewebsocket.room.controller;

import java.util.List;

import org.example.backendproject.purewebsocket.room.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")

pubilc class RoomController {

  private final RoomService roomService;

  @GetMapping
  public List<ChatRoom> getAllRooms() {
    return roomService.findAllRooms();
  }

  @GetMapping("/{roomId}")
  public ChatRoom(@PathVariable String roomId) {
        return roomService.createRoom(roomId)
    }
}
