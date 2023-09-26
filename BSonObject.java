public class BSonObject {

    final int type;
    public static final int UNKNOWN = -1, STRING = 0, INT = 1, DOUBLE = 2;

    final String name;

    private String string;
    private int num;
    private double doub;
    
    public BSonObject(String n, String s){
        name = n;
        type = STRING;
        string = s;
    }
    public BSonObject(String n, int i){
        name = n;
        type = INT;
        num = i;
    }
    public BSonObject(String n, double d){
        name = n;
        type = DOUBLE;
        doub = d;
    }

    public String typeToString(){
        return typeToString(type);
    }
    public static String typeToString(int type){
        if(type == STRING) return "STRING";
        if(type == INT) return "INT";
        if(type == DOUBLE) return "DOUBLE";
        return "UNKNOWN";
    }

    public static int stringToType(String str){
        if(str.toLowerCase().equals("string")) return STRING;
        if(str.toLowerCase().equals("int")) return INT;
        if(str.toLowerCase().equals("double")) return DOUBLE;
        return UNKNOWN;
    }

    public String getString(){
        if(type != STRING) throw new WrongBSonTypeException(typeToString(), typeToString(STRING));
        return string;
    }

    public int getInt(){
        if(type != INT) throw new WrongBSonTypeException(typeToString(), typeToString(INT));
        return num;
    }

    public double getDouble(){
        if(type != DOUBLE) throw new WrongBSonTypeException(typeToString(), typeToString(DOUBLE));
        return doub;
    }
}
