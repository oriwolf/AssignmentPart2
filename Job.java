package ponteraPart2;

public class Job {

	// Stands for the remaining sleep time of a job instance
	private long sleepTime;

	/**
	 * An empty constructor initiate the sleep time to zero when creates an instance.
	 */
	public Job() {
		setSleepTime(0L);
	}

	/**
	 * @param sleepTime - the amount of time a job should sleep
	 */
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * @param dec - the amount of time to subtract from the remaining sleeping time
	 */
	public void decreaseSleepTimeBy(long dec) {
		this.sleepTime -= dec;
	}

	/**
	 * @return - the current remaining sleep time
	 */
	public long getSleepTime() {
		return sleepTime;
	}

	// A function for sleep which called by a job
	public void sleep(long ms) {
		OS.sleep(this, ms);
	}

}