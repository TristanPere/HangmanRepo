public class WordLibrary {
    private String[] words;
    public WordLibrary(String[] words) {
        this.words = words;
    }
    public String randomWord() {
        int randomIndex = (int) (Math.random()* words.length);
        return words[randomIndex];
    }
}
