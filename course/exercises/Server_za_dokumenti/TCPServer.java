import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket server;
    private String destinationFile;

    public TCPServer(String dest) throws IOException {
        this.server = new ServerSocket(1234);
        destinationFile = dest;
    }

    public void listen() throws IOException {
        while(true){
         Socket client = this.server.accept();
         ServerWorker worker = new ServerWorker(client,destinationFile);
         worker.start();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPServer serv = new TCPServer("C:\\Users\\mario\\Desktop\\out");
        serv.listen();
    }

}
