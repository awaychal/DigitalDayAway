package deloitte.digital.away.day.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import deloitte.digital.away.day.exception.DigitalAwayDayException;
import deloitte.digital.away.day.model.Activity;

/**
 * Class to manage file load and save
 * 
 * @author Amruta Waychal
 *
 */
public class FileUtils {

	static String REGEX = "^[a-zA-Z0-9][a-zA-Z0-9& -]* (([0-9]+min)|sprint)$";
	static String SPRINT = "sprint";

	/**
	 * Method to generate a list of activities from a file stored in file resources
	 * 
	 * @param fileName
	 *            File name in resources
	 * @return List of activities
	 * @throws DigitalAwayDayException
	 */
	public List<Activity> loadActivitiesFromFile(String fileName) throws DigitalAwayDayException {
		List<Activity> activities = new ArrayList<>();
		try {
			Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
			Stream<String> lines;
			lines = Files.lines(path);
			Iterator<String> iterator = lines.iterator();
			while (iterator.hasNext()) {
				String line = iterator.next();
				if (Pattern.matches(REGEX, line)) {
					Integer duration = this.getMinutes(line.substring(line.lastIndexOf(" ") + 1));
					activities.add(new Activity(line, duration));
				} else {
					lines.close();
					throw new DigitalAwayDayException(
							"FileUtils:loadActivitiesFromResources(): File line " + line + " has not a valid format");
				}
			}
			lines.close();
			if(activities.isEmpty())
				throw new DigitalAwayDayException(
						"FileUtils:loadActivitiesFromResources(): File is empty");
			
		} catch (Exception ex) {
			throw new DigitalAwayDayException(
					"FileUtils:loadActivitiesFromFile(): there was a problem loading activities from file:"
							+ ex.getLocalizedMessage());
		}
		return activities;
	}

	/**
	 * Method to get minutes value
	 * 
	 * @param value
	 * @return
	 * @throws DigitalAwayDayException
	 */
	private Integer getMinutes(String value) throws DigitalAwayDayException {
		Integer result = 0;
		if (SPRINT.equals(value))
			result = 15;
		else
			result = Integer.valueOf(value.substring(0, value.length() - 3));

		if (result <= 0 || result > 60) {
			throw new DigitalAwayDayException("FileUtils:getMinutes(): invalid activity duration: " + result);
		}
		return result;
	}

}
