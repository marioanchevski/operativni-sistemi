import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends Thread{
    private Socket socket;
    private String in;
    public TCPClient(String in) throws IOException {
        this.socket =  new Socket(InetAddress.getByName("localhost"),1234);
        this.in=in;
    }

    public void listFiles() throws IOException {
        File file = new File(in);
        File[] files = file.listFiles();
        for(File f:files){
            if(f.isFile()){
                send(f);
            }
        }
        socket.close();
    }


    public void send(File file) throws IOException {
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            pw = new PrintWriter(this.socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            pw.println("###" + file.getName() + "###");
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line);
            }
            pw.println("!!!END!!!");
        } finally {
                // ne se zatvara PrintWriter bidejki go zatvara i soketot
                pw.flush();
                br.close();
        }
    }

    public void run(){
        try {
            listFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPClient client1 = new TCPClient("C:\\Users\\mario\\Desktop\\in");
        TCPClient client2 = new TCPClient("C:\\Users\\mario\\Desktop\\in2");
        client1.start();
        client2.start();
    }
}
