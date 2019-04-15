import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class TCPClient{
    private Socket socket;


    public TCPClient() throws UnknownHostException, IOException {
        this.socket = new Socket(InetAddress.getByName("localhost"),9876);
    }

    public void start() throws IOException {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeDouble(1.25);
            dos.writeLong(123584124);
            dos.writeBoolean(true);
            dos.writeUTF("UTF-String");
        }finally {
            dos.flush();
            dos.close();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPClient client = new TCPClient();
        client.start();
    }

}