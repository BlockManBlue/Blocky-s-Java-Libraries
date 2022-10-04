import java.awt.*;
import javax.swing.*;
public class Entity { // meant to be extended off of
    Position position;

    public Entity(Position position){
        this.position = position;
    }

    public void update(long elapsedTime){} // run every server tick

    public void render(Graphics g){} // render basic shape at position
    public void render(Graphics g, JPanel panel){} // render image at position
    public void render(Graphics g, int x, int y){} // render basic shape at (x, y)
    public void render(Graphics g, JPanel panel, int x, int y){} // render image at (x, y)
}
