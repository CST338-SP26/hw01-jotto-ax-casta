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

    //METHOD READWORDS
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

    //METHOD PLAY
    public void play(){
        //scanner to read user input
        Scanner scan = new Scanner(System.in);

        //handles the game menu and player inputs
        boolean gameRunning = true;
        while(gameRunning){
            //prints menu
            System.out.println("=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Choose one of the following:");
            System.out.println("1:\t Start the game");
            System.out.println("2:\t See the word list");
            System.out.println("3:\t See the chosen words");
            System.out.println("4:\t Show Player guesses");
            System.out.println("zz to exit");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("What is your choice: ");

            //input is trimmed
            String choice = scan.nextLine().trim().toLowerCase();
            //option 1 : pickword()->guess()
            if(choice.equals("1")||choice.equals("one")){

            }
            //option 2 : showWordList()
            else if(choice.equals("2")||choice.equals("two")){
                System.out.println(showWordList());
            }
            //option 3 : showPlayedWords()
            else if(choice.equals("3")||choice.equals("three")){
                System.out.println(showPlayedWords());
            }
            //option 4 : showPlayerGuesses()
            else if(choice.equals("4")||choice.equals("four")){

            }
            //quits game
            else if(choice.equals("zz")){
                //stops the while loop
                gameRunning = false;
            }
            //unknown input
            else{
                System.out.println("I don't know what \"" + choice + "\" is.");
            }
            //pauses while loop for player input
            System.out.println("Press enter to continue");
            scan.nextLine();
        }
    }

    //METHOD SHOWPLAYEDWORDS
    public String showPlayedWords(){
        //checks if playWords is empty
        if (playWords.isEmpty()) {
            return "No words have been played";
        }
        String playWordsString = "Current list of played words:\n";
        //returns list of played words
        for (String word:playWords){
            playWordsString += word + "\n";
        }
        return playWordsString;
    }

    //METHOD SHOWWORDLIST
    public String showWordList(){
        //returns list of words
        String showWordsString = "Current word list:\n";
        for (String word:wordList){
            showWordsString += word + "\n";
        }
        return showWordsString;
    }

    //METHOD SHOWPLAYERGUESSES
    public ArrayList<String> showPlayerGuesses() {
        //checks if playGuesses is empty
        if (playGuesses.isEmpty()) {
            System.out.println("No guesses yet");
        }
        //prints current player guesses
        else {
            System.out.println("Current player guesses:");
            for (String word : playGuesses) {
                System.out.println(word);
            }
        }
        //checks for input
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to add the words to the word list? (y/n)");
        String input = scan.nextLine().trim().toLowerCase();

        //checks what the input is
        if (input.equals("y")) {
            System.out.println("Updating word list.");
            //updateWordList();
            System.out.println(showWordList());
        }
        return playGuesses;
    }

    //METHOD PICKWORD
    //public boolean pickWord(){}

    //METHOD GUESS
    //public int guess() {}

    //METHOD GETLETTERCOUNT
    //public int getLetterCount(String wordGuess){}

    //METHOD ADDPLAYERGUESS
    //public boolean addPlayerGuess(String wordGuess){}

    //METHOD PLAYERGUESSSCORES
    //public void playerGuessScores(ArrayList<String> guesses){}

    //METHOD UPDATEWORDLIST()
    //public void updateWordList(){}
}

