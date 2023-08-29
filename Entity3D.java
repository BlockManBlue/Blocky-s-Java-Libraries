import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Entity3D {
    Position position;
    ImageIcon img;
    int origWidth, origHeight;
    public Entity3D(Position pos, ImageIcon i){
        position = pos;
        img = i;
        origWidth = img.getIconHeight();
        origHeight = img.getIconWidth();
    }

    public void update(long elapsedTime){
        
    }

    public final void render(Graphics g, JPanel panel, int x, int y, double scale){
        origWidth = img.getIconHeight();
        origHeight = img.getIconWidth();
        int width = (int)((panel.getHeight() - y) * scale);
        int height = (origHeight * width) / origWidth;
        img.paintIcon(panel, g, 0, panel.getHeight() + 20);
        if(width <= 0 || height <= 0) return;
        try{
            BufferedImage resized = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(img.getImage(), 0, 0, height, width, null);
            g.drawImage(resized, x - height / 2, y, height, width, null);
        }catch(Exception e){

        }
    }
}
