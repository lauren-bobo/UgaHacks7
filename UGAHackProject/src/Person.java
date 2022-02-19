

public class Person {
	
		private double x;
		private double y; 
		public enum type {Normal, Maven, Salesperson, Connector} 
			
		public Person() {
		x = 0;
		y = 0; 
		}
		
		public Person(double initialX, double initialY, type t) {
			x = initialX; 
			y = initialY;
			type = t; 
			
		}
		
		public void setX (double newX) {
			x = newX;
		}
		
		public void setX(double newY) {
			y = newY;
		}
		public double getX() {
			return x;
		}
		public double getY() {
			return y; 
		}
		
		}
