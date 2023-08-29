import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class Camera {
    Position pos;
    JPanel panel;
    Dimension setDimension = null;

    public Camera(Position p, JPanel panel){
        pos = p;
        this.panel = panel;
    }

    public void render(Graphics g, ArrayList<Entity> renderList){
        int width = panel.getWidth();
        int height = panel.getHeight();
        if(setDimension != null){
            width = setDimension.width;
            height = setDimension.height;
        }
        for(Entity e: renderList){
            e.render(g, panel, (int)(((e.position.x) - pos.x) + (width / 2)), (int)(((e.position.y) - pos.y) + (height / 2)));
        }
    }
    public void render(Graphics g, ArrayList<Entity> renderList, int offX, int offY){
        int width = panel.getWidth();
        int height = panel.getHeight();
        if(setDimension != null){
            width = setDimension.width;
            height = setDimension.height;
        }
        for(Entity e: renderList){
            e.render(g, panel, (int)(((e.position.x) - (pos.x + offX)) + (width / 2)), (int)(((e.position.y) - (pos.y + offY)) + (height / 2)));
        }
    }

    public Position mouseToPos(Point mousePos){ // does not work with setDimension
        Position pos = new Position(mousePos.x, mousePos.y);
        pos.x -= panel.getWidth() / 2;
        pos.y -= panel.getHeight() / 2;
        pos.x += this.pos.x;
        pos.y += this.pos.y;
        return pos;
    }

    public void renderbg(Graphics g, ImageIcon img){
        renderbg(g, img, 0, 0);
    }

    public void renderbg(Graphics g, ImageIcon img, int offX, int offY){
        int width = panel.getWidth();
        int height = panel.getHeight();
        if(setDimension != null){
            width = setDimension.width;
            height = setDimension.height;
        }
        img.paintIcon(panel, g, (int)(-pos.x + width / 2 + offX), (int)(-pos.y + height / 2 + offY));
    }

    public Point worldToScreen(Position pos){
        int width = panel.getWidth();
        int height = panel.getHeight();
        if(setDimension != null){
            width = setDimension.width;
            height = setDimension.height;
        }
        return new Point((int)(((pos.x) - this.pos.x) + (width / 2)), (int)(((pos.y) - this.pos.y) + (height / 2)));
    }

    public ArrayList<ArrayList<Entity>> splitEntities(ArrayList<Entity> entities){
        ArrayList<Entity> drawBehind = new ArrayList<Entity>(), drawInFront = new ArrayList<Entity>();
        for(Entity e: entities){
            if(e.position.y > pos.y) drawInFront.add(e);
            else drawBehind.add(e);
        }
        ArrayList<ArrayList<Entity>> list = new ArrayList<ArrayList<Entity>>();
        list.add(drawBehind);
        list.add(drawInFront);
        return list;
    }
}