import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] wordsArr = {"Octopus", "Brown", "Apple", "Pencil", "Case", "Super", "Robin", "Orange", "Rabbit", "Tower", "Eiffel", "Falafel" };
        WordLibrary wordArr = new WordLibrary(wordsArr);
        String randomWord = wordArr.randomWord();
        Game game = new Game(randomWord);
        Scanner scanner = new Scanner(System.in);
        System.out.println(game.getGuessedDashArr());
        while(game.getLives()>0 && game.getCorrectGuesses()!=0){
            System.out.println("Enter Guess:");
            String guess = scanner.nextLine();
            game.guessChar(guess);
        }
        if(game.getLives()==0){
            System.out.println("You loose");
            System.out.println("The word was: " + game.getWord());
        } else {
            System.out.println("Well done you win!");
        }
    }
}
