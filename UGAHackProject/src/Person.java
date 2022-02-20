
public class Person {
		private double x;
		private double y; 
		public enum type {NORMAL, MAVEN, SALESPERSON, CONNECTOR} ;
		boolean isInfected; 
		public type t;
		
		
	public Person(double initialX, double initialY, type t, boolean initialInfected ) {
		x = initialX; 
		y = initialY;
		isInfected = initialInfected;
		this.t = t;
	}
	
	public void setX double newX) {
		x = newX
	}
	
	public void setX(double newY) {
		x = newX
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y; 
	}
	
	public void setType(type t) {
		this.t = t;
	}
	
	public boolean isInfected() {
		return this.isInfected;
	}
	
	public void setInfection(boolean x) {
		this.isInfected = x;
	}
	
	public void getType() {
		return this.t;
	}
	
	
	
	
}
