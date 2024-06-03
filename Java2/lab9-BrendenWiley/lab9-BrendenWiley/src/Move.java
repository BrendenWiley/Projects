public enum Move {

	LEFT_TO_MIDDLE(Peg.LEFT, Peg.MIDDLE), LEFT_TO_RIGHT(Peg.LEFT, Peg.RIGHT), MIDDLE_TO_LEFT(Peg.MIDDLE, Peg.LEFT), 

	MIDDLE_TO_RIGHT(Peg.MIDDLE, Peg.RIGHT), RIGHT_TO_LEFT(Peg.RIGHT, Peg.LEFT), RIGHT_TO_MIDDLE(Peg.RIGHT, Peg.MIDDLE);

	public final Peg from;


	public final Peg to;

	private Move(Peg from, Peg to) {

		this.from = from;
		this.to = to;

	}

	public static Move move(Peg from, Peg to) {

		if(from == null || to == null) {
			throw new NullPointerException();
		}

		if(from == to) {
			throw new IllegalArgumentException();
		}

		Peg p = Peg.other(from, to);

		if(p.equals(Peg.LEFT)) {

			if (from.equals(Peg.RIGHT)) {

				return Move.RIGHT_TO_MIDDLE;
			}

			else {

				return Move.MIDDLE_TO_RIGHT;

			}

		}

		else if(p.equals(Peg.RIGHT)) {

			if (from.equals(Peg.LEFT)) {

				return Move.LEFT_TO_MIDDLE;
			}

			else {

				return Move.MIDDLE_TO_LEFT;

			}

		} 

		else {

			if (from.equals(Peg.LEFT)) {

				return Move.LEFT_TO_RIGHT;
			}

			else {

				return Move.RIGHT_TO_LEFT;

			}

		}

	}

}
