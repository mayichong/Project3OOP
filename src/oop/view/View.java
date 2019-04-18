package oop.view;
import java.io.*;
import java.util.HashMap;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import oop.controller.TTTControllerImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import javafx.scene.media.*;
public class View {
	boolean markerInputFormat = true;
	int timerVal;
	int x;
	TTTControllerImpl controller = new TTTControllerImpl();
	boolean firstTime = true;
	boolean isOver = false;
	String player1MarkerVal;
	int timeout = 0;
	String playerNum;
	String playerInputVal;
	private BorderPane root;
	private Scene scene; 
	String splited []  = new String [3];
	private Text statusNode;
    private final int windowWidth = 800;
    private final int windowHeight = 600;
	String markerTest;
	public View() {
		this.root = new BorderPane();
		this.scene = new Scene(root, windowWidth, windowHeight);
		this.statusNode = new Text("no status");
		this.root.setTop(this.buildSetupPane());
	}
	
	public Scene getMainScene() {
		return this.scene;
	}
	
	/**
	 * The setup pane is where a user can give input
	 * for the initialization of the 2D array.
	 * 
	 * @return
	 */
	
	 Text sizeLabel = new Text("Welcome to Tic Tac Toe game!");
	    Text defaultValLabel = new Text("Please select one of the following game mode:");
	    Text inputName1 = new Text("Please input player1 name:");
	    Text inputName2 = new Text("Please input player2 name:");
	    Text sign = new Text("You want to play against computer.");
	    Text player1Marker = new Text("Please input player 1 marker:");
	    Text player2Marker = new Text("Please input player 2 marker:");
	    Text timerText = new Text("Please input time in each turn (in seconds): ");
	    Text gameStartText = new Text("Game Starts!");
	    Text player1Move = new Text("Player 1's turn!");
	    Text player2Move=  new Text("Player 2's turn!");
	    Text playerInput = new Text("Please enter the row number and column number with a space in between (for example: X X):");
	    Text player2Input = new Text("Please enter the row number and column number with a space in between (for example: X X):");
	    Text insertSuccess = new Text("Insert successfully!");
	    Text error = new Text("location unavailable. Please try again!");
	    Text markerError = new Text("Invalid input. Please enter again!");
	    
	    
	    Text win1 = new Text("Player1 Won!");
	    Text winC = new Text("Computer Won!");
	    Text win2 = new Text("Player2 Won!");
	    Text draw = new Text("It is a draw!");
	    Text display = new Text(controller.getGameDisplay());
	    
