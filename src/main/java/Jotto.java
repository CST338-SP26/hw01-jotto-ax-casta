import java.util.ArrayList;

/**
 * @author Alexander Castaneda
 * @version 0.1.0
 * @Since 1/29/26
 **/

public class Jotto {
    private void readWords(){}
    //Static
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;
    //Strings
    private String currentWord;
    private String filename;
    //int
    private int score;
    //ArrayList
    private final ArrayList<String> wordList = new ArrayList<>();
    private final ArrayList<String> playGuesses = new ArrayList<>();
    private final ArrayList<String> playWords = new ArrayList<>();

    //CONSTRUCTOR
    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }

    //GETTERS
    public String getCurrentWord() {
        return currentWord;
    }

    public String getFilename() {
        return filename;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<String> getPlayGuesses() {
        return playGuesses;
    }

    public ArrayList<String> getPlayWords() {
        return playWords;
    }

    //SETTERS
    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDEBUG() {
        return DEBUG;
    }
}

