import javafx.scene.shape.Circle;

enum Type {
	NORMAL(0.10, 15),
	MAVEN(0.50, 30), 
	SALESPERSON(0.30, 40), 
	CONNECTOR(0.20, 50);
	
	private double contagiousness;
	private int sphereOfInfluence;
	
	private Type(final double contagiousness, final int sphereOfInfluence) {
		this.contagiousness = contagiousness;
		this.sphereOfInfluence = sphereOfInfluence;
	} //Type
	
	public double getContagiousness() {
		return contagiousness;
	} //getContagiousness
	public int getSphereOfInfluence() {
		return sphereOfInfluence;
	}
} //type enum

public class Person extends Circle {

	private static final int RADIUS = 3;

	int sphereOfInfluence;
	double contagiousness;
	boolean isInfected; 
	
	public Person() {
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
			  getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		isInfected=false;
	} //Person
		
	public Person(boolean initialInfected) {
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
		      getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		isInfected = initialInfected;
	} //Person
	
	public Person(double cenX, double cenY, boolean initialInfected) {
		super(cenX, cenY, 3);
		isInfected = initialInfected;
	} //Person
	
	public boolean isInfected() {
		return this.isInfected;
	}
	
	public void setInfection(boolean x) {
		this.isInfected = x;
	}
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
	
} //Person
