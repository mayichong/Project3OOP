package oop.others;

import java.io.Serializable;

public class Players implements Serializable{
    private String username;
    private String marker;
    private Integer win = 0;
    private Integer lose =0;
    
    public Players (String username,String marker){
        this.username = username;
        this.marker = marker;
        this.win = win;
        this.lose = lose;
    }
    
    public String getUserName() {
    	return username;
    }
    
    public String getMarker() {
    	return marker + "    Win: " + win + "     Lose: " + lose;
    }
    
    public void addWin() {
    	win= win + 1;
    }
    
    public void addLose() {
    	lose = lose + 1;
    }

}
