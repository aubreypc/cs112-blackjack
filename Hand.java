class Hand extends CardGroup {

    public Hand() {
        super();
    }

    public int getValue() {
    	int val = 0;
    	int aces = 0;
    	for (int i = 0; i < size(); i++) {
    		Card c = getCard(i);
    		int v = c.getValue();
    		if (v == 14) {
    			aces += 1;
    		} else if (v > 10 && v < 14) {
    			val += 10; // face cards
    		} else {
    			val += v;
    		}
    	}

    	int acesTotal = aces * 11;
    	if (val + acesTotal > 21) {
    		val += aces; // use aces with value 1
    	} else {
    		val += acesTotal;
    	}

    	return val;
    }

    public void print() {
    	for (int i = 0; i < size(); i++) {
    		Card c = getCard(i);
    		String val = "";
    		if (c.getValue() <= 10) {
    			val = Integer.toString(c.getValue());
    		} else if (c.getValue() == 11) {
    			val = "JACK";
    		} else if (c.getValue() == 12) {
    			val = "QUEEN";
    		} else if (c.getValue() == 13) {
    			val = "KING";
    		} else if (c.getValue() == 14) {
    			val = "ACE";
    		}
    		System.out.println(val + " " + c.getSuit());
    	}
    }

}