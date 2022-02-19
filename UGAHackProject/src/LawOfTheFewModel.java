public Class LawOfTheFewModel {

	int N = 100; // Population Size

//Array indices represent the equation's output at a given instant in time

	double[] SN = new double[N + 1]; // Succeptible Normal People in Population at given index
	double[] IN = new double[N + 1]; // Infected Normal People in Population

	double[] SC = new double[N + 1]; // Succeptible Connectors in Population
	double[] IC = new double[N + 1]; // Infected Connectors in Population

	double[] SM = new double[N + 1]; // Succeptible Mavens in Population
	double[] IM = new double[N + 1]; // Infected Mavens in Population

	double[] SP = new double[N + 1]; // Succeptible Salespeople in Population
	double[] IP = new double[N + 1]; // Infected Salespeople in Population

// The sum of the totals from each person type should always equal N, the population
// We'll say that the 3 types of exceptional people should make up 15% of the population (5 people each)

	SN[0] = 84; // Initial number of succeptible normal people at time 0
	IN[0] = 1; // Initial number of infected normal people at time 0

	SC[0] = 5; // Initial number of succeptible connectors at time 0
	IC[0] = 0; // Initial number of infected connectors at time 0
	
	SM[0] = 5; // Initial number of succeptible mavens at time 0
	IM[0] = 0; // Initial number of infected mavens at time 0

	SP[0] = 5; // Initial number of succeptible salespeople at time 0
	IP[0] = 0; // Initial number of infected salespeople at time 0

	double dt = timeElapsed / N;

	for (int i = 0; i < 100; ++i) {
		double t = i * dt;





	}

}