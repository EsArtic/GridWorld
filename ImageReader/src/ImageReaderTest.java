import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/*****************************************************************************
 * ImageReaderTest class test four methods in ImplementImageProcesser class: *
 * 1) showChanelR                                                            *
 * 2) showChanelG                                                            *
 * 3) showChanelB                                                            *
 * 4) showGray                                                               *
 *****************************************************************************/
public class ImageReaderTest {
    private static ImplementImageIO io;
    private static ImplementImageProcesser processer;
    private static Image image1;
    private static Image image2;
    
    // Instantiate the ImplementImageIO and ImplementImageProcesser object
    // Read to pictures in the memory
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    io = new ImplementImageIO();
	    processer = new ImplementImageProcesser();
	    image1 = io.myRead("1.bmp");
	    image2 = io.myRead("2.bmp");
	}

    // The test method of method showChanelR in ImplementImageProcesser class
    @Test
    public void testRed() throws Exception {
        Image unique1 = ImageIO.read(new FileInputStream("goal/1_red_goal.bmp"));
        Image unique2 = ImageIO.read(new FileInputStream("goal/2_red_goal.bmp"));

        Image test1 = processer.showChanelR(image1);
        Image test2 = processer.showChanelR(image2);

        assertEquals(unique1.getWidth(null), test1.getWidth(null));
        assertEquals(unique1.getHeight(null), test1.getHeight(null));

        assertEquals(unique2.getWidth(null), test2.getWidth(null));
        assertEquals(unique2.getHeight(null), test2.getHeight(null));
        
        int width = test1.getWidth(null);
        int height = test1.getHeight(null);
        int i, j;
        
        BufferedImage firstUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstUnique.getGraphics().drawImage(unique1, 0, 0, width, height, null);
        BufferedImage firstTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstTest.getGraphics().drawImage(test1, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(firstUnique.getRGB(i, j), firstTest.getRGB(i, j));
            }
        }
        
        width = test2.getWidth(null);
        height = test2.getHeight(null);
        
        BufferedImage secondUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondUnique.getGraphics().drawImage(unique2, 0, 0, width, height, null);
        BufferedImage secondTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondTest.getGraphics().drawImage(test2, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(secondUnique.getRGB(i, j), secondTest.getRGB(i, j));
            }
        }
    }

    // The test method of method showChanelB in ImplementImageProcesser class
    @Test
    public void testBlue() throws Exception {
        Image unique1 = ImageIO.read(new FileInputStream("goal/1_blue_goal.bmp"));
        Image unique2 = ImageIO.read(new FileInputStream("goal/2_blue_goal.bmp"));

        Image test1 = processer.showChanelB(image1);
        Image test2 = processer.showChanelB(image2);

        assertEquals(unique1.getWidth(null), test1.getWidth(null));
        assertEquals(unique1.getHeight(null), test1.getHeight(null));

        assertEquals(unique2.getWidth(null), test2.getWidth(null));
        assertEquals(unique2.getHeight(null), test2.getHeight(null));
        
        int width = test1.getWidth(null);
        int height = test1.getHeight(null);
        int i, j;
        
        BufferedImage firstUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstUnique.getGraphics().drawImage(unique1, 0, 0, width, height, null);
        BufferedImage firstTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstTest.getGraphics().drawImage(test1, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(firstUnique.getRGB(i, j), firstTest.getRGB(i, j));
            }
        }
        
        width = test2.getWidth(null);
        height = test2.getHeight(null);
        
        BufferedImage secondUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondUnique.getGraphics().drawImage(unique2, 0, 0, width, height, null);
        BufferedImage secondTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondTest.getGraphics().drawImage(test2, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(secondUnique.getRGB(i, j), secondTest.getRGB(i, j));
            }
        }
    }

    // The test method of method showChanelG in ImplementImageProcesser class
    @Test
    public void testGreen() throws Exception {
        Image unique1 = ImageIO.read(new FileInputStream("goal/1_green_goal.bmp"));
        Image unique2 = ImageIO.read(new FileInputStream("goal/2_green_goal.bmp"));

        Image test1 = processer.showChanelG(image1);
        Image test2 = processer.showChanelG(image2);

        assertEquals(unique1.getWidth(null), test1.getWidth(null));
        assertEquals(unique1.getHeight(null), test1.getHeight(null));

        assertEquals(unique2.getWidth(null), test2.getWidth(null));
        assertEquals(unique2.getHeight(null), test2.getHeight(null));
        
        int width = test1.getWidth(null);
        int height = test1.getHeight(null);
        int i, j;
        
        BufferedImage firstUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstUnique.getGraphics().drawImage(unique1, 0, 0, width, height, null);
        BufferedImage firstTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        firstTest.getGraphics().drawImage(test1, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(firstUnique.getRGB(i, j), firstTest.getRGB(i, j));
            }
        }
        
        width = test2.getWidth(null);
        height = test2.getHeight(null);
        
        BufferedImage secondUnique = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondUnique.getGraphics().drawImage(unique2, 0, 0, width, height, null);
        BufferedImage secondTest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        secondTest.getGraphics().drawImage(test2, 0, 0, width, height, null);
        
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                assertEquals(secondUnique.getRGB(i, j), secondTest.getRGB(i, j));
            }
        }
    }

    // The test method of method showGray in ImplementImageProcesser class
    @Test
    public void testGray() throws Exception {
        Image unique1 = ImageIO.read(new FileInputStream("goal/1_gray_goal.bmp"));
        Image unique2 = ImageIO.read(new FileInputStream("goal/2_gray_goal.bmp"));

        Image test1 = processer.showGray(image1);
        Image test2 = processer.showGray(image2);

        assertEquals(unique1.getWidth(null), test1.getWidth(null));
        assertEquals(unique1.getHeight(null), test1.getHeight(null));

        assertEquals(unique2.getWidth(null), test2.getWidth(null));
        assertEquals(unique2.getHeight(null), test2.getHeight(null));
    }
}
