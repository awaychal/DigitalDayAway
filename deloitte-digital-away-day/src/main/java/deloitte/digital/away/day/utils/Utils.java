package deloitte.digital.away.day.utils;

import java.util.List;
import java.util.stream.Collectors;

import deloitte.digital.away.day.exception.DigitalAwayDayException;
import deloitte.digital.away.day.model.Activity;

/**
 * Class to have some utility methods
 * 
 * @author Amruta Waychal
 *
 */
public class Utils {

	/**
	 * Method to get sorted list of activities
	 * 
	 * @param activities
	 * @return
	 * @throws DigitalAwayDayException
	 */
	public List<Activity> sortActivitiesByDuration(List<Activity> activities) throws DigitalAwayDayException {

		return activities.stream().sorted((t1, t2) -> t2.getDuration() - t1.getDuration()).collect(Collectors.toList());
	}

	/**
	 * Method to find no. of teams from duration of activities
	 * 
	 * @param activities
	 * @return
	 * @throws DigitalAwayDayException
	 */
	public Integer findNoOfPrograms(List<Activity> activities) throws DigitalAwayDayException {

		Integer sumOfDuration = activities.stream().mapToInt(activity -> activity.getDuration()).sum();

		// 7 hrs of activities per program
		Float totalHours = sumOfDuration / 60F;
		return Math.round(totalHours / 7);

	}

}
