package ChunkManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChunkCreating {
    public static final int CHUNK_SIZE = 1024;
    private LinkedList<byte[]> chunkList = new LinkedList<>();
    private final byte[] bytes;
    private int counting = 0;
    private boolean isFirstChunk = false;

    public ChunkCreating(byte[] data) {
        this.bytes = data;
        createChunks();
    }

    byte[] toBytes(int i)
    {
        byte[] result = new byte[4];

        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) ((i) & 0xFF);

        return result;
    }
    public void createChunks() {
        int chunkNum = (int) Math.ceil((double) bytes.length / CHUNK_SIZE);
        if (chunkNum == 0) {
            chunkNum = 1;
        }
        counting = chunkNum;
        byte[] totalNumChunks = toBytes(chunkNum);
        for (int i = 0; i < chunkNum; i++) {
            byte[] currentIndexOfChunk = toBytes(i+1);
            int startOfChunk = i * CHUNK_SIZE;
            int len = Math.min(bytes.length - startOfChunk, CHUNK_SIZE);
            byte[] chunk = new byte[len + 8];
            chunk[len] = currentIndexOfChunk[0];
            chunk[len+1] = currentIndexOfChunk[1];
            chunk[len+2] = currentIndexOfChunk[2];
            chunk[len+3] = currentIndexOfChunk[3];
            chunk[len+4] = totalNumChunks[0];
            chunk[len+5] = totalNumChunks[1];
            chunk[len+6] = totalNumChunks[2];
            chunk[len+7] = totalNumChunks[3];
            System.arraycopy(bytes, startOfChunk, chunk, 0, len);
            chunkList.add(chunk);
        }


    }

    public Iterator<byte[]> getIterator() {
        return chunkList.iterator();
    }

    public int getCounting() {
        return this.counting;
    }
}
