import imagereader.IImageIO;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/************************************************************
 *   Implements the IImageIO interface in imagereader       *
 *   Can read bmp image in binary form as well as analyzing *
 * it in binary form                                        *
 *   Can also build and save a bmp image                    *
 ************************************************************/
public class ImplementImageIO implements IImageIO {

    // Method to tranform bytes value in to int
    public static int bytesToInt(byte[] ary, int offset) {  
        int value;
        value = (int) ((ary[offset]&0xFF)
                | ((ary[offset+1]<<8) & 0xFF00)
                | ((ary[offset+2]<<16)& 0xFF0000)
                | ((ary[offset+3]<<24) & 0xFF000000));
        return value;
    }
    
    // Method to tranform the binary RGB value in to pixels array entry
    public static int bytesToInt2(byte[] ary, int offset) {  
        int value;    
        value = (int) ((ary[offset]&0xFF)
                | ((ary[offset+1]<<8) & 0xFF00)
                | ((ary[offset+2]<<16)& 0xFF0000)
                | (0xFF000000));
        return value;
    }

    // read bmp image in binary form as well as analyzing it
    // return an image instance
	public Image myRead(String filePath) throws IOException {

        // The message of the bmp image
        byte[] buffer1 = new byte[54];

        BufferedInputStream in;
        in = new BufferedInputStream(new FileInputStream(filePath));

        in.read(buffer1);
        int size, width, height, biBitCount;
        size = bytesToInt(buffer1, 2);
        width = bytesToInt(buffer1, 18);
        height = bytesToInt(buffer1, 22);
        biBitCount = bytesToInt(buffer1, 28);

        byte[] buffer2 = new byte[size-54];
        in.read(buffer2);

        int widthBytes = (width*biBitCount+31)/32*4;
        int skip = widthBytes - (3*width);

        int temp = size-54, i, j, pixNum = temp/3, current = 0;
        int[] pixels = new int[pixNum];
        int[][] index = new int[height][width];

        // build the index array for tranformation
        for (i = height-1; i >= 0; i--) {
            for (j = 0; j < width; j++) {
                index[i][j] = current;
                current++;
            }
        }
        
        current = 0;
        for (i = 0; i < height; i ++) {
            for (j = 0; j < width; j++) {
                pixels[index[i][j]] = bytesToInt2(buffer2, current);
                current += 3;
            }
            current += skip;
        }
        in.close();

        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pixels, 0, width));
    }

    // Save the given image into the given path
    // Return the image
    public Image myWrite(Image image, String filePath) throws IOException {
        File f = new File(filePath);
        
        // Transform the Image object into the BufferedImage object
        BufferedImage tag = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
        ImageIO.write(tag, "BMP", f);

        return image;
    }
}
