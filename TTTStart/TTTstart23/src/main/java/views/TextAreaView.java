package views;
/*
 * This class creates a javafx scene in which a user can input 
 * an int for row and col for their 'X' and the IntermediateAI 
 * will pick the next spot as 'O'. THis will continue until there
 * is no moves left or 'O'/'X' has won.
 * 
 * Author: Alex Rendler
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.IntermediateAI;
import model.OurObserver;
import model.OurObservable;
import model.TicTacToeGame;
import model.OurPoint;

public class TextAreaView extends BorderPane implements OurObserver {

	  private TicTacToeGame theGame;
	  private IntermediateAI ai;
	  private TextField rowTextField;
	  private TextField colTextField;
	  private Button moveButton;
	  private TextArea gameBoard;
	  private char currentPlayer = 'X';
	  private boolean firstMove = true;

	  public TextAreaView(TicTacToeGame theModel) {
	    theGame = theModel;
	    ai = new IntermediateAI();
	    theGame.addObserver(this);
	    initializePanel();
	  }

	  /*
	   * Sets up the scene and links the moveButton to run
	   * makeMove when clicked.
	   */
	  private void initializePanel() {
	    gameBoard = new TextArea();
	    gameBoard.setStyle("-fx-border-color: blue; border-width: 10;");
	    gameBoard.setEditable(false);
	    Font font = new Font("Courier", 32);
	    gameBoard.setFont(font);
	    gameBoard.setText(theGame.toString());
	    gameBoard.setMaxHeight(200);
	    this.setBottom(gameBoard);

	    Label rowLabel = new Label("Row:");
	    Label colLabel = new Label("Col:");
	    rowTextField = new TextField();
	    colTextField = new TextField();
	    moveButton = new Button("Make Move");
	    
	    moveButton.setOnAction(e -> makeMove());

	    HBox rowBox = new HBox(10, rowLabel, rowTextField);
	    HBox colBox = new HBox(10, colLabel, colTextField);
	    VBox inputBox = new VBox(10, rowBox, colBox, moveButton);
	    inputBox.setPadding(new Insets(10));
	    this.setCenter(inputBox);

	    this.setTop(new TextField("Options"));
	  }

	  /*
	   * Determines who the current player is and takes the
	   * user input and adds it to the gameBoard, and theGame.
	   * It then allows IntermediateAI to choose the next spot 
	   * for 'O'. It also checks if each move is valid.
	   */
	  private void makeMove() {
		    try {
		        char[][] board = theGame.getTicTacToeBoard();
		        int row, col;
		        if (currentPlayer == 'X') {
		            row = Integer.parseInt(rowTextField.getText().trim());
		            col = Integer.parseInt(colTextField.getText().trim());
		            if ((board[row][col] == '_')) {
			            theGame.humanMove(row, col, true);
			            board[row][col] = currentPlayer;
			            rowTextField.clear();
			            colTextField.clear();
			            if (currentPlayer == 'X') {
			                currentPlayer = 'O';
			            } else {
			                currentPlayer = 'X';
			            }
		            } else {
				            moveButton.setText("Invalid Choice");
				        }
			        }// after the user makes a move, let IntermediateAI make a move
	                if (theGame.stillRunning()) {
	                    OurPoint move = ai.desiredMove(theGame);
	                    row = move.getRow();
	                    col = move.getCol();
	                    theGame.computerMove(row, col);
	                    board[row][col] = currentPlayer;
	                    if (currentPlayer == 'X') {
	                        currentPlayer = 'O';
	                    } else {
	                        currentPlayer = 'X';
	                    }
	                }
		
		        
		    } catch (NumberFormatException e) {
		        moveButton.setText("Invalid Choice");
		    }
		}


	  /*
	   *  This method updates after each user input by setting the 
	   *  gameBoard text to equal theGame and determines if the game
	   *  is over or not.
	   */
	 
	  @Override
	  public void update(Object observable) {
		    if (observable == theGame) {
		      gameBoard.setText(theGame.toString());
		      if (!(theGame.stillRunning())) {
		        if (theGame.tied()) {
		          moveButton.setText("Tied game");
		          rowTextField.setEditable(false);
		          colTextField.setEditable(false);
		        } else if(theGame.didWin('O')) {
		               moveButton.setText("O wins!");
		               rowTextField.setEditable(false);
		               colTextField.setEditable(false);
		        }else {
		            moveButton.setText("X wins!");}
		        	rowTextField.setEditable(false);
		        	colTextField.setEditable(false);
		        }
		      }
		    }
		  }