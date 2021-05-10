package ua.kiev.prog;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private String chatRoom;

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return "Message{" +
                "date=" + date +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                ", chatRoom='" + chatRoom + '\'' +
                '}';
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
