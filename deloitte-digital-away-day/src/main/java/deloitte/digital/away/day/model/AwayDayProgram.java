package deloitte.digital.away.day.model;

import java.time.Duration;
import java.time.LocalTime;

import deloitte.digital.away.day.exception.DigitalAwayDayException;

/**
 * Class to manage a away day program with two blocks (morning and evening)
 * 
 * @author Amruta Waychal
 *
 */
public class AwayDayProgram {

	private LocalTime morningStart;
	private MorningBlock morning;

	private LocalTime eveningStart;
	private EveningBlock evening;

	public AwayDayProgram(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart, LocalTime eveningEnd,
			Integer eveningExtraTime) throws DigitalAwayDayException {

		this.checkAwayDayProgram(morningStart, morningEnd, eveningStart, eveningEnd);

		this.morningStart = morningStart;
		this.morning = new MorningBlock(getBlockSize(morningStart, morningEnd));
		this.eveningStart = eveningStart;
		this.evening = new EveningBlock(getBlockSize(eveningStart, eveningEnd), eveningExtraTime);
	}

	/**
	 * Method to insert a task in morning block
	 * 
	 * @param task
	 *            the task to insert
	 * @return true if inserted
	 */
	public boolean insertMorningTask(Activity task) {

		if (task != null) {
			task.setStartTime(this.morningStart.plusMinutes(morning.getUsedSize()));
			return this.morning.addActivity(task);
		}
		return false;
	}

	/**
	 * Method to insert a task in evening block
	 * 
	 * @param task
	 *            the task to insert
	 * @return true if inserted
	 */
	public boolean insertEveningTask(Activity task) {

		if (task != null) {
			task.setStartTime(this.eveningStart.plusMinutes(evening.getUsedSize()));
			return this.evening.addActivity(task);
		}
		return false;
	}

	/**
	 * Method to get block size based in start and end time
	 * 
	 * @param start
	 *            the LocalTime when this block starts
	 * @param end
	 *            the LocalTime when this block ends
	 * @return block size in minutes
	 * @throws Exception
	 */
	private Integer getBlockSize(LocalTime start, LocalTime end) throws DigitalAwayDayException {

		Duration duration = Duration.between(start, end);
		if (duration.getSeconds() > 0) {
			return (int) (duration.getSeconds() / 60);
		}
		throw new DigitalAwayDayException("AwayDayProgram.getBlockSize(): End time must be after start time");
	}

	/**
	 * Method to get the min day program duration in minutes
	 * 
	 * @return Day program min duration (minutes)
	 */
	public Integer getMinDuration() {

		return this.morning.getAvailableSize() + this.evening.getAvailableSize() - this.evening.getExtraTime();
	}

	/**
	 * Method to get the max day program duration in minutes
	 * 
	 * @return Day program max duration (minutes)
	 */
	public Integer getMaxDuration() {

		return this.morning.getAvailableSize() + this.evening.getAvailableSize();
	}

	/**
	 * @param morningStart
	 * @param morningEnd
	 * @param eveningStart
	 * @param eveningEnd
	 * @return
	 * @throws DigitalAwayDayException
	 */
	private boolean checkAwayDayProgram(LocalTime morningStart, LocalTime morningEnd, LocalTime eveningStart,
			LocalTime eveningEnd) throws DigitalAwayDayException {

		if (morningStart != null && eveningStart != null && eveningStart != null && eveningEnd != null
				&& eveningEnd.isAfter(morningStart)) {
			return true;
		} else {
			throw new DigitalAwayDayException(
					"AwayDayProgram.checkDayProgram(): There was a problem creating the program");
		}
	}

	public MorningBlock getMorning() {
		return morning;
	}

	public void setMorning(MorningBlock morning) {
		this.morning = morning;
	}

	public EveningBlock getEvening() {
		return evening;
	}

	public void setEvening(EveningBlock evening) {
		this.evening = evening;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(morning);
		buffer.append(evening);
		return buffer.toString();
	}

}
