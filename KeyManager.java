import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class KeyManager { // Reads, stores, and returns key input information

    JPanel panel;
    boolean[] keys = new boolean[255];
    boolean enabled = true;
    boolean lmb, rmb, mmb;
    Point mousePos = new Point(), mouseDelta = new Point();
    
    public KeyManager(JPanel panel){
        this.panel = panel;
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addMouseListener(new MouseListener());
        panel.addKeyListener(new KeyListener());
        panel.addMouseMotionListener(new MouseMovementListener());
    }

    public boolean getKeyDown(int key){
        if(key < 0) return getMouseDown(key * -1);
        return keys[key];
    }

    public boolean getMouseDown(int mouseButton){
        if(mouseButton == 1) return lmb;
        if(mouseButton == 2) return mmb;
        if(mouseButton == 3) return rmb;
        return false;
    }

    public void reset(){ // resets everything to false
        keys = new boolean[255];
    }

    public void enable(){
        enabled = true;
    }

    public void disable(){
        enabled = false;
    }

    private void checkPressedKey(int key){
        if(!getKeyDown(key)) onKeyPressed(key);
    }
    public void onKeyPressed(int key){}
    private void checkPressedMouse(int mb, Point mousePos){
        if(!getMouseDown(mb)) onMousePressed(mb, mousePos);
    }
    public void onMousePressed(int mb, Point mousePos){}

    private class MouseListener extends MouseAdapter { // used to re-focus the panel
        public void mousePressed(MouseEvent e){
            panel.requestFocus();
            checkPressedMouse(e.getButton(), e.getPoint());
            if(e.getButton() == 1) lmb = true;
            if(e.getButton() == 2) mmb = true;
            if(e.getButton() == 3) rmb = true;
        }

        public void mouseReleased(MouseEvent e){
            if(e.getButton() == 1) lmb = false;
            if(e.getButton() == 2) mmb = false;
            if(e.getButton() == 3) rmb = false;
        }
    }

    private class MouseMovementListener extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e){
            mouseDelta = new Point(e.getX() - mousePos.x, e.getY() - mousePos.y);
            mousePos = e.getPoint();
        }

        public void mouseDragged(MouseEvent e){
            mouseDelta = new Point(e.getX() - mousePos.x, e.getY() - mousePos.y);
            mousePos = e.getPoint();
        }
    }
    
    private class KeyListener extends KeyAdapter { // used to read and store key input information
        public void keyPressed(KeyEvent e){
            if(!enabled) return;
            checkPressedKey(e.getKeyCode());
            keys[e.getKeyCode()] = true;
        }

        public void keyReleased(KeyEvent e){
            if(!enabled) return;
            keys[e.getKeyCode()] = false;
        }
    }
}
