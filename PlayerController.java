import java.awt.*;
public class PlayerController { // Generic player class that doesn't do too much on its own

    // >>> REQUIREMENTS <<<
    //  1. KeyManager
    //  2. BTools
    //  3. Position
    // >***<

    private Position position;
    KeyManager km = null;
    int up, down, left, right;
    double speed;

    public PlayerController(Position pos){ // Only holds a point as a position
        position = pos;
    }
    public PlayerController(Position pos, KeyManager km, int up, int down, int left, int right, double speed){ // can be updated and it moves itself
        position = pos;
        this.km = km;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.speed = speed;
    }

    public void update(long elapsedTime){

        if(km == null) return; // only go forward if keymanager is present
        Position moveAmount = new Position();
        if(km.getKeyDown(up)) moveAmount.y++;
        if(km.getKeyDown(down)) moveAmount.y--;
        if(km.getKeyDown(left)) moveAmount.x--;
        if(km.getKeyDown(right)) moveAmount.x++;
        move((int)(moveAmount.x * speed * elapsedTime), (int)(moveAmount.y * speed * elapsedTime));
    }

    public void move(int x, int y){
        position.x += x;
        position.y += y;
    }

    public void move(Point p){
        move(p.x, p.y);
    }

    public void goToPoint(int x, int y){
        position.x = x;
        position.y = y;
    }

    public void goToPoint(Point p){
        goToPoint(p.x, p.y);
    }

    public void goToPos(Position p){
        position = p.copy();
    }

    public void clampPos(int minx, int maxx, int miny, int maxy){
        position.x = BTools.clamp((int)position.x, minx, maxx);
        position.y = BTools.clamp((int)position.y, miny, maxy);
    }

    public double getX(){
        return position.x;
    }
    public double getY(){
        return position.y;
    }
    public Position getPos(){
        return position;
    }
}
