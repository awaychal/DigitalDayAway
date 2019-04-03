package deloitte.digital.away.day.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EveningBlockTest {

	@Test
	public void addTask_ok() {
		// Given
		EveningBlock block = new EveningBlock(60, 10);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = block.addActivity(activity);
		// Then
		assertTrue("Result must be true", result);
	}

	@Test
	public void addTask_overSize() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = block.addActivity(activity);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test
	public void addTask_nullTask() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		// when
		boolean result = block.addActivity(null);
		// Then
		assertFalse("Result must be false", result);
	}

	@Test
	public void getAvailableSize_empy() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 40", result == 40);
	}

	@Test
	public void getAvailableSize_half() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		block.addActivity(new Activity("test", 20));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 10", result == 20);
	}

	@Test
	public void getAvailableSize_full() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		block.addActivity(new Activity("test", 40));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}

	@Test
	public void getUsedSize_empy() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}

	@Test
	public void getUsedSize_half() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		block.addActivity(new Activity("test", 20));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 20", result == 20);
	}

	@Test
	public void getUsedSize_full() {
		// Given
		EveningBlock block = new EveningBlock(30, 10);
		block.addActivity(new Activity("test", 30));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}

}
