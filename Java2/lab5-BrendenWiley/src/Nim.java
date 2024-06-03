public class Nim {

	private Player currentPlayer;
	private Player waitingPlayer;
	private Piles piles;
	private Player winner = null;
	private Player loser = null;

	public Nim(Player p1, Player p2, int[] initSizes) {
		currentPlayer = p1;
		waitingPlayer = p2;
		piles = new Piles(initSizes);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getWaitingPlayer() {
		return waitingPlayer;
	}

	public int[] getPileSizes() {
		return piles.getSizes();
	}

	public Player getWinner() {
		return winner;
	}

	public Player getLoser() {
		return loser;
	}

	public void takeTurn() {
		// TODO: Implement this method.
		
		int a = 0;
		
		int move[] = new int[2];
		
		
		for(int i = 0; i < 100; ++i) {
		move = currentPlayer.getMove(piles.getSizes());
			
		try {
			
		piles.removeObjects(move);
		
		waitingPlayer.notifyOpponentMove(currentPlayer.getName(), move);
		
		return;
		
		}
	
		catch(Exception IllegalMoveException) {
		
			currentPlayer.notifyIllegalMove(IllegalMoveException.getMessage());		
		}
			}
		}
		
	

	public void checkGameOver() {
		if (piles.isEmpty()) {
			winner = waitingPlayer;
			loser = currentPlayer;
		}
	}

	public void swapPlayers() {
		Player temp = currentPlayer;
		currentPlayer = waitingPlayer;
		waitingPlayer = temp;
	}

	public void play() {
		while (winner == null || loser == null) {
			takeTurn();
			checkGameOver();
			swapPlayers();
		}
		winner.notifyWin();
		loser.notifyLose();
	}
}
