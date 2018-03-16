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

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point moveRightBy(int x) {
		return new Point(this.x+x, y);
	}
	
	public static final Comparator<Point> compareByXandThenY =
			comparing(Point::getX).thenComparing(Point::getY);
			
	public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
		return points.stream().map(point->new Point(point.x+x, point.y))
				.collect(toList());
	}
}
