package deloitte.digital.away.day.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import deloitte.digital.away.day.exception.DigitalAwayDayException;
import deloitte.digital.away.day.model.Activity;

public class UtilsTest {

	@Test
	public void sortActivitiesByDurationTest() throws DigitalAwayDayException {
		// Given
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("test1", 60));
		activities.add(new Activity("test2", 30));
		activities.add(new Activity("test3", 60));
		activities.add(new Activity("test4", 30));
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
		Utils utils = new Utils();
		utils.sortActivitiesByDuration(activities);
		// Then -> No exception
	}

	@Test
	public void findNoOfProgramsTest() throws DigitalAwayDayException {
		// Given
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity("test1", 60));
		activities.add(new Activity("test2", 30));
		activities.add(new Activity("test3", 60));
		activities.add(new Activity("test4", 30));
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
		Utils utils = new Utils();
		Integer no = utils.findNoOfPrograms(activities);
		// Then ->
		Assert.assertEquals(2, no.intValue());
	}

}
