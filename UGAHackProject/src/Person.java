 enum type {
	NORMAL, MAVEN, SALESPERSON, CONNECTOR
			}
public class Person {
	
		private double x;
		private double y; 
		boolean isInfected; 
		public type t;
		
		public Person(){
			x=0;
			y=0;
			isInfected=false;
		}
		public Person(double initialX, double initialY, type t, boolean initialInfected){
		this.t = t;
		x = initialX; 
		y = initialY;
		isInfected = initialInfected;
	}
	
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
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
	
	public type getType() {
		return t;
	}
	
	
	
	
}
