public class Triangle {
	//declare instance variables
	private double sideA;
	private double sideB;
	private double sideC;


	public final static String POLYGONSHAPE = "Triangle";

	public final static double DEFAULT_SIDE = 1;

	//Constructors
	public Triangle() {

		this.sideA = DEFAULT_SIDE;
		this.sideB = DEFAULT_SIDE;
		this.sideC = DEFAULT_SIDE;

	}

	public Triangle(double sideA, double sideB, double sideC) {
		
		if(isTriangle(sideA, sideB, sideC)) {

			this.sideA = sideA;
			this.sideB = sideB;
			this.sideC = sideC;

		}
		else {

			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;

		}

	}

	public Triangle(double[] sides) {

		if(isTriangle(sides)) {

			sideA = sides[0];
			sideB = sides[1];
			sideC = sides[2];

		}

		else {

			sideA = DEFAULT_SIDE;
			sideB = DEFAULT_SIDE;
			sideC = DEFAULT_SIDE;
		}

	}

	public Triangle(Triangle triangle) {

		if(triangle != null) {

			this.sideA = triangle.getSideA();
			this.sideB = triangle.getSideB();
			this.sideC = triangle.getSideC();

		}
		else {
			this.sideA = DEFAULT_SIDE;
			this.sideB = DEFAULT_SIDE;
			this.sideC = DEFAULT_SIDE;
		}

	}

	public double getSideA() {
		return sideA;
	}
	public double getSideB() {
		return sideB;
	}
	public double getSideC() {
		return sideC;
	}
	public double[] getSides() {
		
		double[] sides = new double[3];
		
		sides[0] = getSideA();
		sides[1] = getSideB();
		sides[2] = getSideC();

		return sides;
	}



	public double getAngleA() {

		double angleA = lawOfCosines(sideC, sideB, sideA);

		return angleA;


	}
	public double getAngleB() {

		double angleB = lawOfCosines(sideC, sideA, sideB);
		return angleB;

	}
	public double getAngleC() {

		double angleC = lawOfCosines(sideA, sideB, sideC);

		return angleC;

	}
	public double[] getAngles() {

		double[] angles = {0, 0, 0};

		angles[0] = lawOfCosines(sideC, sideB, sideA);
		angles[1] = lawOfCosines(sideC, sideA, sideB);
		angles[2] = lawOfCosines(sideA, sideB, sideC);

		return angles;
	}

	public boolean setSideA(double sideA) {

		if(isTriangle(sideA, this.sideB, this.sideC)) {
			this.sideA = sideA;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean setSideB(double sideB) {

		if(isTriangle(this.sideA, sideB, this.sideC)) {
			this.sideB = sideB;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean setSideC(double sideC) {

		if(isTriangle(this.sideA, this.sideB, sideC)) {
			this.sideC = sideC;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean setSides(double[] sides) {

		
		
		
		if(isTriangle(sides)) {

			sideA = sides[0];
			sideB = sides[1];
			sideC = sides[2];

			return true;

		}
		else {

			return false;
		}

	}

	public static boolean isTriangle(double a, double b, double c) {

		if((a < (b + c)) && (b < (a + c)) && (c < (a + b)) && (a > 0) && (b > 0) && (c > 0)) {
			return true;
		}
		else {
			return false;
		}

	}
	
	public static boolean isTriangle(double[] sides) {

		if(sides == null) {
			return false;
		}

		if(sides.length != 3) {

			return false;

		}

		double a = sides[0];
		double b = sides[1];
		double c = sides[2];

		if((a < (b + c)) && (b < (a + c)) && (c < (a + b)) && (a > 0) && (b > 0) && (c > 0)) {
			return true;
		}
		else {
			return false;
		}
	
	}

	public static double lawOfCosines(double a, double b, double c) {

		return Math.toDegrees(Math.acos((Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2)) / (-2 * b * a)));

	   
	}


	public String toString() {

		String triangle = POLYGONSHAPE +"(" + String.format("%.4f", sideA) + ", "+ 
				String.format("%.4f", sideB) + ", " + String.format("%.4f", sideC) + ")";

		return triangle;

	}

}
