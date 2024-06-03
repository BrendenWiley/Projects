
public abstract class Shape implements Comparable<Shape> {

	// TODO: Complete this class.
	
	private int id;
	
	private static int nextID = 0;
	
	
	Shape() {
		
		id = nextID;
		++nextID;
		
	}
	
	public int getID() {
		
		return id;
		
	}
	
	public abstract double getPerimeter();
	
	public abstract double getArea();
	
	@Override
	public int compareTo(Shape other) {
		
		if(getClass().getName() != other.getClass().getName()) {
			
			return getClass().getName().compareTo(other.getClass().getName());
			
		}
		else if (getPerimeter() != other.getPerimeter()) {
			
			if(getPerimeter() > other.getPerimeter()) {
				return 1;
			}
			else {
				return -1;
			}

		}
		else if(getArea() != other.getArea()) {
			
			if(getArea() > other.getArea()) {
				return 1;
			}
			else {
				return -1;
			}
			
		}
		return 0;
		
	}
	
	@Override
	public String toString() {
		return "<"
				+ getClass().getName()
				+ ", ID: " + id
				+ ", PERIMETER: " + String.format("%.1f", getPerimeter())
				+ ", AREA: " + String.format("%.1f", getArea())
				+ ">";
	}
	
}