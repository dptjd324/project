package com.example.project.trainer.Chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ChatMessage {
	@Id @GeneratedValue
	private Long messageId;
	@ManyToOne
	@JoinColumn(name="room_id")
	private ChatRoom room;
  	private String sender;//채팅을 보낸 사람
	private String message;// 메세지
   	@Enumerated(EnumType.STRING)
	private MessageType type;
    @CreationTimestamp
	private Date createDate;//채팅발송시간

	public ChatMessage(ChatRoom room, String sender, String message, MessageType type) {
		this.room = room;
		this.sender = sender;
		this.message = message;
		this.type = type;
	}
}