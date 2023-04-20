package ChunkManager;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;


public class ChunkReceiving {

    private int chunkNum = 0;
    ByteBuffer buffer = ByteBuffer.allocate(8);
    private HashMap<Integer, byte[]> chunkMap = new HashMap<>();
    private boolean isReceived;

    public ChunkReceiving(){}


    public void addChunk(byte[] chunk){
        buffer.clear();
        byte[] last8 = Arrays.copyOfRange(chunk, chunk.length - 8, chunk.length);
        buffer.put(last8);
        buffer.position(0);
        int index = buffer.getInt();

        if(chunkNum == 0){
            chunkNum = buffer.getInt(4);
        }
        chunkMap.put(index, Arrays.copyOfRange(chunk, 0, chunk.length - 8));
        if (chunkMap.size() == chunkNum){
            isReceived = true;
        }
    }

    public boolean isReceived(){
        return isReceived;
    }

    public byte[] getChunks(){
        SortedMap<Integer, byte[]> sortedMap = new TreeMap<>(chunkMap);
        int totalSize = sortedMap.values().stream().mapToInt(a -> a.length).sum();
        ByteBuffer chunks = ByteBuffer.allocate(totalSize);

        sortedMap.values().forEach(chunks::put);
        return chunks.array();
    }

}
