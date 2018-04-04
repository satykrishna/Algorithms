package refactor.testing.lambda;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;

public class Point {

	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point moveRightBy(int x) {
		return new Point(this.x+x, y);
	}

	public static Comparator<Point> pointComparator = 
			comparing(Point::getX).thenComparing(Point::getY);
	
	public static List<Point> moveAllPointsBy(List<Point> points, int shiftX) {
		
		List<Point> shiftedPoints = 
				points.stream()
				.map(point -> new Point(point.getX() + shiftX, point.getY()))
				.collect(toList());
		
		return shiftedPoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof Point))
			return false;
		
		Point other = (Point)object;
		
		if(other.x != x || other.y != y)
			return false;
		
		return true;
		
	}
}
