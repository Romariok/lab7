package client;


import java.io.*;
import java.net.*;

public class Connection {
    private int chunkSize;
    private DatagramSocket datagramSocket;

    private final int port;
    private InetAddress address;

    public Connection(InetAddress addres, int port) throws IOException {
        datagramSocket = new DatagramSocket();
        this.address = addres;
        this.port = port;
        datagramSocket.setReceiveBufferSize(8192*8192);
        datagramSocket.setSendBufferSize(8192*8192);
        this.chunkSize = 1024;
        datagramSocket.setSoTimeout(10000);
        System.out.println("--------- Client started! Server on " + address + ":" + port + " ---------");
    }
    public void send(String str) throws Exception {
        this.send(str.getBytes());
    }

    public void send(byte[] bytes) throws Exception {
        int chunkNum = (int) Math.ceil((double) bytes.length / chunkSize);
        for (int i = 0; i < chunkNum; i++) {
            int startOfChunk = i * chunkSize;
            int len = Math.min(bytes.length - startOfChunk, chunkSize);
            byte[] chunk = new byte[len + 1];
            if (chunkNum == 1 || i + 1 == chunkNum ) {
                chunk[len] = (byte) 0;
            } else {
                chunk[len] = (byte) 1;
            }
            System.arraycopy(bytes, startOfChunk, chunk, 0, len);
            DatagramPacket datagramPacket = new DatagramPacket(chunk, len + 1, this.address, port);
            datagramSocket.send(datagramPacket);
        }


        System.out.print("Client send to server "+ chunkNum+" chunks!\n");
    }

    public String recieve() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte [chunkSize+1];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(packet);
        boolean hasNext = (packet.getData()[packet.getLength() - 1] & 0xFF) == 1;
        baos.write(packet.getData(), 0, packet.getLength() - 1);
        while (hasNext){
            datagramSocket.receive(packet);
            hasNext = (packet.getData()[packet.getLength() - 1] & 0xFF) == 1;
            baos.write(packet.getData(), 0, packet.getLength() - 1);
        }
        return baos.toString();
    }


}