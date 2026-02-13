import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * author Alexander Castaneda
 * version 0.1.0
 * Since 1/29/26
 **/

public class Jotto {
    //Scanner
    private final Scanner scan = new Scanner(System.in);
    //Static
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;
    //Strings
    private String currentWord;
    private final String filename;
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
                if (!pickWord()) {
                    showPlayerGuesses();
                }
                else{
                    int roundScore = guess();
                    score += roundScore;
                    System.out.println("Your score is " + score);
                }
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
                showPlayerGuesses();
            }
            //quits game
            else if(choice.equals("zz")){
                //final message
                System.out.println("Final score: " + score);
                System.out.println("Thank you for playing");
                //stops the while loop
                gameRunning = false;
            }
            //unknown input
            else{
                System.out.println("I don't know what \"" + choice + "\" is.");
            }
            //pauses while loop for player input
            if(gameRunning) {
                System.out.println("Press enter to continue");
                scan.nextLine();
            }
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
        System.out.println("Would you like to add the words to the word list? (y/n)");
        String input = scan.nextLine().trim().toLowerCase();

        //checks what the input is
        if (input.equals("y")) {
            System.out.println("Updating word list.");
            updateWordList();
            System.out.println(showWordList());
        }
        return playGuesses;
    }

    //METHOD GUESS
    public int guess() {
        ArrayList<String> currentGuesses = new ArrayList<>();

        int letterCount;
        int score = WORD_SIZE + 1;
        String wordGuess;

        while(true){
            //prints score
            System.out.println("Current Score: " + score);
            //prompts quit input
            System.out.print("What is your guess (q to quit): ");
            wordGuess = scan.nextLine().trim().toLowerCase();

            //quit
            if(wordGuess.equals("q")){
                if(score > 0){
                    score = 0;
                }
                    break;
            }

            //checks word length
            if(wordGuess.length() != WORD_SIZE){
                System.out.println("Word must be 5 characters (" + wordGuess + " is " + wordGuess.length() + ")");
                continue;
            }

            //checks duplicate guesses
            if (currentGuesses.contains(wordGuess)){
                System.out.println(wordGuess + " has already been entered.");
                continue;
            }

            //adds guess to player guesses
            addPlayerGuess(wordGuess);
            currentGuesses.add(wordGuess);

            //checks for the correct word
            if(wordGuess.equals(currentWord)){
                System.out.println("DINGDINGDING!!! the word was " + currentWord);
                playerGuessScores(currentGuesses);
                return score;
            }

            //determines letter count
            letterCount = getLetterCount(wordGuess);

            //all letters are correct
            if(letterCount == WORD_SIZE){
                System.out.println("That word is an anagram!");
            }
            //how many correct letters
            else{
                System.out.println(wordGuess + " has a Jotto score of " + letterCount);
            }
            //counts down score
            score--;

            playerGuessScores(currentGuesses);
        }
        return score;
    }

    //METHOD GETLETTERCOUNT
    public int getLetterCount(String wordGuess){
        int count = 0;
        ArrayList<Character> letters = new ArrayList<>();

        if (wordGuess.equals(currentWord)) {
            return WORD_SIZE;
        }

        for (int i = 0; i < currentWord.length(); i++){
            char c = currentWord.charAt(i);
            if (!letters.contains(c)){
                letters.add(c);
            }
        }

        for(int i = 0; i < wordGuess.length(); i++){
            char c = wordGuess.charAt(i);
            if (letters.contains(c)) {
                letters.remove((Character) c);
                count++;
            }
        }
        return count;
    }

    //METHOD UPDATEWORDLIST()
    public void updateWordList(){
        try{
            FileWriter writer = new FileWriter(filename);

            //adds guesses into wordList
            for (String guess : playGuesses){
                if(!wordList.contains(guess)){
                    wordList.add(guess);
                }
            }

            //writes back to file
            for (String word : wordList){
                writer.write(word + "\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error writing to file.");
        }
    }

    //METHOD PICKWORD
    public boolean pickWord(){
        if (wordList.isEmpty()){
            return false;
        }

        //when all words have been guessed
        if(playWords.size() == wordList.size()){
            System.out.println("You've guessed them all!");
            return false;
        }

        //randomizer
        Random rand = new Random();

        //picks a random word from the list
        do{
            int index = rand.nextInt(wordList.size());
            currentWord = wordList.get(index);
        }
        while (playWords.contains(currentWord));

        playWords.add(currentWord);

        //DEBUG SHOW WORD
        if(DEBUG){
            System.out.println(currentWord);
        }
        return true;
    }

    //METHOD ADDPLAYERGUESS
    public boolean addPlayerGuess(String wordGuess){
        if(!playGuesses.contains(wordGuess)){
            playGuesses.add(wordGuess);
            return true;
        }
        return false;
    }

    //METHOD PLAYERGUESSSCORES
    public void playerGuessScores(ArrayList<String> guesses){
        System.out.println("Guess\t\tScore");

        for(String g : guesses){
            int s = getLetterCount(g);
            System.out.println(g + "\t\t" + s);
        }
        System.out.println();
    }
}

