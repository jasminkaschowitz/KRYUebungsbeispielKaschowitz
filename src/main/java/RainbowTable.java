public class RainbowTable {

    private String hash;
    private String hashMethod;
    private String word;

    public RainbowTable(String hash, String hashMethod, String word) {
        this.hash = hash;
        this.hashMethod = hashMethod;
        this.word = word;
    }

    public String getHash() {
        return hash;
    }

    public String getHashMethod() {
        return hashMethod;
    }

    public String getWord() {
        return word;
    }
}
