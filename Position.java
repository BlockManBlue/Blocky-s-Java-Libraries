public class Position {
    public double x = 0;
    public double y = 0;
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Position(){}

    public Position copy(){
        return new Position(x, y);
    }
}
