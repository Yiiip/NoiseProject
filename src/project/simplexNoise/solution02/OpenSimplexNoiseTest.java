package project.simplexNoise.solution02;

/**
 * OpenSimplex Noise example.
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;

public class OpenSimplexNoiseTest {
	
	private static final int WIDTH = 512;
	private static final int HEIGHT = 512;
	private static final double FEATURE_SIZE = 64;

	public static void main(String[] args) throws IOException {

		Random r = new Random();
		
		OpenSimplexNoise noise = new OpenSimplexNoise();
		
		int seed1 = r.nextInt(32767); System.out.println("seed1 = " + seed1);
		int seed2 = r.nextInt(32767); System.out.println("seed1 = " + seed2); 
		int seed3 = r.nextInt(32767); System.out.println("seed1 = " + seed3);
		OpenSimplexNoise n1 = new OpenSimplexNoise(seed1);
		OpenSimplexNoise n2 = new OpenSimplexNoise(seed2);
		OpenSimplexNoise n3 = new OpenSimplexNoise(seed3);
		
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				
				//double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
				
				double value = (n1.eval(x / 48.0, y / 48.0, 0.0) + n2.eval(x / 24.0, y / 24.0, 0.0) * .5 + n3.eval(x / 12.0, y / 12.0, 0.0) * .25) / (1 + .5 + .25);
				
				int rgb = 0x010101 * (int) ((value + 1) * 127.5);
				image.setRGB(x, y, rgb);
			}
		}
		ImageIO.write(image, "png", new File("src/project/simplexNoise/solution02/OpenSimplexNoise.png"));
	}
}