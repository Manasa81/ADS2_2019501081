import java.awt.Color;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
    private Picture picture;
    double[][] energy;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new java.lang.IllegalArgumentException("picture is null");
        }
        energy = new double[picture.width()][picture.height()];
        energy(picture);
        updateEnergies(energy);
        this.picture = picture;
   }

   // current picture
    public Picture picture() {
        return this.picture;
   }

   // width of current picture
    public int width() {
        return picture.width();
   }

   // height of current picture
    public int height() {
        return picture.height();
   }

   // energy of pixel at column x and row y
    public void energy(Picture picture) {
        for (int x = 0; x < picture.width(); x++) {
            for (int y = 0; y < picture.height(); y++) {
                if (x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1) {
                    energy[x][y] = 1000;
                } else {
                    Color c = picture.get(x - 1, y);
                    int r1 = c.getRed();
                    int g1 = c.getGreen();
                    int b1 = c.getBlue();
                    Color c1 = picture.get(x + 1, y);
                    int r2 = c1.getRed();
                    int g2 = c1.getGreen();
                    int b2 = c1.getBlue();
                    Color c2 = picture.get(x, y - 1);
                    int r3 = c2.getRed();
                    int g3 = c2.getGreen();
                    int b3 = c2.getBlue();
                    Color c3 = picture.get(x, y + 1);
                    int r4 = c3.getRed();
                    int g4 = c3.getGreen();
                    int b4 = c3.getBlue();

                    int xG = (r2 - r1) * (r2 - r1) + (g2 - g1) * (g2 - g1) + (b2 - b1) * (b2 - b1);
                    int yG = (r3 - r4) * (r3 - r4) + (g3 - g4) * (g3 - g4) + (b3 - b4) * (b3 - b4);
                    double ene = Math.sqrt(xG + yG);
                    energy[x][y] = ene;

                }
            }
        }

    }

    public double energy(int x, int y) {
        return energy[x][y];
    }

    public double[][] updateEnergies(double[][] e) {
        double[][] updatedArr = new double[e.length][e[0].length];
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e[0].length; j++) {
                if (i == 0) {
                    updatedArr[0][j] = e[0][j];
                } else {
                    if (j == 0) {
                        updatedArr[i][j] = Math.min(e[i][j] + updatedArr[i - 1][j], e[i][j] + updatedArr[i - 1][j + 1]);
                    } else if (j == e[0].length - 1) {
                        updatedArr[i][j] = Math.min(e[i][j] + updatedArr[i - 1][j], e[i][j] + updatedArr[i - 1][j - 1]);
                    } else {
                        updatedArr[i][j] = Math.min(e[i][j] + updatedArr[i - 1][j],
                                Math.min(e[i][j] + updatedArr[i - 1][j + 1], e[i][j] + updatedArr[i - 1][j - 1]));
                    }
                }
            }

        }
        for (int row = 0; row < e.length; row++) {
            for (int col = 0; col < e[0].length; col++)
                StdOut.printf("%9.0f ", updatedArr[row][col]);
            StdOut.println();
        }
        return updatedArr;
    }
    // private int[]

   // sequence of indices for horizontal seam
//    public int[] findHorizontalSeam(){}

//    // sequence of indices for vertical seam
//    public int[] findVerticalSeam(){}

//    // remove horizontal seam from current picture
//    public void removeHorizontalSeam(int[] seam){}

//    // remove vertical seam from current picture
//    public void removeVerticalSeam(int[] seam){}

   //  unit testing (optional)
    public static void main(String[] args) {

   }

}