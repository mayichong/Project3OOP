package oop.others;

public class UserPlayer extends Players {
    public UserPlayer (String username,String marker){
        super(username,marker);
    }

    public String markerChoice(String username,String marker){
        return marker;
    }
}
