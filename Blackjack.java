import java.util.*;

class Blackjack {
 
    private static class BlackjackGame extends CardGame {
    
		private static HumanPlayer human = new HumanPlayer(100);
    	private static ComputerPlayer computer = new ComputerPlayer(100);
    	private Deck discarded;
    	private int pot;
    	private int minBet = 20;

    	public void setup() {
			deck.populate();
			deck.shuffle();
			discarded = new Deck();
		}

		public int playRound() {
			deck.dealTo(human.hand, 2);
			deck.dealTo(computer.hand, 2);

			//make bets
			pot = 0;
			System.out.println("You have " + Integer.toString(human.chips) + " chips.");
			System.out.println("Computer has " + Integer.toString(computer.chips) + " chips.");
			System.out.println("Enter your bet: ");
			int humanBet = in.getIntInRange(minBet, human.chips);
			human.chips -= humanBet;
			int computerBet = computer.makeBet(minBet);
			System.out.println("Computer bet " + Integer.toString(computerBet));
			pot = humanBet + computerBet;

			//print hands
			human.printHand();
			computer.printHand();
			
			//player makes moves until bust or stay
			while (human.hand.getValue() <= 21) {
				String status = makeMove();
				human.printHand();
				if (status == "bust" || status == "stay") {
					break;
				}
			}

			//AI's turn to play
			if (human.hand.getValue() != 21) {
				//count cards
				for (int i = 0; i < computer.hand.size(); i++) {
					Card c = computer.hand.getCard(i);
					computer.updateCount(c);
				}
				for (int i = 0; i < human.hand.size(); i++) {
					Card c = human.hand.getCard(i);
					computer.updateCount(c);
				}
				//make moves
				while (computer.hand.getValue() <= 21) {
					sleep(1000);
					String status = computer.makeMove(human.hand);
					if (status == "hit") {
						deck.dealTo(computer.hand, 1);
						System.out.println("Computer chose to hit.");
						computer.printHand();
					} else if (status == "stay") {
						System.out.println("Computer chose to stay.");
						break;
					} else {
						break;
					}
				}
			}

			//award chips to winner
			if (human.hand.getValue() > 21) {
				computer.chips += pot;
			} else if (computer.hand.getValue() > 21) {
				human.chips += pot;
			} else if (human.hand.getValue() > computer.hand.getValue()) {
				human.chips += pot;
			} else if (human.hand.getValue() == computer.hand.getValue()) {
				// tie
				computer.chips += computerBet;
				human.chips += humanBet;
			} else {
				computer.chips += pot;
			}

			//check if someone's chips are below minimum bet
			if (computer.chips < minBet) {
				return 1;
			} else if (human.chips < minBet) {
				return 2;
			}


			//discard cards
			human.hand.dealAll(discarded);
			computer.hand.dealAll(discarded);

			//if deck is low, refill and reshuffle
			if (deck.size() < 26) {
				discarded.dealAll(deck);
				deck.shuffle();
				computer.setCount(0);
			}

			// break from run() loop
			//return 1;
			return 0;
		}

		public void finish(int status) {
			if (status == 1) {
				System.out.println("You win!!");
			} else {
				System.out.println("Computer wins. Better luck next time!");
			}
		}

		public String makeMove() {
			//get input
			String[] valid = {"hit", "stay"};
			String input = in.getValid(true, valid, "Enter your move.", "Please enter \"hit\" or \"stay\".");
			if (input.equals("hit")) {
				deck.dealTo(human.hand, 1);
				if (human.hand.getValue() > 21) {
					System.out.println("Bust!");
					return "bust";
				}
				return "hit";
			} else {
				return "stay";
			}
		}

	}

	private static BlackjackGame game = new BlackjackGame();

    public static void main(String[] args) {
        game.run();
    }

}
