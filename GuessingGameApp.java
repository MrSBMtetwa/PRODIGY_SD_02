/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessinggameapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

/**
 *
 * @author sthandisobrighton
 */
public class GuessingGameApp extends Application {
    
     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        Game game = new Game(1, 100); 
        GuessingGameGUI gui = new GuessingGameGUI(game);

       
        Scene scene = new Scene(gui.createGameInterface(), 300, 200);
        primaryStage.setTitle("Guess the Number");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


class Game {
    private final int minRange;
    private final int maxRange;
    private final int numberToGuess;
    private int attempts;

    public Game(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.numberToGuess = generateRandomNumber();
        this.attempts = 0;
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }

    public String guess(int userGuess) {
        attempts++;
        if (userGuess < numberToGuess) {
            return "Too low!";
        } else if (userGuess > numberToGuess) {
            return "Too high!";
        } else {
            return "Correct! You guessed the number in " + attempts + " attempts.";
        }
    }

    public int getAttempts() {
        return attempts;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }
}


class GuessingGameGUI {

    private final Game game;
    private final TextField guessInput;
    private final Label feedbackLabel;
    private final Button guessButton;
    private final Label attemptLabel;

    public GuessingGameGUI(Game game) {
        this.game = game;
        this.guessInput = new TextField();
        this.feedbackLabel = new Label();
        this.guessButton = new Button("Guess");
        this.attemptLabel = new Label("Attempts: 0");
    }

    public GridPane createGameInterface() {
     
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Enter your guess:"), 0, 0);
        grid.add(guessInput, 1, 0);

       
        grid.add(guessButton, 1, 1);

        
        grid.add(feedbackLabel, 1, 2);

       
        grid.add(attemptLabel, 1, 3);

     
        guessButton.setOnAction(e -> handleGuess());

        return grid;
    }

    private void handleGuess() {
        try {
           
            int userGuess = Integer.parseInt(guessInput.getText());

       
            String feedback = game.guess(userGuess);

            
            feedbackLabel.setText(feedback);
            attemptLabel.setText("Attempts: " + game.getAttempts());

           
            guessInput.clear();

            
            if (feedback.startsWith("Correct")) {
                guessButton.setDisable(true);
            }
        } catch (NumberFormatException ex) {
            
            feedbackLabel.setText("Please enter a valid number.");
        }
    }
    
}
