package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.OurObserver;
import model.TicTacToeGame;



public class CompleteButtonView extends BorderPane implements OurObserver {  
	private TicTacToeGame theGame;
	  private Button button1;
	  private Button button2;
	  private Button button3;
	  private Button button4;
	  private Button button5;
	  private Button button6;
	  private Button button7;
	  private Button button8;
	  private Button button9;
	  private Label label;
	  
	  
	  public CompleteButtonView(TicTacToeGame theModel) {
	    theGame = theModel;
	    initializePanel();
	  }

	  private void initializePanel() {
		  
		  GridPane everything = new GridPane();
		  everything.setHgap(0);
		  everything.setVgap(2);
		  GridPane buttonPane = new GridPane();
		  buttonPane.setHgap(10);
		  buttonPane.setVgap(10);
		  everything.add(buttonPane, 0, 0);
		  GridPane textPane = new GridPane();
		  everything.add(textPane, 0, 1);
		  
		  
		  
		  
		  char[][] board = theGame.getTicTacToeBoard();
		  
		  button1 = new Button();
		  button2 = new Button();
		  button3 = new Button();
		  button4 = new Button();
		  button5 = new Button();
		  button6 = new Button();
		  button7 = new Button();
		  button8 = new Button();
		  button9 = new Button();
		  
		  
		  
		  Font font = new Font("Arial", 35);
		  button1.setFont(font);
		  
		  button2.setFont(font);
		  
		  button3.setFont(font);
		  
		  button4.setFont(font);
		  
		  button5.setFont(font);
		  
		  button6.setFont(font);
		  
		  button7.setFont(font);
		  
		  button8.setFont(font);
		  
		  button9.setFont(font);
		  
		  
		  button1.setText("" + board[0][0]);
		  button2.setText("" + board[0][1]);
		  button3.setText("" + board[0][2]);
		  button4.setText("" + board[1][0]);
		  button5.setText("" + board[1][1]);
		  button6.setText("" + board[1][2]);
		  button7.setText("" + board[2][0]);
		  button8.setText("" + board[2][1]);
		  button9.setText("" + board[2][2]);
		  
		  textPane.setVgap(10);
		  textPane.setHgap(10);
		  label = new Label("Click to make a Move");
		  
		  textPane.add(label, 5, 5);
		  
		  buttonPane.add(button1, 1, 1);
		  buttonPane.add(button2, 2, 1);
		  buttonPane.add(button3, 3, 1);
		  buttonPane.add(button4, 1, 4);
		  buttonPane.add(button5, 2, 4);
		  buttonPane.add(button6, 3, 4);
		  buttonPane.add(button7, 1, 7);
		  buttonPane.add(button8, 2, 7);
		  buttonPane.add(button9, 3, 7);
		  
		  EventHandler<ActionEvent> textFieldHandler = new AHandler();
		  button1.setOnAction(textFieldHandler);
		  button2.setOnAction(textFieldHandler);
		  button3.setOnAction(textFieldHandler);
		  button4.setOnAction(textFieldHandler);
		  button5.setOnAction(textFieldHandler);
		  button6.setOnAction(textFieldHandler);
		  button7.setOnAction(textFieldHandler);
		  button8.setOnAction(textFieldHandler);
		  button9.setOnAction(textFieldHandler);
		  
		  this.setCenter(everything);
	    
	    
	  }


	@Override
	public void update(Object theObserved) {
	// TODO Auto-generated method stub
		char[][] board = theGame.getTicTacToeBoard();
		 button1.setText("" + board[0][0]);
		 button2.setText("" + board[0][1]);
		 button3.setText("" + board[0][2]);
		 button4.setText("" + board[1][0]);
		 button5.setText("" + board[1][1]);
		 button6.setText("" + board[1][2]);
		 button7.setText("" + board[2][0]);
		 button8.setText("" + board[2][1]);
		 button9.setText("" + board[2][2]);
		 label.setText("Click to make a move");
	
	}
	
	private class AHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if(theGame.stillRunning()) {
			if(event.getSource() == button1) {
				theGame.humanMove(0, 0, false);
				char[][] board = theGame.getTicTacToeBoard();
				
			}else if(event.getSource() == button2) {
				theGame.humanMove(0, 1, false);
				
			}else if(event.getSource() == button3) {
				theGame.humanMove(0, 2, false);
				
			}else if(event.getSource() == button4) {
				theGame.humanMove(1, 0, false);
				
			}else if(event.getSource() == button5) {
				theGame.humanMove(1, 1, false);
				
			}else if(event.getSource() == button6) {
				theGame.humanMove(1, 2, false);
				
			}else if(event.getSource() == button7) {
				theGame.humanMove(2, 0, false);
				
			}else if(event.getSource() == button8) {
				theGame.humanMove(2, 1, false);
				
			}else if(event.getSource() == button9) {
				theGame.humanMove(2, 2, false);
				
			}
			char[][] board = theGame.getTicTacToeBoard();
			
			 button1.setText("" + board[0][0]);
			 button2.setText("" + board[0][1]);
			 button3.setText("" + board[0][2]);
			 button4.setText("" + board[1][0]);
			 button5.setText("" + board[1][1]);
			 button6.setText("" + board[1][2]);
			 button7.setText("" + board[2][0]);
			 button8.setText("" + board[2][1]);
			 button9.setText("" + board[2][2]);
			 
			if(theGame.tied()) {
				label.setText("Game Tied");
			}else if(theGame.didWin('X')) {
				label.setText("X Wins");
			}else if(theGame.didWin('O')) {
				label.setText("O Wins");
			}
			
		}
	}
	}
}