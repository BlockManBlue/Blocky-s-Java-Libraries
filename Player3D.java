import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Player3D{
    Camera3D camera;
    Position position;
    KeyManager km;
    JPanel panel;
    Point mousePos = new Point(), deltaMouse = new Point();
    boolean lockMouse = true;

    public Player3D(Position pos, KeyManager km, JPanel p){
        position = pos;
        this.km = km;
        panel = p;
        camera = new Camera3D(pos, 0);

        panel.addMouseMotionListener(new MouseMoveListener());
    }

    public void update(long elapsedTime){
        if(lockMouse){
            panel.setCursor(panel.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
        }else panel.setCursor(Cursor.getDefaultCursor());
        
        Point moveDir = new Point();
        if(km.getKeyDown(KeyEvent.VK_W)) moveDir.y += 1;
        if(km.getKeyDown(KeyEvent.VK_S)) moveDir.y += -1;
        if(km.getKeyDown(KeyEvent.VK_D)) moveDir.x += -1;
        if(km.getKeyDown(KeyEvent.VK_A)) moveDir.x += 1;
        if(!moveDir.equals(new Point())){
            double moveAngle = BTools.vectorToAngle(new Position(moveDir.x, moveDir.y));
            double diff = camera.direction - BTools.vectorToAngle(new Position(0, 1));
            moveAngle += diff;
            Position movePos = BTools.angleToVector(moveAngle);
            position.x += (float)movePos.x * elapsedTime * 0.3;
            position.y += (float)movePos.y * elapsedTime * 0.3;
        }
        int deltaX = deltaMouse.x;
        camera.direction += deltaX * elapsedTime * 0.0001;
        if(lockMouse) try{
            Robot robot = new Robot();
            robot.mouseMove(panel.getWidth() / 2, panel.getHeight() / 2);
            mousePos = new Point(panel.getWidth() / 2, panel.getHeight() / 2);
            deltaMouse = new Point();
        }catch(Exception ex){}
        camera.position = position;
    }


    private class MouseMoveListener extends MouseMotionAdapter{
        public void mouseMoved(MouseEvent e){
            Point mouse = e.getPoint();
            deltaMouse = new Point(mouse.x - mousePos.x, mouse.y - mousePos.y);
            mousePos = mouse;
        }

        public void mouseDragged(MouseEvent e){
            mouseMoved(e);
        }
    }
}