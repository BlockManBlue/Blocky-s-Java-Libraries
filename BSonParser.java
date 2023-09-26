import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class BSonParser { // BSon is basically my own version of JSon
    
    public static ArrayList<BSonObject> readFile(String filePath){
        String fileEnd = BTools.getLastSubString(filePath, 5);
        if(!fileEnd.equals(".bson")){
            throw new BSonBadFileException(1);
        }
        ArrayList<BSonObject> objects = new ArrayList<BSonObject>();
        try{
            File file = new File(filePath);
            if(!file.exists()) throw new BSonBadFileException();
            Scanner reader = new Scanner(file);
            while(reader.hasNext()){
                String typeStr = reader.next();
                int type = BSonObject.stringToType(typeStr);
                if(type == BSonObject.UNKNOWN) throw new BSonBadFileException(typeStr);

                String name = reader.next();

                BSonObject obj = null;
                if(type == BSonObject.STRING){
                    String str = reader.nextLine();
                    obj = new BSonObject(name, str);
                }
                if(type == BSonObject.INT){
                    int i = reader.nextInt();
                    reader.nextLine();
                    obj = new BSonObject(name, i);
                }
                if(type == BSonObject.DOUBLE){
                    double d = reader.nextDouble();
                    reader.nextLine();
                    obj = new BSonObject(name, d);
                }
                if(obj == null) throw new BSonBadFileException();
                objects.add(obj);
            }
            reader.close();
        }catch(Exception e){
            throw new BSonBadFileException();
        }
        return objects;
    }

    public static BSonObject getObject(String name, ArrayList<BSonObject> objects){
        for(BSonObject obj: objects){
            if(obj.name.equals(name)) return obj;
        }
        return null;
    }
}
