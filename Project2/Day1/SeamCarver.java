import java.awt.Color;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
    private Picture pc;
    private double border = 1000;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new java.lang.IllegalArgumentException("picture is null");
        }

        this.pc = new Picture(picture);
   }

   // current picture
    public Picture picture() {
        return this.pc;
   }

   // width of current picture
    public int width() {
        return pic.width();
   }

   // height of current picture
    public int height() {
        return pic.height();
    }

    private double energy(int i, int j) {
        int h = height() - 1;
        int w = width() - 1;
        if (i > w || j < 0 || j > h || j < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        if (i == 0 || i == w || j == 0 || j == h) {
            return border;
        }
        return energyWithoutBorder(i, j);
    }

    private double energyWithoutBorder(int x, int y) {
        Color c = pc.get(x - 1, y);
        Color c1 = pc.get(x + 1, y);
        Color c2 = pc.get(x, y - 1);
        Color c3 = pc.get(x, y + 1);
        return Math.sqrt(gradient(c, c1) + gradient(c2, c3));
    }

    private double gradient(Color x, Color y) {
        double r = x.getRed() - y.getRed();
        double g = x.getGreen() - y.getGreen();
        double b = x.getBlue() - y.getBlue();
        return r * r + g * g + b * b;
    }

    private double[][] transpose(double[][] iE) {
        int h = height();
        int w = width();
        double[][] tE = new double[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                tE[j][i] = iE[i][j];
            }
        }
        return tE;
    }

    public int[] findHorizontalSeam() {
        double[][] transEnergies = transpose(initialEnergies());
        return minVerticalPath(transEnergies);

    }

    // store initial energies
    private double[][] initialEnergies() {
        double[][] iE = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                iE[i][j] = energy(j, i);
            }
        }
        return iE;
    }

    // find the topological sort, add the minimum energies from top to bottom
    private void topologicalOrder(double[][] iE) {
        int hei = iE.length;
        int wid = iE[0].length;
        for (int row = 1; row < hei; row++) {
            for (int column = 0; column < wid; column++) {
                double tempEnergy = iE[row - 1][column];
                double minimum = 0;
                if (column == 0) {
                    minimum = tempEnergy;
                } else {
                    minimum = Math.min(tempEnergy, iE[row - 1][column - 1]);
                }
                if (column != (wid - 1)) {
                    minimum = Math.min(minimum, iE[row - 1][column + 1]);
                }
                iE[row][column] += minimum;
            }
        }
    }

    // find the minimum vertical path
    private int[] minVerticalPath(double[][] initEnergies) {
        int h = initEnergies.length;
        int w = initEnergies[0].length;
        int minPath[] = new int[h];
        topologicalOrder(initEnergies);

        // as we are finding the shortest path from the bottom, find the minimum element
        // in the last row
        minPath[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (initEnergies[h - 1][i] < initEnergies[h - 1][minPath[h - 1]]) {
                minPath[h - 1] = i;
            }
        }
        for (int row = h - 2; row >= 0; row--) {
            int column = minPath[row + 1];
            minPath[row] = column;
            if (column > 0 && initEnergies[row][column - 1] < initEnergies[row][minPath[row]]) {
                minPath[row] = column - 1;
            }
            if (column < (w - 2) && initEnergies[row][column + 1] < initEnergies[row][minPath[row]]) {
                minPath[row] = column + 1;
            }
        }
        return minPath;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] ene = initialEnergies();
        return minVerticalPath(ene);
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height() <= 1 || seam.length != width() || seam[0] < 0 || seam[0] > height() - 1) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] < 0 || seam[i + 1] < 0 || seam[i] > height() - 1 || seam[i + 1] > width() - 1) {
                throw new java.lang.IllegalArgumentException();
            }
            if (Math.abs(seam[i + 1] - seam[i]) > 1) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Picture p = new Picture(width(), height() - 1);
        for (int wid = 0; wid < width(); wid++) {
            for (int hei = 0; hei < seam[wid]; hei++) {
                p.set(wid, hei, pic.get(wid, hei));
            }
            for (int hei = seam[wid] + 1; hei < height(); hei++) {
                p.set(wid, hei - 1, pic.get(wid, hei));
            }
        }
        pic = p;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width() <= 1 || seam.length != height() || seam[0] < 0 || seam[0] > width() - 1) {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] < 0 || seam[i + 1] < 0 || seam[i] > width() - 1 || seam[i + 1] > width() - 1) {
                throw new java.lang.IllegalArgumentException();
            }
            if (Math.abs(seam[i + 1] - seam[i]) > 1) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Picture p = new Picture(width() - 1, height());
        for (int hei = 0; hei < height(); hei++) {
            for (int wid = 0; wid < seam[hei]; wid++) {
                p.set(wid, hei, pc.get(wid, hei));
            }
            for (int wid = seam[hei] + 1; wid < width(); wid++) {
                p.set(wid - 1, hei, pc.get(wid, hei));
            }
        }
        pc = p;
    }

    /*
     * private boolean validPath(int minPath[], int hei, int wid) { if (minPath ==
     * null) { return false; } if (minPath.length != hei || minPath[0] < 0 ||
     * minPath[0] > wid) { return false; } for (int i = 1; i < hei; i++) { if
     * (minPath[i] < Math.max(0,minPath[i-1] - 1) || minPath[i] >
     * Math.min(wid,minPath[i-1] + 1)) { return false; } } return true; }
     */

    // unit testing (optional)
    public static void main(String[] args) {

    }


}