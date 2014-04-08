package orientation;

/*------------------------------------------------------------------------------------------------*/
public class Moments {

    private final int BACKGROUND = 0;
    
    /*--------------------------------------------------------------------------------------------*/
    public double moment(int[][] image, int p, int q) {
        
        double Mpq = 0.0;
        int height = image.length;
        int width  = image[0].length;
        
        for(int j=0; j < height; j++) {
            for(int i=0; i<width; i++) {
                if(image[i][j] != BACKGROUND) {
                    Mpq += Math.pow(i,p) * Math.pow(j,q);
                }
            }
        }
        
        return Mpq;
    }
    /*--------------------------------------------------------------------------------------------*/
    public double centralMoment(int[][]image, int p, int q) {
        
        double cMpq = 0.0;        
        int height = image.length;
        int width  = image[0].length;
        
        double m00 = moment(image, 0, 0); // = region area
        double xCtr = moment(image, 1, 0) / m00;
        double yCtr = moment(image, 0, 1) / m00;
        
        for(int j=0; j<height; j++) {
            for(int i=0; i<width; i++) {
                if(image[i][j] != BACKGROUND) {
                    cMpq += Math.pow(i - xCtr, p) * Math.pow(i - yCtr, q);                    
                }
            }
        }
        return cMpq;
    }
    /*--------------------------------------------------------------------------------------------*/
    public double normalCentralMoment(int[][]image, int p, int q) {
        
        double m00 = moment(image, 0, 0);
        double norm = Math.pow(m00, (double)(p+q+2)/2);
        return centralMoment(image, p, q) / norm;
    }
    /*--------------------------------------------------------------------------------------------*/
    
}
/*------------------------------------------------------------------------------------------------*/