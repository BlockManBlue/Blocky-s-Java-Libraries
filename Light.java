public class Light extends Entity{
    int radius = 10;
    public Light(Position pos){
        super(pos);
    }
    public Light(Position pos, int r){
        super(pos);
        radius = r;
    }
}
