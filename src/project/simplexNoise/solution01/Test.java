package project.simplexNoise.solution01;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {

	public static void main(String[] args) {
		
		int width = 1024;
		int height = 1024;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, ColorSpace.TYPE_RGB);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double noise = SimplexNoise.noise(x, y);
				int h = 10 + (int)(noise * 10);
				for (int i = 0; i < h; i++) {
					System.out.print("H");
					if (i == h - 1) System.out.println("\t\t" + noise);
				}
				
				double noiseCol = (0.5 + noise);
				if (noiseCol < 0) {
					noiseCol = 0;
				} else if (noiseCol > 1) {
					noiseCol = 1;
				}
				int col = (int)(noiseCol * 255);
				int rgb = 0xFF000000 | col << 16 | col << 8 | col;
				bufferedImage.setRGB(x, y, rgb);
			}
		}
		
		try {
			ImageIO.write(bufferedImage, "png", new File("src/project/simplexNoise/solution01/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
