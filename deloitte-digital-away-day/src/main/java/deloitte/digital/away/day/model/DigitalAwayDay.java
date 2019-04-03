package deloitte.digital.away.day.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import deloitte.digital.away.day.exception.DigitalAwayDayException;

/**
 * Class to manage an away day
 * 
 * @author Amruta Waychal
 *
 */
public class DigitalAwayDay {

	List<AwayDayProgram> programs = new ArrayList<>();

	public DigitalAwayDay(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime, Integer numberOfPrograms) throws DigitalAwayDayException {

		for (int i = 0; i < numberOfPrograms; i++) {
			programs.add(new AwayDayProgram(morningStart, morningEnd, eveningStart, eveningEnd, eveningExtraTime));
		}
	}

	/**
	 * Method to add a list of activities into the digital away day
	 * 
	 * @param activities
	 *            The list of activities to be added
	 * @throws DigitalAwayDayException
	 */
	public void addActivities(List<Activity> activities) throws DigitalAwayDayException {

		this.checkTasks(activities);

		int count = 0;
		int totalMornEvenSlots = 2 * programs.size();

		for (Activity activity : activities) {
			boolean result = false;
			while (!result) {
				if (count == totalMornEvenSlots) {
					count = 0;
				}
				// Morning Slots
				if (count < (totalMornEvenSlots / 2)) {
					result = programs.get(count).insertMorningTask(activity);
				//	Evening Slots
				}
				if (count >= (totalMornEvenSlots / 2)) {
					result = programs.get(count % 2).insertEveningTask(activity);

				}

				count = count + 1;
			}
		}
	}

	/**
	 * Method to check activities before insert
	 * 
	 * @param activities
	 *            The activities to be checked
	 * @throws DigitalAwayDayException
	 * 
	 */
	private void checkTasks(List<Activity> activities) throws DigitalAwayDayException {

		Integer activitiesTime = activities.stream().mapToInt(activity -> activity.getDuration()).sum();
		Integer minTime = programs.stream().mapToInt(program -> program.getMinDuration()).sum();
		Integer maxTime = programs.stream().mapToInt(program -> program.getMaxDuration()).sum();
		if (activitiesTime < minTime || activitiesTime > maxTime) {
			throw new DigitalAwayDayException("DigitalAwayDay.checkTasks(): The list of activities has not the proper duration");
		}

	}

	public List<AwayDayProgram> getPrograms() {
		return programs;
	}

	public void setPrograms(List<AwayDayProgram> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("");
		Integer count = 1;
		for (AwayDayProgram dayProgram : programs) {
			buffer.append("Team " + count + ":").append(System.getProperty("line.separator")).append(dayProgram)
					.append(System.getProperty("line.separator"));
			count++;
		}
		return buffer.append(System.getProperty("line.separator")).toString();
	}

}
