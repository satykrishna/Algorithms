package example.test.refactor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import refactor.testing.lambda.Point;

public class TestPoint {

	@Test
	
	public void testPoint() throws Exception {
		
		Point p1 = new Point(5, 5);
		
		Point p2 = p1.moveRightBy(10);
		
		assertEquals(15, p2.getX());
		
		assertEquals(5, p2.getY());
		
		int result = Point.compareByXandThenY.compare(p1, p2);
		
		assertEquals(-1, result);
	}
	
	@Test
	
	public void testPointstoRight() throws Exception {
		
		List<Point> points = 
				Arrays.asList(new Point(4, 4), new Point(10, 5));
		
		List<Point> expectedPoints = 
				Arrays.asList(new Point(14, 4), new Point(20, 5));
		
		assertEquals(expectedPoints, Point.moveAllPointsRightBy(points, 10));
	}

}
