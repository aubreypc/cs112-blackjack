import java.util.UUID;

class Card {

    private String suit;
    private Integer value;
    private UUID identifier;

    public Card(Integer v, String s) {
        suit = s;
        value = v;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String s) {
        suit = s;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer v) {
        value = v;
    }

    public UUID generateUUID() {
        // generates random unique identifier
        // used in Deck.shuffle() method
        identifier = UUID.randomUUID();
        return identifier;
    }

    public UUID getUUID() {
        return identifier;
    }
}