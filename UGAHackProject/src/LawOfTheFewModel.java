public class LawOfTheFewModel {
	
public final double THRESHOLDFORINFECTION = .50; 
double stickiness = .30; 
	/* 
	 * @param a
	 * @return
	 */
	public int getNumInfected(Person[] a) {
		int numInfected = 0;
		for (Person peep: a) {
			if(peep.isInfected()) {
				numInfected++;
			}
		}
		return numInfected; 
	} //getNumInfected
	
	/**
	 * 
	 */
	public int getNumHealthy() {
		int pop = Main.getPopulation();
		return pop - getNumInfected(); 
	} //getNumHealthy
	
	/**
	 * 
	 * @param a
	 * @param b
	 */
	public void attemptInfect(Person i, Person j) {
			double susceptible = Person.getRandomNumber(0,100);
			double contaminate = (i.getContagiousness() * 100) + (stickiness * 100);
			if (contaminate > susceptible) {
				j.setInfection(true);
			} //if	
	} //attemptInfect
		
		public void spread(Person[] population) {
			for( int i = 0 ; i< population.length; i++) {
				if (population[i].isInfected()) {
					Person[] proximity = population[i].getProximity();
					for (int j = 0; j < proximity.length; j++) {
						if (!proximity[j].isInfected()) {
							attemptInfect(population[i], population[j]);
						} //if
					} //for
				} //if
			} //for
		} //spread
		
	} //LawOfTheFew


/*
double[] SN = new double[N + 1]; // Succeptible Normal People in Population at given index
double[] IN = new double[N + 1]; // Infected Normal People in Population

double[] SC = new double[N + 1]; // Succeptible Connectors in Population
double[] IC = new double[N + 1]; // Infected Connectors in Population

double[] SM = new double[N + 1]; // Succeptible Mavens in Population
double[] IM = new double[N + 1]; // Infected Mavens in Population

double[] SP = new double[N + 1]; // Succeptible Salespeople in Population
double[] IP = new double[N + 1]; // Infected Salespeople in Population


/**
	int N = 100; // Population Size
	//Array indices represent the equation's output at a given instant in time
		
		People[] TotalSucceptible = new double[N + 1];
		People[] TotalInfected = new double[N + 1];

	

// The sum of the totals from each person type should always equal N, the population
// We'll say that the 3 types of exceptional people should make up 15% of the population (5 people each of 100)
// And also that the idea starts from one Normal person.
	double initialPercentInfected = (1 / N) ; 
	TotalSucceptible[0] = (N-1);
	TotalInfected[0] = 1;
	
	SN[0] = (.15 * N) + (initialPercentInfected); // Initial number of succeptible normal people at time 0
	IN[0] = 1; // Initial number of infected normal people at time 0

	SC[0] = (.05 * N); // Initial number of succeptible connectors at time 0
	IC[0] = 0; // Initial number of infected connectors at time 0
	
	SM[0] = (.05 * N); // Initial number of succeptible mavens at time 0
	IM[0] = 0; // Initial number of infected mavens at time 0

	SP[0] = (.05 * N); // Initial number of succeptible salespeople at time 0
	IP[0] = 0; // Initial number of infected salespeople at time 0

	double dt = 100 / N; // Total Time of Simulation (100 days) / N

	for (int time = 0 ; time < N ; ++i) { 
	// i represents the number of steps taken. i must be less than N so it doesn't exceed the array bounds.
	// In this case, i also represents a given unit time because the # steps = # of days the simulation covers.
	// This is neccesary so # of succeptible and infecteds can be graphed with respect to i days.
	// If you want a different total time, like 365 days, i needs to be increased each loop so that it takes 365 steps to reach N.
		double t = i * dt;
	// People that the idea is shared with per day = 3 Chance of idea spreading = 0.1
		double beta = 0.3;
				
	// Differential Equations
	// Beta * The Total # of Succeptible from the specific category * Total # of Infected
	// To represent the increased infectivity of our exceptional individuals, their numbers are multiplied by a factor.
	// 2 for connectors, 2.5 for mavens, 3 for salespeople.
		double dSNdt = - beta * SN[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dSCdt = - beta * SC[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dSMdt = - beta * SM[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dSPdt = - beta * SP[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		
		double dINdt = beta * SN[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dICdt = beta * SC[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dIMdt = beta * SM[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		double dIPdt = beta * SP[i] * (IN[i] + (2 * IC[i]) + (2.5 * IM[i]) + (3 * IP[i]));
		
	// Integrate to find population at given i + 1 or current time 
		SN[i + 1] = SN[i] + dSNdt * dt;
		IN[i + 1] = IN[i] + dINdt * dt;

		SC[i + 1] = SC[i] + dSCdt * dt;
		IC[i + 1] = IC[i] + dICdt * dt;
		
		SM[i + 1] = SM[i] + dSMdt * dt;
		IM[i + 1] = IM[i] + dIMdt * dt;
		
		SP[i + 1] = SP[i] + dSPdt * dt;
		IP[i + 1] = IP[i] + dIPdt * dt;
		
		TotalSucceptible[i + 1] = SN[i + 1] + SC[i + 1] + SM[i + 1] + SP[i + 1];
		TotalInfected[i + 1] = IN[i + 1] + IC[i + 1] + IM[i + 1] + IP[i + 1];
	
	} // for
	
} // LawOfTheFewModel
	*/
