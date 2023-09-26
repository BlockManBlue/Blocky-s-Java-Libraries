import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class AnimImage {

    ImageIcon spriteSheet;
    int frameCount;
    long delay;
    int width, height;

    int currentFrame;
    long timer = 0;
    
    public AnimImage(String filePath, int fCount, int fps, int frameWidth, int frameHeight){
        spriteSheet = new ImageIcon(filePath);
        frameCount = fCount;
        delay = 1000 / fps;
        width = frameWidth;
        height = frameHeight;
    }
    public AnimImage(ImageIcon sheet, int fCount, int fps, int frameWidth, int frameHeight){
        spriteSheet = sheet;
        frameCount = fCount;
        delay = 1000 / fps;
        width = frameWidth;
        height = frameHeight;
    }
    
    public static AnimImage parseBSon(String sheet, String bsonFile){
        ArrayList<BSonObject> objects = BSonParser.readFile(bsonFile);
        BSonObject obj = BSonParser.getObject("frameCount", objects);
        int fCount = obj.getInt();
        obj = BSonParser.getObject("fps", objects);
        int fps = obj.getInt();
        obj = BSonParser.getObject("frameWidth", objects);
        int frameWidth = obj.getInt();
        obj = BSonParser.getObject("frameHeight", objects);
        int frameHeight = obj.getInt();
        return new AnimImage(sheet, fCount, fps, frameWidth, frameHeight);
    }

    public void paint(JPanel panel, Graphics g, int x, int y){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = img.getGraphics();
        int x2 = currentFrame % (spriteSheet.getIconWidth() / width);
        int y2 = currentFrame / (spriteSheet.getIconHeight() / height);
        g2.drawImage(spriteSheet.getImage(), -x2 * width, -y2 * height, panel);
        g.drawImage(img, x, y, panel);
    }

    public void update(long elapsedTime){
        timer += elapsedTime;
        if(timer >= delay){
            timer = 0;
            currentFrame++;
            if(currentFrame >= frameCount) currentFrame = 0;
        }
    }

    public void resize(int width, int height){
        double widthChange = width / this.width;
        double heightChange = height / this.height;
        this.width = width;
        this.height = height;
        spriteSheet.setImage(spriteSheet.getImage().getScaledInstance((int)(spriteSheet.getIconWidth() * widthChange), (int)(spriteSheet.getIconHeight() * heightChange), Image.SCALE_DEFAULT));
    }

    public AnimImage copy(boolean sameFrame){
        AnimImage img = new AnimImage(spriteSheet, frameCount, 1000 / (int)delay, width, height);
        if(sameFrame) img.currentFrame = currentFrame;
        return img;
    }
}
