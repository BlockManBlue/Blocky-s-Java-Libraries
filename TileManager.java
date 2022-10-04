import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class TileManager { // class to manage a tiling system

    private ImageIcon[][] tileImages; // [tileset][tile]
    String[][] tiles; // [x][y] "<tileset> <tile>"
    private int width, height;
    private int tileSize;

    private boolean doneLoading = false;

    public TileManager(int w, int h, String[] fileNames, String mapFile, int tileSize, String[] fileExtensions){
        width = w;
        height = h;
        tiles = new String[w][h];
        tileImages = new ImageIcon[fileNames.length][16];
        this.tileSize = tileSize;

        // get tile images
        for(int i = 0; i < fileNames.length; i++){
            tileImages[i][0] = new ImageIcon(fileNames[i] + "/0" + fileExtensions[i]);
            tileImages[i][1] = new ImageIcon(fileNames[i] + "/1" + fileExtensions[i]);
            tileImages[i][2] = new ImageIcon(fileNames[i] + "/2" + fileExtensions[i]);
            tileImages[i][3] = new ImageIcon(fileNames[i] + "/3" + fileExtensions[i]);
            tileImages[i][4] = new ImageIcon(fileNames[i] + "/4" + fileExtensions[i]);
            tileImages[i][5] = new ImageIcon(fileNames[i] + "/5" + fileExtensions[i]);
            tileImages[i][6] = new ImageIcon(fileNames[i] + "/6" + fileExtensions[i]);
            tileImages[i][7] = new ImageIcon(fileNames[i] + "/7" + fileExtensions[i]);
            tileImages[i][8] = new ImageIcon(fileNames[i] + "/8" + fileExtensions[i]);
            tileImages[i][9] = new ImageIcon(fileNames[i] + "/9" + fileExtensions[i]);
            tileImages[i][10] = new ImageIcon(fileNames[i] + "/10" + fileExtensions[i]);
            tileImages[i][11] = new ImageIcon(fileNames[i] + "/11" + fileExtensions[i]);
            tileImages[i][12] = new ImageIcon(fileNames[i] + "/12" + fileExtensions[i]);
            tileImages[i][13] = new ImageIcon(fileNames[i] + "/13" + fileExtensions[i]);
            tileImages[i][14] = new ImageIcon(fileNames[i] + "/14" + fileExtensions[i]);
            tileImages[i][15] = new ImageIcon(fileNames[i] + "/15" + fileExtensions[i]);

            // resize
            for(int i2 = 0; i2 < 16; i2++) tileImages[i][i2].setImage(tileImages[i][i2].getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_DEFAULT));
        }

        // read map file and make temporary map
        int[][] tempMap = new int[w][h];
        try{
            Scanner reader = new Scanner(new File(mapFile));
            for(int y = 0; y < h; y++){
                for(int x = 0; x < w; x++){
                    tempMap[x][y] = reader.nextInt();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
       

        // use temporary map to make real map
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                // check if there's a tile
                if(tempMap[x][y] == 0) tiles[x][y] = "0 0";

                // check top left
                boolean isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 0";
                if(isGood) continue;

                // check top center
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 1";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 9";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 15";
                if(isGood) continue;

                // check top right
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 2";
                if(isGood) continue;

                // check center left
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 10";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 3";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false;
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false;
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false;
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false;
                if(isGood) tiles[x][y] = tempMap[x][y] + " 14";
                if(isGood) continue;

                

                // check center
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 11";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 4";
                if(isGood) continue;

                // check center right
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] != 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 12";
                if(isGood) continue;

                isGood = true;
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] == 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 5";
                if(isGood) continue;

                

                // check bottom left
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 6";
                if(isGood) continue;

                // check bottom center
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] != 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 13";
                if(isGood) continue;

                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] == 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 7";
                if(isGood) continue;

                // check bottom right
                isGood = true;
                if(x > 0 && tempMap[x - 1][y] == 0) isGood = false; // left
                if(x < width - 1 && tempMap[x + 1][y] != 0) isGood = false; // right
                if(y > 0 && tempMap[x][y - 1] == 0) isGood = false; // top
                if(y < height - 1 && tempMap[x][y + 1] != 0) isGood = false; // bottom
                if(isGood) tiles[x][y] = tempMap[x][y] + " 8";
                if(isGood) continue;

            }
        }

        doneLoading = true;
    }

    public void render(Graphics g, Point origin, JPanel parent){
        if(!doneLoading) return;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                try{
                    if(getTileImage(x, y) != null)
                        getTileImage(x, y).paintIcon(parent, g, origin.x + (x * tileSize), origin.y + (y * tileSize));
                }catch(Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
                
            }
        }
    }

    public int getTileID(int x, int y){
        Scanner reader = new Scanner(tiles[x][y]);
        int id = reader.nextInt();
        reader.close();
        return id;
    }

    public ImageIcon getTileImage(int x, int y){
        Scanner reader = new Scanner(tiles[x][y]);
        int id = reader.nextInt();
        int orientation = reader.nextInt();
        reader.close();
        if(id == 0) return null;
        return tileImages[id - 1][orientation];
    }

    public int getOrientation(int x, int y){
        Scanner reader = new Scanner(tiles[x][y]);
        reader.nextInt();
        int orientation = reader.nextInt();
        reader.close();
        return orientation;
    }

    public Point getTilePos(Point p){
        int x = p.x / tileSize;
        int y = p.y / tileSize;
        return new Point(x, y);
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
    
}
