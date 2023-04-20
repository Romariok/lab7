package server.Connections;

import ChunkManager.ChunkCreating;
import ChunkManager.ChunkReceiving;
import DataStructure.Response;
import server.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;

import static Command.CommandExecutor.execute;

public class ServerHandler implements Runnable{
    private ByteBuffer buffer;
    private DatagramChannel keyChannel;
    private InetSocketAddress socketAddress;
    public ServerHandler(ByteBuffer buffer, DatagramChannel channel, InetSocketAddress address){
        this.buffer = buffer;
        this.keyChannel = channel;
        this.socketAddress = address;
    }

    @Override
    public void run(){
        Log.getLogger().log(Level.INFO, "Cached Pool got new submit");
        ChunkReceiving receiving;
        synchronized (SelectorManager.getChunkList()){
            receiving = SelectorManager.getChunkList().get(socketAddress);
            if (receiving == null) {
                receiving = new ChunkReceiving();
                SelectorManager.getChunkList().put(socketAddress, receiving);
            }
            receiving.addChunk(Arrays.copyOfRange(buffer.array(), 0, buffer.position()));
        }
        if (receiving.isReceived()) {
            try{
                execute(socketAddress, receiving.getChunks());
            }
            catch (Exception ex){
                Log.getLogger().warning("ServerHandler: "+ex.getMessage());
                try{
                    ChunkCreating creating = new ChunkCreating(new Response(ex.toString()).getOutput().getBytes());
                    Iterator<byte[]> keys= creating.getIterator();
                    while(keys.hasNext()){
                        keyChannel.send(ByteBuffer.wrap(keys.next()), socketAddress);
                    }
                }catch (IOException ignore){
                }
            }

        }


    }
}
