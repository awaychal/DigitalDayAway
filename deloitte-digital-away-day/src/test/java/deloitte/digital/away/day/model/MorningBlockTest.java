package deloitte.digital.away.day.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MorningBlockTest {

	@Test
	public void addTask_ok() {
		// Given
		MorningBlock block = new MorningBlock(60);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = block.addActivity(activity);
		// Then
		assertTrue("Result must be true", result);
	}
	
	@Test
	public void addTask_overSize() {
		// Given
		MorningBlock block = new MorningBlock(30);
		Activity activity = new Activity("test", 60);
		// when
		boolean result = block.addActivity(activity);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void addTask_nullTask() {
		// Given
		MorningBlock block = new MorningBlock(30);
		// when
		boolean result = block.addActivity(null);
		// Then
		assertFalse("Result must be false", result);
	}
	
	@Test
	public void getAvailableSize_empy() {
		// Given
		MorningBlock block = new MorningBlock(30);
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
	@Test
	public void getAvailableSize_half() {
		// Given
		MorningBlock block = new MorningBlock(30);
		block.addActivity(new Activity("test", 20));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 10", result == 10);
	}
	
	@Test
	public void getAvailableSize_full() {
		// Given
		MorningBlock block = new MorningBlock(30);
		block.addActivity(new Activity("test", 30));
		// when
		Integer result = block.getAvailableSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	
	@Test
	public void getUsedSize_empy() {
		// Given
		MorningBlock block = new MorningBlock(30);
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 0", result == 0);
	}
	
	@Test
	public void getUsedSize_half() {
		// Given
		MorningBlock block = new MorningBlock(30);
		block.addActivity(new Activity("test", 20));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 20", result == 20);
	}
	
	@Test
	public void getUsedSize_full() {
		// Given
		MorningBlock block = new MorningBlock(30);
		block.addActivity(new Activity("test", 30));
		// when
		Integer result = block.getUsedSize();
		// Then
		assertTrue("Result must be 30", result == 30);
	}
	
}
