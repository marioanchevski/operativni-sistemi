package lab1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HW01_5 {

	public static void main(String[] args) throws IOException {
		TCPServer server = new TCPServer();
		server.listen();
		TCPClient client  = new TCPClient();
		client.start();

	}

}
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
	
}


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
	
}
