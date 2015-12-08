import java.util.ArrayList;
import java.util.UUID;

class Deck extends CardGroup {

    public Deck() {
        super();
    }

    public void populate() {
        // populates the deck with the standard 52 cards
        String[] suits = {"SPADES", "CLUBS", "DIAMONDS", "HEARTS"};
        for (int i = 2; i <= 14; i++) {
            for (int j = 0; j < 4; j++) {
                Card c = new Card(i, suits[j]);
                add(c);
            }
        }
    }

    public void shuffle() {
        // every card gets a randomly generated unique identifier
        // from there, we can sort based on the identifiers to shuffle
        int size = getCards().size();
        UUID[] identifiers = new UUID[size];
        ArrayList<Card> sortedCards = getCards();

        for (int i = 0; i < size(); i++) {
            identifiers[i] = getCard(i).generateUUID();
        }

        // selection sort on identifiers
       	// sorting cards at same time
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i+1; j < size; j++) {
                UUID current = identifiers[j];
                if (identifiers[min].compareTo(current) == 1) {
                    min = j;
                }
            }
            UUID temp = identifiers[i];
            identifiers[i] = identifiers[min];
            identifiers[min] = temp;

            Card tempCard = sortedCards.get(i);
            sortedCards.set(i, sortedCards.get(min));
            sortedCards.set(min, tempCard);
        }

        setCards(sortedCards);
    }

}