package Command;


import server.FileManagment.ParserXML;
import server.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;

import static server.Connections.Connection.collection;

public class CommandExecutor {
    public static final int CHUNK_SIZE = 8192;
    private static DatagramChannel datagramChannel;

    public static void setChannel(DatagramChannel datagramChannel){
        CommandExecutor.datagramChannel = datagramChannel;
    }

    public static void execute(InetSocketAddress client, byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        CommandResponse command = (CommandResponse) ois.readObject();
        Log.getLogger().log(Level.INFO, "Received command "+ command + "from "+ client);

        command.execute();
        byte[] output = command.getResponse().getOutput().getBytes();


        int chunkNum = (int) Math.ceil((double) output.length / CHUNK_SIZE);
        if (chunkNum == 0) {
            chunkNum = 1;
        }

        Log.getLogger().log(Level.INFO, "Sending" + chunkNum + "chunks to "+ client);

        for (int i = 0; i < chunkNum; i++) {
            int startOfChunk = i * CHUNK_SIZE;
            int len = Math.min(output.length - startOfChunk, CHUNK_SIZE);
            byte[] chunk = new byte[len + 1];
            if(chunkNum == i + 1){
                chunk[len] = (byte) 0;
            }
            else
            {
                chunk[len] = (byte) 1;
            }
            System.arraycopy(output, startOfChunk, chunk, 0, len); //copying from output to chunk
            datagramChannel.send(ByteBuffer.wrap(chunk), client);
        }


        new ParserXML("C:\\Users\\ronya\\Proga_6\\src\\files\\file.xml").writeData(collection);
    }
}
