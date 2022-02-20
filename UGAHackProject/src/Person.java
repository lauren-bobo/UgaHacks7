import javafx.scene.shape.Circle;

enum type {
	NORMAL, MAVEN, SALESPERSON, CONNECTOR
			}
public class Person extends Circle{

	private static final int RADIUS = 3;

	boolean isInfected; 
	public type t;
	
	public Person(){
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
			  getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		isInfected=false;
	} //Person
		
	public Person(type t, boolean initialInfected){
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
		      getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		this.t = t;
		isInfected = initialInfected;
	} //Person
	
	public Person(double cenX, double cenY, type t, boolean initialInfected){
		super(cenX, cenY, 3);
		this.t = t;
		isInfected = initialInfected;
	} //Person
	
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
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
	
} //Person
