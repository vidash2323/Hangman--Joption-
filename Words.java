import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Words {

    private final ArrayList<Word> listOfWords;
    private final Random rand;
    private final Word emergencyWord;

    public Words() {
        this.listOfWords = new ArrayList<>();
        this.rand = new Random();
        this.emergencyWord = new Word("hangman");
    }

    public void addWordsToListOfWords(String wordCategory) {
        /*
        Add words from file to the list of words
         */
        try {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            Scanner reader = null;
            if (response == fileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                reader = new Scanner(file);
            }
            //  File words = new File("words/" + wordCategory + ".txt");


            while (reader.hasNextLine()) {
                String data = reader.nextLine().strip();
                Word word = new Word(data.toLowerCase());
                word.splitWordToLetters();
                this.listOfWords.add(word);
            }

            reader.close();
        }
        catch (FileNotFoundException e) {
        }
    }

    public Word selectRandomWord() {
        /*
        Selects a random word from the list of words
         */

        if (this.listOfWords.size() > 0) {
            int upperbound = this.listOfWords.size();
            return this.listOfWords.get(rand.nextInt(upperbound));
        }
        return this.emergencyWord;
    }

    public void resetListOfWords() {
        this.listOfWords.clear();
    }
}
