import java.util.concurrent.ThreadLocalRandom;

class ComputerPlayer extends Player {

	private int count = 0;

	public ComputerPlayer(int c) {
		super(c);
	}

	public void printHand() {
    	System.out.println("Computer's hand: (" + Integer.toString(hand.getValue()) + ")");
    	hand.print();
    }

    public String makeMove(Hand humanHand) {
        if (hand.getValue() > humanHand.getValue()) {
            return "stay";
        } else {
            return "hit";
        }
    }

    public int makeBet(int min) {
        int amount = min;
        if (count > 0) {
            // count is favorable; make a big bet
            double rand = ThreadLocalRandom.current().nextDouble(0.60) + 0.25; //between 0.25 and 0.85
            amount = ((int) Math.floor(chips * rand));
        } else {
            double rand = ThreadLocalRandom.current().nextDouble(0.25) + 0.1; //between 0.1 and 0.35
            amount = ((int) Math.floor(chips * rand));
        }
        if (amount < min) {
            amount = min;
        } else if (amount > chips) {
            amount = chips;
        }
        chips -= amount;
        return amount;
    }

    public int getCount() {
    	return count;
    }

    public void setCount(int c) {
    	count = c;
    }

    public void updateCount(Card c) {
    	int val = c.getValue();
    	if (val < 7) {
    		count++;
    	} else if (val > 10) {
    		count--;
    	}
    }

}