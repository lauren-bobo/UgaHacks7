import javafx.scene.shape.Circle;

enum PersonType {
	NORMAL,
	MAVEN, 
	SALESPERSON, 
	CONNECTOR;
	} //type enum

public class Person extends Circle {
	
	private static final int RADIUS = 3;

	PersonType personType;
	double sphereOfInfluence;
	double contagiousness;
	boolean isInfected; 
	Person[] proximity;
	
	public Person() {
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
			  getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		isInfected=false;
		this.personType = PersonType.valueOf("NORMAL");
		typeToValue();
	} //Person
		
	public Person(boolean initialInfected, PersonType personType) {
		super(getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), 
		      getRandomNumber(RADIUS + 2, 500 - RADIUS - 2), RADIUS);
		isInfected = initialInfected;
		this.personType = personType;
		typeToValue();
	} //Person
	
	public Person(double cenX, double cenY, boolean initialInfected, PersonType personType) {
		super(cenX, cenY, 3);
		isInfected = initialInfected;
		this.personType = personType;
		typeToValue();
	} //Person
	
	public void typeToValue() {
		switch(personType) {
		case MAVEN:
			contagiousness = 0.50;
			sphereOfInfluence = 30;
			break;
		case SALESPERSON:
			contagiousness = 0.30;
			sphereOfInfluence = 40;
			break;
		case CONNECTOR:
			contagiousness = 0.20;
			sphereOfInfluence = 50;
			break;
		default:
			contagiousness = 0.10;
			sphereOfInfluence = 15;
			break;
		} //switch
	} //typeToValue
	
	public boolean isInfected() {
		return this.isInfected;
	} //isInfected
	
	public void setInfection(boolean x) {
		this.isInfected = x;
	} //setInfection
	
	public double getSphereOfInfluence() {
		return sphereOfInfluence;
	} //getSphereOfInfluence
	
	public void setProximity(Person[] proximity) {
		this.proximity = proximity;
	} //setProximity
	/*
	public PersonType getPersonType() {
		return personType;
	} //getPersonType
	*/
	
	public double distanceTo(Person p) {
		return Math.sqrt(Math.pow((p.getCenterX() - this.getCenterX()), 2) + 
				Math.pow((p.getCenterY() - this.getCenterY()), 2));
	} //distanceTo
	
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
	
} //Person
