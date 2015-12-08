import java.util.ArrayList;

class CardGroup {

    private ArrayList<Card> cards;

    public CardGroup() {
        cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> c) {
        cards = c;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int size() {
        return cards.size();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void dealTo(CardGroup c, int amount) {
        for (int i = 0; i < amount; i++) {
            c.add(cards.remove(0));
        }
    }

    public void dealAll(CardGroup c) {
    	dealTo(c, size());
	}

}
