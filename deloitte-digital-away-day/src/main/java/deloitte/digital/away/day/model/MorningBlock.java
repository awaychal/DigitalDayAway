package deloitte.digital.away.day.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a group of activities with a delimited size
 * 
 * @author Amruta Waychal
 *
 */
public class MorningBlock {

	private List<Activity> activities;

	private Integer size;

	public MorningBlock(Integer size) {
		this.activities = new ArrayList<>();
		this.size = size;
	}

	/**
	 * Method to add a activity if there is enough free size
	 * 
	 * @param activity the activity to add
	 * @return
	 */
	public boolean addActivity(Activity activity) {
		
		if (activity != null && activity.getDuration() <= this.getAvailableSize()) {
			this.activities.add(activity);
			return true;
		}
		return false;
	}

	/**
	 * Method to retrieve the available size
	 * 
	 * @return available size
	 */
	public Integer getAvailableSize() {
		return this.size - activities.stream().mapToInt(activity -> activity.getDuration()).sum();
	}

	/**
	 * Method to retrieve the used size
	 * 
	 * @return used size
	 */
	public Integer getUsedSize() {
		return activities.stream().mapToInt(activity -> activity.getDuration()).sum();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		activities.forEach(activity -> buffer.append(activity).append(System.getProperty("line.separator")));
		return buffer.toString();
	}

}
