package Command;

import server.Log;

import java.io.*;

public class Serializer {

    public static <T extends Serializable> byte[] serialize(T object){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            System.err.println("Unable to create ObjectOutputStream: " + e);
            return new byte[0];
        }
        try {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.err.println("Unable to serialize object: " + object.getClass().getSimpleName() + ": " + e);
            return new byte[0];
        }
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println("Unable to close ObjectOutputStream");
        }
        return byteArrayOutputStream.toByteArray();
    }
    public static Object deserialize(byte[] data) {
        try{
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
    //        while(ois.readObject() != null)
            return ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex){
            Log.getLogger().warning(ex.getMessage());
            System.out.println(ex.getClass().getSimpleName());
            return null;
        }

    }
}
