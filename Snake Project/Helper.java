package snake;
import java.util.ArrayList;
import java.awt.Point;

public class Helper {

	public Helper()
	{
		
	}
	public boolean noTailAt(int x, int y, ArrayList<Point> snakeParts)
	{
        for (Point point : snakeParts) {
                if (point.equals(new Point(x, y))) {
                        return false;
                }
        }
        return true;
	}
}
