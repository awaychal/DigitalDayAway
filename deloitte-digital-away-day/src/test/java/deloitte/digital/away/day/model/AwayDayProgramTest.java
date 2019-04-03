package deloitte.digital.away.day.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import deloitte.digital.away.day.exception.DigitalAwayDayException;

public class AwayDayProgramTest {

	@Test
	public void insertMorningTask_ok() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(12, 00), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = dayProgram.insertMorningTask(activity);
		// Then
		assertTrue("Result must be true", result);
	}

	@Test
	public void insertMorningTask_noSpace() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = dayProgram.insertMorningTask(activity);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test
	public void insertMorningTask_nullTask() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		// when
		boolean result = dayProgram.insertMorningTask(null);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test(expected = DigitalAwayDayException.class)
	public void insertMorningTask_constructorFail() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(8, 30), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		// when
		dayProgram.insertMorningTask(null);
		// Then -> exception
	}

	@Test
	public void insertEveningTask_ok() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(12, 00), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = dayProgram.insertEveningTask(activity);
		// Then
		assertTrue("Result must be true", result);
	}

	@Test
	public void insertEveningTask_noSpace() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 0);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = dayProgram.insertEveningTask(activity);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test
	public void insertEveningTask_nullTask() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(16, 00), 0);
		// when
		boolean result = dayProgram.insertEveningTask(null);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test(expected = DigitalAwayDayException.class)
	public void insertEveningTask_constructorFail() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(12, 00), 0);
		// when
		dayProgram.insertEveningTask(null);
		// Then -> exception
	}

	@Test
	public void getMinDuration_ok() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 0);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}

	@Test
	public void getMinDuration_ok_extra() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 10);
		// when
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}

	@Test
	public void getMinDuration_oneTask() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 0);
		// when
		dayProgram.insertMorningTask(new Activity("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}

	@Test
	public void getMaxDuration_ok() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 0);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 60", result == 60);
	}

	@Test
	public void getMaxDuration_ok_extra() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 10);
		// when
		Integer result = dayProgram.getMaxDuration();
		// Then
		assertTrue("Result must be 70", result == 70);
	}

	@Test
	public void getMaxDuration_oneTask() throws DigitalAwayDayException {
		// Given
		AwayDayProgram dayProgram = new AwayDayProgram(LocalTime.of(9, 00), LocalTime.of(9, 30), LocalTime.of(13, 00),
				LocalTime.of(13, 30), 0);
		// when
		dayProgram.insertMorningTask(new Activity("test", 30));
		Integer result = dayProgram.getMinDuration();
		// Then
		assertTrue("Result must be 30", result == 30);
	}

}
