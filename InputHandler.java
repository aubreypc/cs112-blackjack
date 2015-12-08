import java.util.Scanner;

class InputHandler {

	private Scanner scan = new Scanner(System.in);

	private class InvalidInputException extends Exception {
		public InvalidInputException () {
			super();
		}
	}

	public String get() {
		// returns input without any processing
		return scan.next();
	}

	public String get(boolean forceLowercase, String[] valid) throws InvalidInputException {
		// ensures input matches one of the items in valid array
		String input = get();
		for (int i = 0; i < valid.length; i++) {
			if (forceLowercase) {
				if (valid[i].toLowerCase().equals(input.toLowerCase())) {
					return input.toLowerCase();
				}
			} else {
				if (valid[i].equals(input)) {
					return input;
				}
			}
		}
		throw new InvalidInputException();
	}

	public String getValid(boolean forceLowercase, String[] valid, String prompt, String error) {
		// convenience method for looping until valid input is given
		System.out.println(prompt);
		while (true) {
			try {
				return get(forceLowercase, valid);
			} catch (InvalidInputException e) {
				System.out.println(error);
			}
		}
	}

	public int getIntInRange(int min, int max) {
		while (true) {
			int i = scan.nextInt();
			if (i >= min && i <= max) {
				return i;
			} else {
				System.out.println("Enter an integer between " + Integer.toString(min) + " and " + Integer.toString(max));
			}
		}
	}

}