package deloitte.digital.away.day.model;

import java.time.LocalTime;

/**
 * Class for activity information
 * 
 * @author Amruta Waychal
 *
 */
public class Activity {

	static String SPRINT = "sprint";

	private String name;

	private LocalTime startTime;

	private Integer duration;

	public Activity(String name, Integer duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	private StringBuffer addLunchBreakActivity(StringBuffer buffer) {
		if(LocalTime.of(13, 00).equals(this.startTime)) {
					buffer.append(LocalTime.of(12, 00)+" pm : Lunch Break 60min\n");
		}
		return buffer;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		//to add 12.00-13.00 as lunch time activity
		addLunchBreakActivity(buffer);
		buffer.append(this.startTime + " ").append((this.getStartTime().getHour() < 12 ? "am" : "pm"))
				.append(" : " + this.getName());
		return buffer.toString();

	}

}
