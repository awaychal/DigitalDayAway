package deloitte.digital.away.day;

import java.time.LocalTime;
import java.util.List;

import deloitte.digital.away.day.exception.DigitalAwayDayException;
import deloitte.digital.away.day.model.Activity;
import deloitte.digital.away.day.model.DigitalAwayDay;
import deloitte.digital.away.day.utils.FileUtils;
import deloitte.digital.away.day.utils.Utils;

/**
 * Main class. Reads a file and generates the away day
 * 
 * @author Amruta Waychal
 *
 */
public class Main {

	static String INPUT = "activities.txt";

	public static void main(String[] args) {

		FileUtils fileUtils = new FileUtils();
		Utils utils = new Utils();
		List<Activity> activities;
		Integer noOfPrograms;
		try {
			// 1. Load activities and
			activities = fileUtils.loadActivitiesFromFile(INPUT);

			// 2. Sort activities by desc order of duration
			activities = utils.sortActivitiesByDuration(activities);

			// 3. Find the no. of programs formed
			noOfPrograms = utils.findNoOfPrograms(activities);

			DigitalAwayDay digitalAwayDay = new DigitalAwayDay(LocalTime.of(9, 00), LocalTime.of(12, 00), LocalTime.of(13, 00),
					LocalTime.of(16, 00), 60, noOfPrograms);
			digitalAwayDay.addActivities(activities);
			
			System.out.println("Deloitte Away Day:");
			System.out.println(digitalAwayDay);

		} catch (DigitalAwayDayException e) {
			System.out.println("There was an error executing away day:" + e.getLocalizedMessage());
		}
	}
}
