import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Camera3D {
    double direction; // in radians
    Position position;
    int farClip = 10000, nearClip = 10;
    int fov = 60;

    public Camera3D(Position pos, double dir){
        position = pos;
        direction = dir;
    }

    public void render(Graphics g, JPanel panel, ArrayList<Entity3D> entities){
        while(direction < -Math.PI) direction += 2 * Math.PI;
        while(direction > Math.PI) direction -= 2 * Math.PI;
        Position unitDirection = BTools.angleToVector(direction);        
        for(Entity3D entity: entities){
            Position unitAngle = BTools.getDirectionTrig(position, entity.position);

            double dot = unitDirection.x * unitAngle.x + unitDirection.y * unitAngle.y;
            double relAngle = Math.acos(dot);

            double angle = BTools.getAngle(position, entity.position);
            
            if(relAngle < Math.toRadians(fov / 2) && BTools.getDistance(position, entity.position) < farClip && BTools.getDistance(position, entity.position) > nearClip){
                angle -= direction;
                angle += Math.toRadians(fov / 2);
                int pixel = (int)((angle / Math.toRadians(fov)) * panel.getWidth());
                double dist = BTools.getDistance(position, entity.position);
                double scale = -Math.sqrt(0.001 * dist) + 1.5;

                entity.render(g, panel, pixel, panel.getHeight() / 4 - 20, scale);
            }
        }
    }
}
