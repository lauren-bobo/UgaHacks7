import javafx.scene.shape.Circle;

/*
 * Enum to track the type of the object.
 */
enum PersonType {
	NORMAL,
	MAVEN, 
	SALESPERSON, 
	CONNECTOR;
	} //type enum

/*
 * Person extends Circle.
 * This is done for the coordinates and visuals.
 */
public class Person extends Circle {
	
	private static final int RADIUS = 3;

	PersonType personType;
	double sphereOfInfluence;
	double contagiousness;
	boolean isInfected; 
	Person[] proximity;
	
	/*
	 * Non-parametized constructor, adds circle to board
	 * randomly and defaults person to Normal.
	 */
	public Person() {
		super(getRandomNumber(RADIUS + 2, 400 - RADIUS - 2), 
			  getRandomNumber(RADIUS + 2, 400 - RADIUS - 2), RADIUS);
		isInfected = false;
		this.personType = PersonType.valueOf("NORMAL");
		typeToValue();
	} //Person
		
	/*
	 * Like non-parametized constructor except it takes in a
	 * person type for the Person object.
	 * @param PersonType the type of the person
	 */
	public Person(PersonType personType) {
		super(getRandomNumber(RADIUS + 2, 400 - RADIUS - 2), 
		      getRandomNumber(RADIUS + 2, 400 - RADIUS - 2), RADIUS);
		isInfected = false;
		this.personType = personType;
		typeToValue();
	} //Person
	
	/*
	 * Parametized constructor that allows for control over the position,
	 * if initially infected, and the person type.
	 * @param: cenX the center X of the circle
	 * @param: cenY the center Y of the circle
	 * @param: initialInfected whether or not the Person starts off infected
	 * @param: PersonType the type of person
	 */
	public Person(double cenX, double cenY, boolean initialInfected, PersonType personType) {
		super(cenX, cenY, 3);
		isInfected = initialInfected;
		this.personType = personType;
		typeToValue();
	} //Person
	
	/*
	 * Converts the type of the Person to their respective
	 * contagiousness and sphereOfInfluence variables.
	 */
	public void typeToValue() {
		switch(personType) {
		case MAVEN:
			contagiousness = 0.025;
			sphereOfInfluence = 70;
			break;
		case SALESPERSON:
			contagiousness = 0.015;
			sphereOfInfluence = 100;
			break;
		case CONNECTOR:
			contagiousness = 0.010;
			sphereOfInfluence = 150;
			break;
		default:
			contagiousness = 0.005;
			sphereOfInfluence = 60;
			break;
		} //switch
	} //typeToValue
	
	/*
	 * Getter method for whether or not the Person is infected.
	 * @return isInfected
	 */
	public boolean isInfected() {
		return isInfected;
	} //isInfected
	
	/*
	 * Sets the status of the person being infected.
	 * @param isInfected boolean
	 */
	public void setInfection(boolean isInfected) {
		this.isInfected = isInfected;
	} //setInfection
	
	/*
	 * Getter method for the sphere of influence (radius).
	 * @return double sphereOfInfluence 
	 */
	public double getSphereOfInfluence() {
		return sphereOfInfluence;
	} //getSphereOfInfluence
	
	/*
	 * Setter method for the proximity array.
	 * @param Person array
	 */
	public void setProximity(Person[] proximity) {
		this.proximity = proximity;
	} //setProximity
	
	/*
	 *  Getter method for the Proximity array.
	 *  @return returns proximity array
	 */
	public Person[] getProximity() {
		return proximity;
	} //getProximity
	
	/*
	 * Getter method for contagiousness value.
	 * @return returns double for the contagiousness
	 */
	public double getContagiousness() {
		return contagiousness;
	} //getContagiousness
	
	/*
	 * Getter method for the person type.
	 * @return returns the Person type the person is.
	 */
	public PersonType getPersonType() {
		return personType;
	} //getPersonType

	/*
	 * Calculates the distance from one person to another.
	 * @param Person Person to calculate the distance from
	 * @return the distance from the other person
	 */
	public double distanceTo(Person p) {
		return Math.sqrt(Math.pow((p.getCenterX() - this.getCenterX()), 2) + 
				Math.pow((p.getCenterY() - this.getCenterY()), 2));
	} //distanceTo
	
	/*
	 * Generates a random number between the two parameters,
	 * inclusive and exclusive respectively.
	 * @param int min (inclusive)
	 * @param int max (exclusive)
	 * @return the random int
	 */
	public static int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	} //getRandomNumber
	
} //Person