	    Text timeoutError = new Text("Timeout invalid. Please try again!");
	    TextField defaultVal = new TextField();
        TextField username = new TextField();
        TextField player2 = new TextField();
        TextField player1MarkerField = new TextField();
        TextField player2MarkerField = new TextField();
        TextField timer = new TextField();
        TextField player1InputLocation = new TextField();
        TextField player2InputLocation = new TextField();
        Button GameRecord = new Button ("Record");
        Button markerInputButton = new Button ("Input");
        Button emojiClick = new Button("emoji");
        Button button2 = new Button("Initialize Array");
        Button button1 = new Button("Continue"); 
        Button quit = new Button("Quit");
        Button afterName = new Button("Play");
        Button afterMarker = new Button("Submit");
        Button afterLocation = new Button("Submit");
        Button playAgain = new Button("Play again");
        Button afterPlayer1 = new Button("Submit");
        Button afterPlayer2 = new Button("Submit");
        Button goBack = new Button("Go Back");
        Button quit2 = new Button("Quit");
	public GridPane buildSetupPane() {
		display.getStyleClass().add("array");
        EventHandler<MouseEvent> quitHandler = new EventHandler<MouseEvent>() {
        	@Override 
        	public void handle(MouseEvent e) { 
        		controller.serialize();
                System.exit(0);
            } 
        }; 

        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        
        //Setting size for the pane 
        gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        controller.getPlayerInfo();
        beginning();
        gridPane.add(display, 0, 1);
        
         EventHandler<MouseEvent> playAgainHandler = new EventHandler<MouseEvent>() { 
             @Override
             public void handle(MouseEvent e) {
            	 display.getStyleClass().add("array");
            	gridPane.getChildren().remove(display);
            	gridPane.getChildren().remove(win1);
            	gridPane.getChildren().remove(playAgain);
            	gridPane.getChildren().clear();
             	beginning();
             	controller.boardReset();
             	display = new Text(controller.getGameDisplay());
             	gridPane.add(display, 0, 1);
             	isOver = false;
             	firstTime = true;
             } 
          };
           
          EventHandler<MouseEvent> goBackHandler = new EventHandler<MouseEvent>() { 
              @Override 
              public void handle(MouseEvent e) {
            	  display.getStyleClass().add("array"); 
             	beginning();
              } 
           };
           
         EventHandler<MouseEvent> submitInfo = new EventHandler<MouseEvent>() {
        	 @Override 
             public void handle(MouseEvent e) {
        		 gridPane.getChildren().remove(markerError);
        		 gridPane.getChildren().remove(timeoutError);
        		 display.getStyleClass().add("array");
        		 if (playerNum.equals("1")) {
        			 if (player1MarkerField.getText().length() == 1 && player1MarkerField.getText().isEmpty() == false && timer.getText().isEmpty() == false && username.getText().isEmpty() == false) {
        				 
        				 try{
                             int timerVal = Integer.parseInt(timer.getText());
                             
                         }catch (NumberFormatException e1){
                        	 
                             gridPane.add(timeoutError, 0, 10);
                         }
        				 startGameComputer(username.getText(),player1MarkerField.getText(),timer.getText());
        			 }else {
        				 gridPane.add(markerError, 0, 10);
        				 playAgainst("1"); 
        			 }
 
        			 
        		 }else if (playerNum.equals("2")){
        			 
        			 	if (player2MarkerField.getText().length() == 1 && player1MarkerField.getText().length() == 1 && player1MarkerField.getText().isEmpty() == false && player2MarkerField.getText().isEmpty() == false && timer.getText().isEmpty() == false && username.getText().isEmpty() == false && player2.getText().isEmpty() == false) {
        				 
        				 try{
                             timerVal = Integer.parseInt(timer.getText());
                             
                         }catch (NumberFormatException e1){
                        	 
                             gridPane.add(timeoutError, 0, 10);
                         }
        				 startGamePlayer(username.getText(), player2.getText(), player1MarkerField.getText(), player2MarkerField.getText(), timer.getText());
        			 }else {
        				 gridPane.add(markerError, 0, 10);
        				 playAgainst("2"); 
        			 }

        		 }         		 
       	 }
         };
         
         EventHandler<MouseEvent> submitLocation = new EventHandler<MouseEvent>() {
        	 @Override 
             public void handle(MouseEvent e) {
        		 display.getStyleClass().add("array");
        		 gridPane.getChildren().remove(display);
        		 gridPane.getChildren().remove(error);
        		 
        		 playerInputVal = player1InputLocation.getText();
        		 
        		 if (checkValid(playerInputVal) == 1) {
        			 display.getStyleClass().add("array");
        			 if (controller.checkFullOrNot() == true){
        				 display.getStyleClass().add("array");
        				Media media = new Media(Paths.get("src/drawSound.Wav").toUri().toString());
    	  				MediaPlayer mediaPlayer = new MediaPlayer(media);
    	  				mediaPlayer.play();
   		  				gridPane.add(display, 0, 1);	
   		  				gridPane.add(draw, 0, 10);
   		  				gridPane.add(playAgain, 0, 11);
   		  				gridPane.add(quit2, 0, 11);
   		  				isOver = true;
   		  				startGameComputer("1","2","3");
                         
                     }else if (controller.determineWinner() == 1) {
                    	 display.getStyleClass().add("array");
                    	 gridPane.getChildren().clear();
                    	 controller.addWin();
                    	 gridPane.add(display, 0, 1);
    	  				 gridPane.add(win1, 0, 10);
    	  				 gridPane.add(quit2, 0, 12);
    	  				 Media media = new Media(Paths.get("src/winSound.mp3").toUri().toString());
    	  				 MediaPlayer mediaPlayer = new MediaPlayer(media);
    	  				 mediaPlayer.play();
    	  				 controller.storePlayerInfo();
    	  				 gridPane.add(playAgain, 0, 11);
    	  				 
    	  				 isOver = true;
    	  				 startGameComputer("1","2","3");
    	  			 }else if (controller.determineWinner() != 1) {
    	  				display.getStyleClass().add("array");
    	  				 String computer = controller.please();
      		  			 char x = computer.charAt(0);
      		  			 char y = computer.charAt(1);
      		  			 int xNum = x - '0';
      		  			 int yNum = y- '0';
      		  			 controller.setSelection(xNum,yNum,2);
      		  			 display = new Text (controller.getGameDisplay());
      		  			 gridPane.add(display, 0, 1); 
      		  			 
      		  			 if (controller.determineWinner()==2) {
      		  				display.getStyleClass().add("array");
      		  			Media media = new Media(Paths.get("src/loseSound.Wav").toUri().toString());
       	  				 MediaPlayer mediaPlayer = new MediaPlayer(media);
       	  				 mediaPlayer.play();
      		  				 gridPane.add(winC, 0, 11);
      		  				gridPane.add(quit2, 0, 12);
      		  				 gridPane.add(playAgain, 0, 12);
      		  				controller.addLose();
      		  				 isOver = true;
      		  				 startGameComputer("1","2","3");
      		  			  }
      	  			}
                     
        		 }else {
        			 gridPane.add(display, 0, 1);
        			 gridPane.add(error,0,20);
        		 }
        		 player1InputLocation.clear();
                 
        	 }
         };
         
         EventHandler<MouseEvent> againstPlayerNum1 = new EventHandler<MouseEvent>() {
        	 @Override 
             public void handle(MouseEvent e) {
        		 display.getStyleClass().add("array");
        		 gridPane.getChildren().remove(display);
        		 gridPane.getChildren().remove(error);
        		 
        		 playerInputVal = player1InputLocation.getText();
        		  
        		 if (checkValid(playerInputVal) == 1) {
        			 display.getStyleClass().add("array");
        			 if (controller.checkFullOrNot() == true){
        				 Media media = new Media(Paths.get("src/drawSound.Wav").toUri().toString());
     	  				MediaPlayer mediaPlayer = new MediaPlayer(media);
     	  				mediaPlayer.play();
    		  				gridPane.add(display, 0, 1);	
    		  				gridPane.add(draw, 0, 10);
    		  				gridPane.add(playAgain, 0, 11);
    		  				gridPane.add(GameRecord, 0, 12);
    		  				isOver = true;
    		  				startGameComputer("1","2","3");
                          
                      }else if (controller.determineWinner() == 1) {
                    	  display.getStyleClass().add("array");
                    	 gridPane.getChildren().clear();
                    	 Media media = new Media(Paths.get("src/winSound.mp3").toUri().toString());
    	  				 MediaPlayer mediaPlayer = new MediaPlayer(media);
    	  				mediaPlayer.play();
                    	 gridPane.add(display, 0, 1);
    	  				 gridPane.add(win1, 0, 10);
    	  				 gridPane.add(playAgain, 0, 11);
    	  				gridPane.add(GameRecord, 0, 12);
    	  				controller.addWin();
    	  				controller.storePlayerInfo();
    	  				isOver = true;
 		  				 startGameComputer("1","2","3");
    	  			 }else if (controller.determineWinner() != 1) {
    	  				display.getStyleClass().add("array");
   	  				 	 gridPane.add(display, 0, 1);
    	  				 againstPlayerNum2(markerTest);
      	  			}
                     
        		 }else {
        			 gridPane.add(display, 0, 1);
        			 gridPane.add(error,0,20);
        		 }
        		 player1InputLocation.clear();
                 
        	 }
         };
         
         EventHandler<MouseEvent> againstPlayerNum2 = new EventHandler<MouseEvent>() {
        	 @Override 
             public void handle(MouseEvent e) {
        		 display.getStyleClass().add("array");
        		 gridPane.getChildren().remove(display);
        		 gridPane.getChildren().remove(error);
        		 
        		 playerInputVal = player2InputLocation.getText();
        		
        		 if (checkValid2(playerInputVal) == 1) {
        			 System.out.println(markerTest);
                     if (controller.determineWinner() == 2) {
                    	 Media media = new Media(Paths.get("src/winSound.mp3").toUri().toString());
    	  				 MediaPlayer mediaPlayer = new MediaPlayer(media);
    	  				mediaPlayer.play();
    	  				controller.storePlayerInfo();
                    	 gridPane.getChildren().clear();
                    	 gridPane.add(display, 0, 1);
    	  				 gridPane.add(win2, 0, 10);
    	  				controller.addWin();
    	  				 gridPane.add(playAgain, 0, 11);
    	  				 firstTime = false;
    	  				 isOver = true;
 		  				 startGameComputer("1","2","3");
    	  			 }else if (controller.determineWinner() != 1) {
    	  				 firstTime = false;
   	  				 	 gridPane.add(display, 0, 1);
   	  				 	 startGamePlayer(username.getText(), player2.getText(), player1MarkerField.getText(), player2MarkerField.getText(), timer.getText());
      	  			}
                     
        		 }else {
        			 gridPane.add(display, 0, 1);
        			 gridPane.add(error,0,20);
        		 }

        		 player2InputLocation.clear();
                 
        	 }
         };
         
       afterName.addEventFilter(MouseEvent.MOUSE_CLICKED, submitInfo);
       quit.addEventFilter(MouseEvent.MOUSE_CLICKED,quitHandler);
       afterLocation.addEventFilter(MouseEvent.MOUSE_CLICKED, submitLocation);
       playAgain.addEventFilter(MouseEvent.MOUSE_CLICKED, playAgainHandler);
       afterPlayer1.addEventFilter(MouseEvent.MOUSE_CLICKED, againstPlayerNum1);
       afterPlayer2.addEventFilter(MouseEvent.MOUSE_CLICKED, againstPlayerNum2);
       goBack.addEventFilter(MouseEvent.MOUSE_CLICKED, goBackHandler);
       quit2.addEventFilter(MouseEvent.MOUSE_CLICKED,quitHandler);

        return gridPane;
	}

