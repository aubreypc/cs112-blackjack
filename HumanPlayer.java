class HumanPlayer extends Player {

	public HumanPlayer(int c) {
		super(c);
	}

	public void printHand() {
    	System.out.println("Your hand: (" + Integer.toString(hand.getValue()) + ")");
    	hand.print();
    }


}