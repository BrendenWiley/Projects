
public abstract class Polygon extends Shape{
	
	private double perimeter;
	
	Polygon(double... sides) {
		
		if(sides == null) {
			throw new IllegalArgumentException("null sides");
		}
		
		if(sides.length < 3) {
			throw new IllegalArgumentException("Invalid number of sides: " + sides.length);
		}
		
		for(int i = 0; i < sides.length; ++i) {
			
			if(sides[i] <= 0) {
				throw new IllegalArgumentException("Nonpositive side: " + sides[i]);
			}	
		}
		
	for(int i = 0; i < sides.length; ++i) {
		
		double sum = 0;
		
		for(int k = 0; k < sides.length; ++k) {
			
			sum = sum + sides[k];
			
		}
		
		sum = sum - sides[i];
		
		if(sides[i] >= sum) {
			throw new IllegalArgumentException("Polygon inequality violated: " + sides[i] + " >= " + sum);
		}
	}
	
	double sum = 0;
		
	for(int p = 0; p < sides.length; ++p) {
		
		sum = sum + sides[p];
		
	}
	
		this.perimeter = sum;

	}
	
	public double getPerimeter() {
		
		return perimeter;
		
	}
	
}
