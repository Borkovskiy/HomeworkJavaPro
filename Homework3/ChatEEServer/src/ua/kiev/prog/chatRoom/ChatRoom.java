package ua.kiev.prog.chatRoom;


public class ChatRoom {
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ChatRoom{" +
                "name='" + name + '\'' +
                '}';
    }
}
