import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheWord extends JFrame {
    private String[] words = {"apple","Banana","cherry","grape","orange","pear","strawberry"};
    private Random random = new Random();
    private String selected;
    private char[] guessed;
    private int attempts = 6;

    private JLabel statusLabel;
    private JLabel WordLabel;
    private JTextField guessField;
    private JButton guessButton;

    public GuessTheWord() {
        selected = words[random.nextInt(words.length)];
        guessed = new char[selected.length()];

        setTitle("Guess the Word Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        statusLabel = new JLabel("Guess the Word. you have " + attempts + "attempts left.");
        WordLabel = new JLabel(getHiddenWord());
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText().toLowerCase();
                if (guess.length() != 1) {
                    JOptionPane.showMessageDialog(null, " Please enter a single Letter.");
                    return;
                }
                char guessedLetter = guess.charAt(0);
                boolean correctGuess = false;

                for (int i = 0; i < selected.length(); i++) {
                    if (selected.charAt(i) == guessedLetter) {
                        guessed[i] = guessedLetter;
                        correctGuess = true;
                    }
                }
                if (!correctGuess) {
                    attempts--;
                }
                if (attempts == 0 || String.valueOf(guessedLetter).equals(selected)) {
                    guessButton.setEnabled(false);
                    String message = attempts == 0 ? "Out of attempts. the word was: " + selected : "congratulations! you guessed the word: " + selected;
                    JOptionPane.showMessageDialog(null, message);
                }
                updateUI();
            }
        });
        add(statusLabel);
        add(WordLabel);
        add(createGuessPanel());

        updateUI();
    }
    private JPanel createGuessPanel(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("enter a letter :"));
        panel.add(guessField);
        panel.add(guessButton);
        return panel;
    }
    private String getHiddenWord(){
        StringBuilder hidden = new StringBuilder();
        for(char c:guessed){
            if(c==0){
                hidden.append('-');
            }else{
                hidden.append(c);
            }
            hidden.append(' ');
        }
        return hidden.toString();
    }
    private void updateUI(){
        statusLabel.setText("Guess the Word. you have" + attempts + "attempts left.");
        WordLabel.setText(getHiddenWord());
        guessField.setText("");
        guessField.requestFocus();
    }
    public static void main(String args[]) {
        SwingUtilities.invokeLater(()->new GuessTheWord().setVisible(true));
    }
}