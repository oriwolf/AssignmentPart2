package ponteraPart2;

import java.util.*;

public class OS {
	// Data structure maintains the sleeping jobs
	private static LinkedList<Job> sleepingJobs = new LinkedList<>();
	// Data structure maintains the running jobs
	private static Queue<Job> runningJobs = new LinkedList<>();

	/**
	 * Should put the job in the 'sleepingJobs' data structure.
	 * @param job
	 * @param milliSeconds
	 */
	public static void sleep(Job job, long milliSeconds) {
		ListIterator<Job> it = sleepingJobs.listIterator();
		job.setSleepTime(milliSeconds);
		if (!it.hasNext()) { // linked list is empty so just adding the job as the first element.
			sleepingJobs.add(0, job);
			return;
		}
		long time = sleepingJobs.element().getSleepTime();
		if (milliSeconds < time) {
			sleepingJobs.add(0, job); //adding the new job at the beginning
			sleepingJobs.get(1).decreaseSleepTimeBy(milliSeconds); // updating time so it waits as it should
			return;
		}
		int counter = 0;
		it.next();
		while (it.hasNext()) {
			Job next = it.next();
			counter++;
			if (time + next.getSleepTime() <= job.getSleepTime()) {
				time += next.getSleepTime();
			} else {
				break;
			}
		}
		long originalMS = job.getSleepTime();
		if (!it.hasNext()) {
			counter++; //so it will be added after the last element which stood in the while condition
			job.decreaseSleepTimeBy(time);
			sleepingJobs.add(counter, job);
			return;
		}
		if (counter < sleepingJobs.size()) {
			job.decreaseSleepTimeBy(time);
			sleepingJobs.add(counter, job);
			counter++;
			Job jobAfterNew = sleepingJobs.get(counter);
			long newTime = time + jobAfterNew.getSleepTime() - originalMS;
			jobAfterNew.setSleepTime(newTime);
		}
	}

	/**
	 * Should move the jobs from 'sleepingJobs' to 'runningJobs' when the time to sleep is up.
	 */
	public static void awake() {
		Iterator<Job> sleepIter = sleepingJobs.iterator();
		while (sleepIter.hasNext()) {
			Job curJob = sleepIter.next();
			if (curJob.getSleepTime() <= 0) {
				sleepIter.remove();
				runningJobs.add(curJob);
			} else {
				curJob.decreaseSleepTimeBy(1);
				return;
			}
		}
	}
}