	public void beginning() {
		display.getStyleClass().add("array");
		GridPane gridPane = new GridPane();
		
		gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
       
        gridPane.add(sizeLabel, 0, 0); 
		gridPane.add(defaultValLabel, 0, 1); 
		final ToggleGroup group = new ToggleGroup();
	    
	    RadioButton rb1 = new RadioButton("Single Player");
	    rb1.setToggleGroup(group);
	    rb1.setSelected(true);

	    RadioButton rb2 = new RadioButton("Two Players");
	    rb2.setToggleGroup(group);

        sizeLabel.getStyleClass().add("whiteText");
        defaultValLabel.getStyleClass().add("whiteText");
        Line line = new Line();
        
        line.setStartX(0.0f); 
        line.setStartY(0.0f);         
        line.setEndX((float) windowWidth); 
        line.setEndY(0.0f);
        
        gridPane.add(rb1, 0, 6);
        gridPane.add(rb2, 0, 8);
        //gridPane.add(sizeTextField, 0, 1); 
        //TODO #4: Add the text field for the default value
        //gridPane.add(defaultVal, 0, 5);
        gridPane.add(button1, 0, 12);
        gridPane.add(quit,3,4);
        gridPane.add(line, 0, 2, 3, 1); 
        gridPane.add(GameRecord, 0, 15);
        
        
        EventHandler<MouseEvent> gameRecord = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) {
            	
            	gridPane.getChildren().clear();
            	
            	
            	Text dataSet = new Text(controller.dataPass());
            	gridPane.add(dataSet, 0, 1);
            	dataSet.getStyleClass().add("dataset");
            	gridPane.add(goBack, 0, 3);
            	}
         };
         
        EventHandler<MouseEvent> submitGameType = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) {
            	
            	gridPane.getChildren().clear();
            	
            	if (rb1.isSelected() == true) {
            		gridPane.getChildren().clear();
            		
            		playAgainst("1");
            		
            	}else if (rb2.isSelected() == true){
            		playAgainst("2");
            		
            	}	
            } 
         };
         button1.addEventFilter(MouseEvent.MOUSE_CLICKED, submitGameType);
         GameRecord.addEventFilter(MouseEvent.MOUSE_CLICKED,gameRecord);
         root.setCenter(gridPane);
	}
	
	public void playAgainst(String num) {
		display.getStyleClass().add("array");
		GridPane gridPane = new GridPane(); 
        
		gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5); 
        
		if (num.equals("1")) {
			playerNum = "1";
			gridPane.add(sign,0,0);
    		gridPane.add(inputName1,0,3);
        	gridPane.add(username,1,3);
        	gridPane.add(player1Marker, 0, 5);
        	gridPane.add(player1MarkerField,1,5);
        	gridPane.add(timerText,0,7);
        	gridPane.add(timer,1,7);
        	gridPane.add(afterName,1,8);
        	gridPane.add(goBack, 0, 8);
        	gridPane.add(emojiClick, 2, 5);
        	
        	EventHandler<MouseEvent> emoji = new EventHandler<MouseEvent>() {
    			@Override 
            	public void handle(MouseEvent e) {
    			markerInputFormat = false;
    			gridPane.getChildren().remove(emojiClick);
                gridPane.getChildren().remove(player1MarkerField);
                gridPane.add(markerInputButton,2,5);
 	
                } 
    		};
    		EventHandler<MouseEvent> inputButton = new EventHandler<MouseEvent>() {
    			@Override 
            	public void handle(MouseEvent e) { 
    			gridPane.add(player1MarkerField, 1, 5);
                gridPane.getChildren().remove(emojiClick);
                gridPane.add(emojiClick,2,5);
                } 
    		};
    		
    		emojiClick.addEventFilter(MouseEvent.MOUSE_CLICKED,emoji);
    		markerInputButton.addEventFilter(MouseEvent.MOUSE_CLICKED, inputButton);
		}else {
			playerNum = "2";
			gridPane.getChildren().remove(emojiClick);
			gridPane.add(sign,0,4);
    		gridPane.add(inputName1,0,5);
        	gridPane.add(username,0,6);
        	gridPane.add(inputName2,0,7);
        	gridPane.add(player2,0,8);
        	gridPane.add(afterName, 1,11);  
        	gridPane.add(player1Marker,1,5);
        	gridPane.add(player1MarkerField,1,6);
        	gridPane.add(player2Marker,1,7);
        	gridPane.add(player2MarkerField,1,8);
        	gridPane.add(emojiClick,2,8);
//        	gridPane.add(emojiClick,2,6);
        	gridPane.add(timer, 0, 10);
			gridPane.add(timerText, 0, 9);
			gridPane.add(goBack, 0, 11);
		}
		root.setCenter(gridPane);
	}
	

	
	public int checkValid(String location) {
		display.getStyleClass().add("array");
		splited = playerInputVal.split("\\s+");
		
		if (splited.length == 2) {
		
			
			try{
			
			if (controller.setSelection(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),1) == true){
				
				controller.setSelection(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),1);
	  			  display = new Text(controller.getGameDisplay());
	  			  
	  			 return 1;
				
	  			
	  		  }else {
	  			display = new Text(controller.getGameDisplay());
	  			return 0;
	  			  
	  		  }	
			}catch (NumberFormatException e1){
           	 	System.out.println("@");
                return 0;
            }
			
		}else
			return 0;
			
	}
	
	public int checkValid2(String location) {
		display.getStyleClass().add("array");
		GridPane gridPane = new GridPane();  
		splited = playerInputVal.split("\\s+");
		
		if (splited.length == 2) {
			
		
			try{
                
			if (controller.setSelection(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),2) == true){
				  gridPane.add(insertSuccess, 0, 0);
	  			  controller.setSelection(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),2);

	  			  display = new Text(controller.getGameDisplay());
 
	  			 return 1;
	  		  }else {
	  			display = new Text(controller.getGameDisplay());
	  			return 0;
	  			  
	  		  }
			}catch (NumberFormatException e1){
           	 	
                return 0;
            }
		}else
			return 0;
	}
	
	public void startGameComputer(String username, String marker, String timer) {
		GridPane gridPane = new GridPane();
		display.getStyleClass().add("array");
		
		if (firstTime == true) {
			controller.startNewGame(1, Integer.parseInt(timer));
			}
		
			gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
	        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	        gridPane.setVgap(5); 
	        gridPane.setHgap(5); 
	        controller.createPlayer(username,marker, 1);
			
			gridPane.add(player1Move, 0, 4);
			gridPane.add(playerInput, 0, 6);
			gridPane.add(quit, 1, 6);
			gridPane.add(player1InputLocation, 0, 8);
			gridPane.add(afterLocation, 0, 12);
			this.root.setCenter(gridPane);
			
			
			if (isOver == true) {
				gridPane.getChildren().clear();
			}
		
		
	}
	public void startGamePlayer(String player1, String player2, String marker1, String marker2, String timer) {
		display.getStyleClass().add("array");
		if (firstTime == true) {
			controller.startNewGame(2, Integer.parseInt(timer));
			}
		
		GridPane gridPane = new GridPane();	
		gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5); 
		controller.createPlayer(player1,marker1, 1);
		controller.createPlayer(player2,marker2, 2);
		gridPane.add(player1Move, 0, 4);
		gridPane.add(playerInput, 0, 6);
		gridPane.add(quit, 1, 6);
		gridPane.add(player1InputLocation, 0, 8);
		gridPane.add(afterPlayer1, 0, 12);
		this.root.setCenter(gridPane);
		markerTest = marker2;
	}
	
	public void againstPlayerNum2 (String markerTest) {
		display.getStyleClass().add("array");
		GridPane gridPane = new GridPane();	
		gridPane.setMinSize(windowWidth, (int) windowHeight/4); 
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);
		gridPane.add(player2Move, 0, 4);
		gridPane.add(playerInput, 0, 6);
		gridPane.add(quit, 1, 6);
		gridPane.add(player2InputLocation, 0, 8);
		gridPane.add(afterPlayer2, 0, 12);
		this.root.setCenter(gridPane);
	}
}