abstract class CardGame {

	protected InputHandler in = new InputHandler();
	protected Deck deck = new Deck();

	public void run() {
		// main game loop
		// should not need to be modified
		setup();
		int statusCode = 0;
		while (true) {
			statusCode = playRound();
			if (statusCode != 0) {
				break;
			}
		}
		finish(statusCode);
	}

	public void setup() {
		// called at the beginning	
	}

	public int playRound() {
		// logic for each round
		return 1;
	}

	public void finish(int status) {
		// runs at the end
	}

	public void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (Exception e) {

		}
	}

}
