package org.example.backendproject.purewebsocket.room.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat_rooms")

public class ChatRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String roomId;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String owner;

  // 생성자, 게터, 세터 등 필요한 메소드 추가

  public ChatRoom() {
  }

  public ChatRoom(String name, String description, String owner) {
    this.name = name;
    this.description = description;
    this.owner = owner;
  }
}
