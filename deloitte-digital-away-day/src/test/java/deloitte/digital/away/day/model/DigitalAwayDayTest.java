package deloitte.digital.away.day.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import deloitte.digital.away.day.exception.DigitalAwayDayException;

public class DigitalAwayDayTest {


	@Test(expected=DigitalAwayDayException.class)
	public void addTasks_fewTasks() throws DigitalAwayDayException {
		// Given
		DigitalAwayDay awayDay = new DigitalAwayDay(LocalTime.of(9, 00), LocalTime.of(12, 00), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0, 2);
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("test1", 60));
		activities.add(new Activity("test2", 60));
		activities.add(new Activity("test3", 60));
		activities.add(new Activity("test4", 60));
		activities.add(new Activity("test5", 60));
		activities.add(new Activity("test6", 60));
		activities.add(new Activity("test7", 60));
		activities.add(new Activity("test8", 60));
		activities.add(new Activity("test9", 60));
		activities.add(new Activity("test10", 60));
		activities.add(new Activity("test11", 60));
		activities.add(new Activity("test12", 30));
		activities.add(new Activity("test13", 30));
		activities.add(new Activity("test14", 15));
		activities.add(new Activity("test15", 15));
		// when
		awayDay.addActivities(activities);
		// Then -> exception
	}

	@Test
	public void addTasks_manyTasks() throws DigitalAwayDayException {
		// Given
		DigitalAwayDay awayDay = new DigitalAwayDay(LocalTime.of(9, 00), LocalTime.of(12, 00), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 60, 2);
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("test1", 60));
		activities.add(new Activity("test2", 60));
		activities.add(new Activity("test3", 60));
		activities.add(new Activity("test4", 60));
		activities.add(new Activity("test5", 60));
		activities.add(new Activity("test6", 60));
		activities.add(new Activity("test7", 60));
		activities.add(new Activity("test8", 60));
		activities.add(new Activity("test9", 60));
		activities.add(new Activity("test10", 60));
		activities.add(new Activity("test11", 60));
		activities.add(new Activity("test12", 60));
		activities.add(new Activity("test13", 60));
		activities.add(new Activity("test14", 15));
		activities.add(new Activity("test15", 15));
		// when
		awayDay.addActivities(activities);
		// Then -> No exception
	}

}
