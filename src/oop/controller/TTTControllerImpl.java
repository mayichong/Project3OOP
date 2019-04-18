package oop.controller;
import java.util.ArrayList;

import oop.others.GameBoard;
import oop.others.UserPlayer;
import oop.others.SecondPlayer;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
public class TTTControllerImpl {
	UserPlayer myself;
	UserPlayer other;
	String data = "";
	HashMap<String,UserPlayer> hmap = new HashMap<String,UserPlayer>();
	
    //Adding elements to HashMap
	
    String globalMarker = "1";
    String compMarker = "C";
    GameBoard board = new GameBoard(3,3,"0");
    public void startNewGame(int numPlayers, int timeoutInSecs){

        //initialize a game board with entry 0
        board.initArray("0");

        //check if numplayer is valid or not

        //if (numPlayers < 1 || numPlayers > 2){
       //     System.out.println("Invalid Input. Please try again.");
       // }

        //Timer, WIP

    }

    public void boardReset() {
    	board.initArray("0");
    }
    public void createPlayer(String username, String marker, int playerNum){

        /**
         * Create a player with specified username, marker,
         * and player number (either 1 or 2)
         *
         * @param username
         * @param marker
         * @param playerNum
         */

        if (playerNum == 1){
            //create user player
            myself = new UserPlayer(username,marker);
            globalMarker = myself.markerChoice(username,marker);

        }else if (playerNum == 2){
            //create second player
            myself = new UserPlayer(username,marker);
            other = new UserPlayer(username,marker);
            compMarker = other.markerChoice(username,marker);
     
        }
    }

    /**
     * Allow user to specify location for marker.
     * Return true if the location is valid and available.
     *
     * @param row Must be valid. 0,1,2
     * @param col Must be valid. 0,1,2
     * @param currentPlayer Must be valid. 1 = player1; 2 = player2
     * @return
     */
    public boolean setSelection(int row, int col, int currentPlayer){


        if (row >= 3 || row < 0){
            return false;
        }

        if (col >= 3 || col < 0){
            return false;
        }

        if (currentPlayer == 1){

            return board.insertInt(row,col,globalMarker);

        }

        if (currentPlayer == 2){
            return board.insertInt(row,col,compMarker);
        }else{
            return false;
        }

    }


    /**
     * Determines if there is a winner and returns the following:
     *
     * 0=no winner / game in progress / not all spaces have been selected;
     * 1=player1;
     * 2=player2;
     * 3=tie/no more available locations
     *
     * @return
     */
    public int determineWinner(){

        int hello = board.checkWin(globalMarker,compMarker);

        return hello;
    }

    /**
     * Return a printable display of current selections.
     * Shows 3x3 (or nxn) board with each players marker.
     *
     * @return
     */
    public String getGameDisplay(){


        System.out.println(board.boardDisplay(globalMarker));
        String cool = "";
        return board.boardDisplay(globalMarker);
    }

    public String please(){

        String lol = board.avaX();
        return lol;
    }

    public boolean checkFullOrNot(){
        return board.checkFull();
    }
    
    public void storePlayerInfo() {
        //Adding elements to HashMap
    	hmap.put(myself.getUserName(),myself);
    }
    
    public void serialize() {
    	try
        {
               FileOutputStream fos = new FileOutputStream("hashmap.ser");
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(hmap);
               oos.close();
               fos.close();
               System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
    }
    public void getPlayerInfo() {
        try
        {
           FileInputStream fis = new FileInputStream("hashmap.ser");
           ObjectInputStream ois = new ObjectInputStream(fis);
           //读取数据
           hmap = (HashMap)ois.readObject();
           
           ois.close();
           fis.close();
        }catch(IOException ioe)
        {
           ioe.printStackTrace();
           return;
        }catch(ClassNotFoundException c)
        {
           System.out.println("Class not found");
           c.printStackTrace();
           return;
        }
        System.out.println("Deserialized HashMap..");
        // Display content using Iterator
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
//           System.out.print("key: "+ mentry.getKey() + " & Value: ");
//           System.out.println(((UserPlayer)mentry.getValue()).getMarker());
           data += mentry.getKey() + "     Marker: " + ((UserPlayer)mentry.getValue()).getMarker() + "\n\n\n";
           
           
        }
    }
    
    public void addWin() {
    	myself.addWin();
    }
    
    public void addLose() {
    	myself.addLose();
    }
    
    public String dataPass() {
    	return data;
    }
}