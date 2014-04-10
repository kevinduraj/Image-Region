package orientation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*-------------------------------------------------------------------------------------------------
            Orientation describes the direction of the major axis, that is the axis 
            that runs through the centroid and along the widest part of the region.
-------------------------------------------------------------------------------------------------*/

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
                
       int[][] image = ImageRead("src/images/assignment05.png");
       Moments moments = new Moments();
       System.out.println("u00 = " + moments.centralMoment(image, 0, 0));
       
       System.out.println("u01 = 0 = " + moments.centralMoment(image, 0, 1));       
       System.out.println("u10 = 0 = " + moments.centralMoment(image, 1, 0));
       
       System.out.println("u11 = " + moments.centralMoment(image, 1, 1));
       System.out.println("u20 = " + moments.centralMoment(image, 2, 0));
       System.out.println("u02 = " + moments.centralMoment(image, 0, 2));
       
       double u11 = moments.centralMoment(image, 1, 1);
       double u20 = moments.centralMoment(image, 2, 0);
       double u02 = moments.centralMoment(image, 0, 2);
       
       double theta = 0.5 * Math.atan2(2.0 * u11, u20 - u02);
       
       System.out.format("\nOrientation %.3f ... PI = %.3f\n", theta, theta*180.0/Math.PI);

       
    }
    /*--------------------------------------------------------------------------------------------*/

    private static int[][] ImageRead(String filename) throws IOException {

        File infile = new File(filename);
        BufferedImage bi = ImageIO.read(infile);

        int red[][] = new int[bi.getHeight()][bi.getWidth()];
        int grn[][] = new int[bi.getHeight()][bi.getWidth()];
        int blu[][] = new int[bi.getHeight()][bi.getWidth()];

        for (int i = 0; i < red.length; ++i) {
            for (int j = 0; j < red[i].length; ++j) {
                red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                blu[i][j] = bi.getRGB(j, i) & 0xFF;
            }
        }
        return grn;
    }
    /*--------------------------------------------------------------------------------------------*/
    
}
/*------------------------------------------------------------------------------------------------*/
