public class RainbowTable {

    private String hash;
    private String hashMethod;
    private String word;

    //eigene Klasse, um sowohl den Hash-Wert, die verwendete Hash-Methode als auch das ursprüngliche Passwort zu speichern

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
