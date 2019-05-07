package obj2100;
import java.awt.Color;
import java.util.Random;

public class RandomColorGenerator {
	
	public static Color getRandomColor() {
		Random rand = new Random();
		// Java 'Color' class takes 3 floats, from 0 to 1.
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);
		return randomColor;
	}

}
