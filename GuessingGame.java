import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessingGame extends JFrame implements ActionListener 
{    
    private JTextField guessTextField;
    private JButton guessButton, newGameButton;
    private JLabel infoLabel, resultLabel;
    private int randomNumber;
    private int remainingGuesses = 5;
    public GuessingGame() 
	{
        setTitle("Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        infoLabel = new JLabel("Guess a number between 1 and 100.");
        mainPanel.add(infoLabel);
        
        guessTextField = new JTextField(10);
        mainPanel.add(guessTextField);
        
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        mainPanel.add(guessButton);
        
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        mainPanel.add(newGameButton);
        
        resultLabel = new JLabel("");
        mainPanel.add(resultLabel);
        
        add(mainPanel);
        
        setVisible(true);
        
        startNewGame();
    }
    private void startNewGame() 
	{
        randomNumber = (int)(Math.random() * 100 + 1);
		System.out.println(randomNumber);
        remainingGuesses = 5;
        guessTextField.setText("");
        resultLabel.setText("");
        infoLabel.setText("Guess a number between 1 and 100. You have " + remainingGuesses + " guesses left.");
        guessButton.setEnabled(true);
    }
    private void checkGuess() 
	{
        int guess;
        try 
		{
            guess = Integer.parseInt(guessTextField.getText());
        } 
		catch (NumberFormatException e) 
		{
            resultLabel.setText("Invalid guess.");
            return;
        }
        if (guess == randomNumber) 
		{
            guessButton.setEnabled(false);
            resultLabel.setText("You won "+remainingGuesses*1000+"$");
            infoLabel.setText("The number was " + randomNumber + ". Play again?");
			System.out.println();
        } 
		else if (remainingGuesses == 0) 
		{
            guessButton.setEnabled(false);
            resultLabel.setText("You lose.");
            infoLabel.setText("The number was " + randomNumber + ". Play again?");
        } 
		else 
		{
			if(guess<randomNumber)
			{
				resultLabel.setText("Guess Greater. You have " + remainingGuesses + " guesses left.");
			}
			else if(guess>randomNumber)
			{
				resultLabel.setText("Guess Lower. You have " + remainingGuesses + " guesses left.");
			}
        }
		remainingGuesses--;
    }
    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == guessButton) 
		{
            checkGuess();
        } 
		else if (e.getSource() == newGameButton) 
		{
            startNewGame();
        }
    }
    public static void main(String[] args) 
	{
        new GuessingGame();
    }
}
