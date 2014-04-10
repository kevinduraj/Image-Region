package convexhull;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Main {

    private static int height;
    private static int width;
    //private static int pixelObject = 1;
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {

        ArrayList<Point> points = new ArrayList<Point>();

        int[][] image = ImageRead("src/images/assignment05.png");
        height = image.length;
        width = image[0].length;

        
        /* for each 4 different regions in the object */
        for (int pixelObject = 1; pixelObject < 5; pixelObject++) {
            
            PrintWriter writer = new PrintWriter("src/files/convex-hull-"+pixelObject+".txt", "UTF-8");
            
            /*---- Left to Right ----*/
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (image[y][x] == pixelObject) {
                        System.out.println("y=" + y + "\t" + "x=" + x);
                        writer.println("y=" + y + "\t" + "x=" + x);
                        Point p = new Point();
                        p.y = y;
                        p.x = x;
                        points.add(p);
                    }
                }
            }
            ConvexHullAlgorithm convex = new FastConvexHull();
            ArrayList<Point> result = convex.execute(points);

            for (Point p : result) {
                System.out.println("y=" + p.y + "\tx=" + p.x);
                writer.println("y=" + p.y + "\tx=" + p.x);
                image[p.y][p.x] = 255;
            }
            writer.close(); 
        }

        points.clear();

        ImageWrite("src/images/convexhull.png", image);

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

    private static void ImageWrite(String filename, int img[][]) throws IOException {

        BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < bi.getHeight(); ++i) {
            for (int j = 0; j < bi.getWidth(); ++j) {
                int val = img[i][j];
                int pixel = (val << 16) | (val << 8) | (val);
                bi.setRGB(j, i, pixel);
            }
        }

        File outputfile = new File(filename);
        ImageIO.write(bi, "png", outputfile);
    }
    /*--------------------------------------------------------------------------------------------*/

}
