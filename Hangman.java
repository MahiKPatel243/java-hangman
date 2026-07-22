import java.util.Scanner;
public class Hangman 
{
    //instance variables
    private String word;
    private String mystery;
    private String lettersChosen;
    private int lives;
    static String[] choices = {"Marvel","Money","Encyclopedia","Hungry","Catastrophe","Student","Eurasia","Excitement","College","APClasses"};
    static boolean game = true;

    Scanner keyboard = new Scanner(System.in);
    
    //default constructor
    public Hangman()
    {
        word = getWord();
        lives = 5;
        lettersChosen = "";
        getMystery();
        isStartGame();
    }

    //methods with accesors and mutators
    public static String getWord()
    {
        return choices[(int)(Math.random()*choices.length)];
    }
    public void isStartGame()
    {
        if(game)
        {
            startGame();
            game = false;
        }
        
        if(!game)
        {
            endGame();
        }
    }
    public void startGame()
    {
        System.out.println("Welcome to Hangman!  You have to guess the mystery word in 5 guesses or less.");
            while(!(mystery.equals(word)) && lives>0)
            {
                System.out.print("Lives: " + lives);
                System.out.println("\tLetters picked: " + lettersChosen);
                System.out.println(mystery);
                System.out.print("Letter to try: ");
                String a = keyboard.nextLine();
                setLettersChosen(a);
                setMystery(a);
                System.out.println("-------------------------------------------------------------");
            }
    }
    public void endGame()
    {
        if(mystery.equals(word))
        {
            System.out.print(mystery + "\nYou guessed with " + lives + " lives left");
        }
        else
        {
            System.out.println("You lost. The word was " + word);
        }
    }
    public String getMystery()
    {
        mystery = "";
        for(int i = 1; i<=word.length(); i++)
        {
            mystery+="*";
        }
        return mystery;
    }
    public void setLettersChosen(String a)
    {
        lettersChosen+=a+" ";
    }
    public void setMystery(String a)
    {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder(mystery);
        for(int i = 0; i<word.length(); i++)
        {
            char wordLetter = word.charAt(i);
            char b = Character.toLowerCase(a.charAt(0));
            char c = Character.toUpperCase(b);
            if(b == wordLetter)
            {
                stringBuilder.setCharAt(i, b);
                count++;
            }
            else if(c == wordLetter)
            {
                stringBuilder.setCharAt(i,c);
                count++;
            }
        }
        String modifiedString = stringBuilder.toString();
        if(count==0)
        {
            System.out.println("Wrong -1 life");
            lives--;
        }
        else
        {
            System.out.println("Correct!");
        }
        mystery = modifiedString;
    }
}