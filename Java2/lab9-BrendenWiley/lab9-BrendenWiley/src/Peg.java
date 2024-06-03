public enum Peg{
		
		LEFT, MIDDLE, RIGHT;
		
		public static Peg other(Peg p1, Peg p2) {
			
			if(p1.equals(p2)) {
				throw new IllegalArgumentException();
			}
			
			if(p1 == null || p2 == null) {
				throw new NullPointerException();
			}
			
			if(p1 == LEFT && p2 == MIDDLE) {
				Peg p = RIGHT;
				return p;
			}
			
			if(p1 == LEFT && p2 == RIGHT) {
				Peg p = MIDDLE;
				return p;
			}
			
			if(p1 == MIDDLE && p2 == LEFT) {
				Peg p = RIGHT;
				return p;
			}
			
			if(p1 == MIDDLE && p2 == RIGHT) {
				Peg p = LEFT;
				return p;
			}
			
			if(p1 == RIGHT && p2 == MIDDLE) {
				Peg p = LEFT;
				return p;
			}
			
			else {
				Peg p = MIDDLE;
				return p;
			}
			
	
			
			
		}

		
	}
