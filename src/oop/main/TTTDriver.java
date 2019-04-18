package oop.main;
import oop.controller.TTTControllerImpl;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;
import java.io.*;

public class TTTDriver {
    public static void main(String[] args) throws IOException {
        TTTControllerImpl controller = new TTTControllerImpl();
        System.out.println("Welcome to Tic Tac Toe game!\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        int a = 1;
        int b = 1;
        String marker1 = "1";
        String marker2 = "2";
        String coord = "3";
        String splited []  = new String [3];
        int rowNum = -1;
        int colNum = 0;
        int rowNum2 = 0;
        int colNum2 = 0;
        int count = 0;
        boolean getNum = false;
        boolean isOver = false;
        boolean gameOver = false;
        boolean markerSelection = false;
        boolean notimeout = true;
        int timeout = 0;
        long currentTime;
        while (isOver == false){

            System.out.println("What game mode would you like to play?\n");
            System.out.println("1.Play against computer");
            System.out.println("2.Play against another player");
            System.out.println("3.Quit");
            String choice = input.next();

            if (choice.equals("1")) {

                System.out.println("You want to play against computer.");

                while(a == 1){
                    System.out.println("Please enter time for each player:");
                    try{
                        timeout = input.nextInt();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input. Please try again.");
                        input.next();
                    }

                }

                controller.startNewGame(Integer.parseInt(choice),timeout);
                System.out.println("Please input your username:");
                String username = input.next();

                while (markerSelection == false){
                    System.out.println("Please input a marker (1 character):");
                    String marker = input.next();

                    if (marker.length() != 1){
                        System.out.println("Invalid input. Marker only accepts one character long.");
                    }else if (marker.equals("C")){
                        System.out.println("You can not choose the same marker as computer. Try again!");
                    }
                    else{
                        controller.createPlayer(username,marker,1);
                        System.out.println(username + ", you chose marker " + marker + ", you have " + timeout +" seconds to decide each turn, let's begin!");

                        //The game begins!

                        while(gameOver == false){
                            currentTime = System.currentTimeMillis();
                            controller.getGameDisplay();
                            System.out.println("Please enter the row number and column number with a space in between (for example: X X), 8 to quit):");
                            getNum = false;
                            if (timeout <= 0){
                                notimeout = true;
                            }else{
                                notimeout = false;
                            }
                            while(System.currentTimeMillis() - currentTime < timeout*1000 || notimeout == true){
                                if (br.ready()){
                                    coord = br.readLine();
                                    splited = coord.split("\\s+");

                                    if (splited.length == 1 && splited[0].equals("8")){
                                        System.out.println("You quit the game. Computer won!");
                                        System.exit(0);
                                    }else if (splited.length != 2){
                                        System.out.println("Invalid Input. Please try again!");
                                        getNum = false;
                                    }else{
                                        try{
                                            rowNum = Integer.parseInt(splited[0]);
                                            colNum = Integer.parseInt(splited[1]);
                                            getNum = true;
                                            break;
                                        }catch (NumberFormatException e){
                                            System.out.println("Not a number, Please try again!");
                                            getNum = false;
                                        }
                                    }
                                }
                            }


                            if (controller.setSelection(rowNum,colNum,1) == true || getNum == false){
                                if (getNum == true) {
                                    count++;
                                    System.out.println("You entered location " + rowNum + ", " + colNum);
                                    controller.setSelection(rowNum, colNum, 1);

                                    if (controller.determineWinner() == 1) {
                                        controller.getGameDisplay();
                                        System.out.println(username + ", You won!");
                                        isOver = true;
                                        break;
                                    }
                                }
                                System.out.println("Computer's Turn!");
                                String computer = controller.please();
                                char x = computer.charAt(0);
                                char y = computer.charAt(1);
                                int xNum = x - '0';
                                int yNum = y- '0';
                                controller.setSelection(xNum,yNum,2);

                                if (controller.determineWinner() == 2){
                                    controller.getGameDisplay();
                                    System.out.println("Computer won!");
                                    isOver = true;
                                    System.exit(0);
                                }


                                if (controller.determineWinner() == 0){
                                    controller.getGameDisplay();
                                    System.out.println("It is a draw!");
                                    isOver = true;
                                    System.exit(0);
                                }

                            }else{
                                System.out.println("Location unavailable. Please try again.");
                            }
                        }
                        break;
                    }
                }

            } else if (choice.equals("2")){

                System.out.println("You want to play against another player.");


                while(a == 1){
                    System.out.println("Please enter time for each player:");
                    try{
                        timeout = input.nextInt();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input. Please try again.");
                        input.next();
                    }

                }

                controller.startNewGame(Integer.parseInt(choice),timeout);
                System.out.println("Please input player1 username:");
                String player1 = input.next();


                while(a==1) {
                    System.out.println("Please input player1 marker:");
                    marker1 = input.next();

                    if (marker1.length() != 1) {
                        System.out.println("Invalid input. Marker only accepts one character long.");
                    } else {
                        break;
                    }
                }

                System.out.println("Please input player2 username:");
                String player2 = input.next();

                while(b==1) {
                    System.out.println("Please input player2 marker:");
                    marker2 = input.next();

                    if (marker2.length() != 1) {
                        System.out.println("Invalid input. Marker only accepts one character long.");
                    }

                    else if (marker2.equals(marker1)) {
                        System.out.println("You can not enter the same marker as player 1! Try again!");
                    }

                    else {
                        break;
                    }
                }

                System.out.println("Player 1, " + player1 + ", you chose marker " + marker1 + ".");
                System.out.println("Player 2, " + player2 + ", you chose marker " + marker2 + ".");
                controller.createPlayer(player1,marker1,1);
                controller.createPlayer(player2,marker2,2);
                System.out.println("Player 1 go first.");
                while(gameOver == false){
                    currentTime = System.currentTimeMillis();
                    controller.getGameDisplay();
                    System.out.println("Player 1's turn.");

                    System.out.println("Please enter the row number and column number with a space in between (for example: X X), 8 to quit):");
                    getNum = false;
                    if (timeout <= 0){
                        notimeout = true;
                    }else{
                        notimeout = false;
                    }

                    while(System.currentTimeMillis() - currentTime < timeout*1000 || notimeout == true){
                        if (br.ready()){
                            coord = br.readLine();
                            splited = coord.split("\\s+");

                            if (splited.length == 1 && splited[0].equals("8")){
                                System.out.println("You quit the game. Player 2 won!");
                                System.exit(0);
                            }else if (splited.length != 2){
                                System.out.println("Invalid Input. Please try again!");
                                getNum = false;
                            }else{
                                try{
                                    rowNum = Integer.parseInt(splited[0]);
                                    colNum = Integer.parseInt(splited[1]);
                                    getNum = true;
                                    break;
                                }catch (NumberFormatException e){
                                    System.out.println("Not a number. Please try again!");
                                    getNum = false;
                                }
                            }
                        }
                    }

                    if (controller.setSelection(rowNum,colNum,1) == true || getNum ==false){
                        if (getNum == true){
                            System.out.println("You entered location " + rowNum +", " + colNum);
                            controller.setSelection(rowNum,colNum,1);
                        }

                        if (controller.determineWinner() == 1) {
                            controller.getGameDisplay();
                            System.out.println(player1 + ", you won!");
                            System.exit(0);

                        }else{

                            if (controller.checkFullOrNot() == true){
                                controller.getGameDisplay();
                                System.out.println("It is a draw!");
                                System.exit(0);
                            }

                            while (gameOver == false){
                                controller.getGameDisplay();
                                System.out.println("Player2's turn.");
                                currentTime = System.currentTimeMillis();
                                System.out.println("Please enter the row number and column number with a space in between (for example: X X), 8 to quit):");
                                getNum = false;
                                if (timeout <= 0){
                                    notimeout = true;
                                }else{
                                    notimeout = false;
                                }

                                while(System.currentTimeMillis() - currentTime < timeout*1000 || notimeout == true){
                                    if (br.ready()){
                                        coord = br.readLine();
                                        splited = coord.split("\\s+");

                                        if (splited.length == 1 && splited[0].equals("8")){
                                            System.out.println("You quit the game. Player 1 won!");
                                            System.exit(0);
                                        }else if (splited.length != 2){
                                            System.out.println("Invalid Input. Please try again!");
                                            getNum = false;
                                        }else{
                                            try{
                                                rowNum = Integer.parseInt(splited[0]);
                                                colNum = Integer.parseInt(splited[1]);
                                                getNum = true;
                                                break;
                                            }catch (NumberFormatException e){
                                                System.out.println("Not a number");
                                                getNum = false;
                                            }
                                        }
                                    }
                                }


                                if (controller.setSelection(rowNum,colNum,2) == true || getNum ==false){
                                    if (getNum == true){
                                        System.out.println("You entered location " + rowNum2 +", " + colNum2);
                                        controller.setSelection(rowNum,colNum,2);
                                    }

                                    if (controller.determineWinner() == 2){
                                        controller.getGameDisplay();
                                        System.out.println(player2 +", you won!");
                                        System.exit(0);
                                    }
                                    break;
                                }else{
                                    System.out.println("Location unavailable. Please try again.");
                                }

                            }
                        }

                    }else{
                        System.out.println("Location unavailable. Please try again.");
                    }

                }
            }
            else if (choice.equals("3")){
                System.out.println("See you next time!");
                break;
            }else{
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
