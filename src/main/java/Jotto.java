import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * author Alexander Castaneda
 * version 0.1.0
 * Since 1/29/26
 **/

public class Jotto {
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
    public ArrayList<String> readWords(){
        wordList.clear();
        //opens and scans file
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            //scans the file
            while (scan.hasNextLine()){
                String word = scan.nextLine();
                //checks for duplicates
                if(!wordList.contains(word)){
                    wordList.add(word);
                }
            }
            scan.close();
        }
        //catches error
        catch (FileNotFoundException e){
            System.out.println("Couldn't open " + filename);
            return wordList;
        }
        return wordList;
    }
    public void play(){

    }
    public int guess() {
        return 0;
    }
}

