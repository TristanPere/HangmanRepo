public class Game {
    private String word;
    private String[] solutionCharArr;
    private String[] guessedDashArr; // Elephant - E_E_____
    private String[] usedCharArr = new String[26]; // A,E,C,G,.....
    private int correctGuesses;
    private int guessNum = 0;
    private Player player = new Player("Player1");
    public Game(String word) {
        this.word = word;
        this.solutionCharArr = word.toUpperCase().split("");
        this.guessedDashArr = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.guessedDashArr[i] = "_";
        }
        this.correctGuesses = word.length();
    }
    public int getCorrectGuesses() {
        return correctGuesses;
    }
    public String getWord() {
        return word;
    }
    public int getLives() {
        return player.getLives();
    }
    public String getSolutionCharArr() {
        return String.join("",solutionCharArr);
    }
    public String getGuessedDashArr() {
        return String.join(" ",guessedDashArr);
    }
    public String getUsedCharArr() {
        int i=0;
        while(usedCharArr[i]!=null){
            i++;
        }
        String[] cleanArr = new String[i];
        for (int j = 0; j < i; j++) {
            cleanArr[j] = usedCharArr[j];
        }
        return String.join(",",cleanArr);
    }
    private String successfulGuessMessage(boolean successful, String guess){
        String newLine = System.getProperty("line.separator");
        PictureArr hangManGallows = new PictureArr();
        if (successful){
            return String.join(newLine,
                    guess + ": Is a Correct letter",
                    "Working Solution: " + getGuessedDashArr(),
                    "Guessed Characters: " + getUsedCharArr(),
                    "Lives left: " + player.getLives());
        } else {
            return String.join(newLine,
                    guess + ": Is not a Correct letter",
                    "Working Solution: " + getGuessedDashArr(),
                    "Guessed Characters: " + getUsedCharArr(),
                    "Lives left: " + player.getLives(),
                    hangManGallows.getHangMan(player.getLives())
                    );

        }
    }
    private boolean charCheck(String guessChar){
        if (getUsedCharArr().contains(guessChar.toUpperCase())){
            System.out.println(guessChar.toUpperCase() + " has already been guessed. Please enter a new letter:");
            System.out.println("Lives left: " + getLives());
            return false;
        } else if (!guessChar.matches("[a-zA-Z]+")) {
            System.out.println(guessChar.toUpperCase() + " is not a letter. Please Guess Letters Only.");
            System.out.println("Lives left: " + getLives());
            return false;
        } else if (guessChar.length()>1) {
            System.out.println("Please only enter one letter at a time.");
            System.out.println("Lives left: " + getLives());
            return false;
        } else {
            return true;
        }
    }
    public void guessChar(String guessChar){
        boolean valid = charCheck(guessChar);
        if (valid){
        if (word.toUpperCase().contains(guessChar.toUpperCase())){
            int count = 0;
            for (int i = 0; i < solutionCharArr.length; i++) {
                if(solutionCharArr[i].equals(guessChar.toUpperCase())) {
                    count ++;
                }
            }
            int[] index = new int[count];
            count = 0;
            for (int i = 0; i < solutionCharArr.length; i++) {
                if(solutionCharArr[i].equals(guessChar.toUpperCase())) {
                    index[count] = i;
                    count++;
                }
            }
            for (int i = 0; i < index.length; i++) {
                guessedDashArr[index[i]] = guessChar.toUpperCase();
            }
            usedCharArr[guessNum] = guessChar.toUpperCase();
            this.guessNum++;
            this.correctGuesses-=count;
            System.out.println(successfulGuessMessage(true, guessChar.toUpperCase()));
        } else {
            this.player.looseLife();
            usedCharArr[guessNum] = guessChar.toUpperCase();
            this.guessNum++;
            System.out.println(successfulGuessMessage(false, guessChar.toUpperCase()));
        }
        }
    }
}

