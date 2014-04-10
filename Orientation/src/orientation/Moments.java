package orientation;

/*------------------------------------------------------------------------------------------------
 Computing Central Moments for multiple Regions within a image
 Written by: Kevin Duraj
--------------------------------------------------------------------------------------------------*/
public class Moments {

    private int REGION = 0;
    
    /*--------------------------------------------------------------------------------------------*/
    public double moment(int[][] image, int p, int q) {
        
        double Mpq = 0.0;
        int height = image.length;
        int width  = image[0].length;
        
        for(int j=0; j < height; j++) {
            for(int i=0; i<width; i++) {
                if(image[j][i] == REGION) {
                    Mpq += Math.pow(i,p) * Math.pow(j,q);
                }
            }
        }
        
        return Mpq;
    }
    /*--------------------------------------------------------------------------------------------*/
    public double centralMoment(int region, int[][]image, int p, int q) {
        
        this.REGION = region;        
        double cMpq = 0.0;        
        int height = image.length;
        int width  = image[0].length;
        
        double m00 = moment(image, 0, 0); //m00 = region area
        double xCtr = moment(image, 1, 0) / m00;
        double yCtr = moment(image, 0, 1) / m00;
        
        for(int j=0; j<height; j++) {
            for(int i=0; i<width; i++) {
                if(image[j][i] == REGION) {
                    cMpq += Math.pow(i - xCtr, p) * Math.pow(j - yCtr, q);                    
                }
            }
        }
        return cMpq;
    }
    /*--------------------------------------------------------------------------------------------*/
    
}
/*------------------------------------------------------------------------------------------------*/