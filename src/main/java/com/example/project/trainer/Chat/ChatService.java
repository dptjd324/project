package com.example.project.trainer.Chat;

import com.example.project.trainer.Chat.dto.ChatMessageRequest;
import com.example.project.trainer.Chat.dto.ChatMessageResponse;
import com.example.project.trainer.Chat.dto.ChatRoomResponse;
import com.example.project.trainer.Chat.entity.ChatRoom;

import java.util.List;

public interface ChatService {
    void createChatRoom(String loginId, String trainerId);
    ChatRoom getChatRoom(String loginId, String trainerId);
    ChatMessageResponse saveMessage(ChatMessageRequest message);
    List<ChatRoomResponse> findAllChatRooms(String loginId);
    List<ChatRoomResponse> findAllChatRoomsTrainerVer(String trainerId);
    List<ChatMessageResponse> findAllChatMessages(String roomId);
}
