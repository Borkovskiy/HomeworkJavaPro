package ua.kiev.prog.chatRoom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class ChatRoomList {

    private static final ChatRoomList chatRoomList = new ChatRoomList();

    private final Gson gson;
    private final List<ChatRoom> list = new LinkedList<>();

    public static ChatRoomList getInstance() {
        return chatRoomList;
    }

    private ChatRoomList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(ChatRoom chatRoom) {
        list.add(chatRoom);
    }

    public synchronized List<ChatRoom> getList() {
        return list;
    }

}
