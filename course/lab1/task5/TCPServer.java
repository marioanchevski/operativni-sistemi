import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer{
    private ServerSocket server;


    public TCPServer() throws IOException {
        this.server = new ServerSocket(9876);

    }

    public void listen() throws IOException {
        DataInputStream dis = null;
        Socket client = this.server.accept();
        try{
            dis = new DataInputStream(client.getInputStream());
            System.out.println(dis.readDouble());
            System.out.println(dis.readLong());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readUTF());
        }finally {
            dis.close();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer();
        server.listen();
    }

}