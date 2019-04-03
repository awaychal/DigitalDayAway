package deloitte.digital.away.day.utils;

import org.junit.Test;

import deloitte.digital.away.day.exception.DigitalAwayDayException;

public class FileUtilsTest {
	
	@Test
	public void loadActivitiesFromFileTest() throws DigitalAwayDayException{
		FileUtils fileUtils =new FileUtils();
		fileUtils.loadActivitiesFromFile("activities.txt");
	}
	
	@Test(expected=DigitalAwayDayException.class)
	public void loadActivitiesFromFileTestException() throws DigitalAwayDayException{
		FileUtils fileUtils =new FileUtils();
		fileUtils.loadActivitiesFromFile("");
	}
	
	@Test(expected=DigitalAwayDayException.class)
	public void loadActivitiesFromFileTestFormatException() throws DigitalAwayDayException{
		FileUtils fileUtils =new FileUtils();
		fileUtils.loadActivitiesFromFile("InputTest.txt");
	}
	
	@Test(expected=DigitalAwayDayException.class)
	public void loadActivitiesFromFileTestForEmptyFile() throws DigitalAwayDayException{
		FileUtils fileUtils =new FileUtils();
		fileUtils.loadActivitiesFromFile("InputTestEmpty.txt");
	}


}
