
public class IsoscelesTrapezoid extends Polygon{ 
	
	private double top;
	private double base;
	private double leg;
	private double area;
	private double triangleBase;
	private double height;
	
	public IsoscelesTrapezoid(double top, double base, double leg) {
		
		super(top, base, leg, leg);
		this.top = top;
		this.base = base;
		this.leg = leg;
		this.triangleBase = (base - top) / 2.0;
		this.height = Math.sqrt(Math.pow(leg, 2) - Math.pow(triangleBase, 2));
		this.area = ((top + base) / 2.0) * height;
	
	}
	
	public double getTop() {
		
		return top;
		
	}
	
	public double getBase() {
		
		return base;
		
	}
	
	public double getLeg() {
		
		return leg;
	}
	
	@Override
	public double getArea() {
		
		return area;
		
	}
	
	public Rectangle getCenterRectangle() {
		
		Rectangle rectangle;
		
		if(top > base) {
			rectangle = new Rectangle(base, height);
		}
		else {
			rectangle = new Rectangle(top, height);
		}
		
		return rectangle;
		
	}
	
	
	
}
