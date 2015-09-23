import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*************************************************************
 *   Implements the IImageProcessor interface in imagereader *
 *   Can tranforms the given bmp image into red, green and   *
 * blue color chanel                                         *
 *   Can also build the gray level image of the given image  *
 *************************************************************/
public class ImplementImageProcesser implements IImageProcessor {

    // Reset the RGB value for every pixels of the image to show the red chanel
    public Image showChanelR(Image sourceImage) {
        int width = sourceImage.getWidth(null);
        int height = sourceImage.getHeight(null);
        int i, j;

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(sourceImage, 0, 0, width, height, null);

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                int rgb = tag.getRGB(i, j);
                int red = (rgb >> 16) & 0xFF;
                tag.setRGB(i, j, (red << 16));
            }
        }
        return tag;
    }

    // Reset the RGB value for every pixels of the image to show the green chanel
    public Image showChanelG(Image sourceImage) {	
        int width = sourceImage.getWidth(null);
        int height = sourceImage.getHeight(null);
        int i, j;
        
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(sourceImage, 0, 0, width, height, null);

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                int rgb = tag.getRGB(i, j);
                int green = (rgb >> 8) & 0xFF;
                tag.setRGB(i, j, (green<<8));
            }
        }
        return tag;
    }

    // Reset the RGB value for every pixels of the image to show the blue chanel
    public Image showChanelB(Image sourceImage) {
        int width = sourceImage.getWidth(null);
        int height = sourceImage.getHeight(null);
        int i, j;

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(sourceImage, 0, 0, width, height, null);

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                int rgb = tag.getRGB(i, j);
                int blue = (rgb >> 0) & 0xFF;
                tag.setRGB(i, j, blue);
            }
        }
        return tag;
    }

    // Reset the RGB value for every pixels of the image to show the gray level image
    public Image showGray(Image sourceImage) {
        int width = sourceImage.getWidth(null);
        int height = sourceImage.getHeight(null);
        int i, j;

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(sourceImage, 0, 0, width, height, null);

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                int rgb = tag.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb >> 0) & 0xFF;
                int gray = (int)((b * 29 + g * 150 + r * 77 + 128) >> 8);
                gray = (int)((0xFF000000) | (gray << 16) | (gray << 8) | (gray));
                tag.setRGB(i, j, gray);
            }
        }
        return tag;
    }
}
