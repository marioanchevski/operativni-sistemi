import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread {
    private Socket socket;
    private String outputFile;

    public ServerWorker(Socket socket,String out){
        this.socket=socket;
        outputFile=out;
    }

    public void work() throws IOException {
        BufferedReader br = null;
        PrintWriter pw =null;
        try{
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String line = new String();
            String ime="1";
            while((line=br.readLine())!=null){
                ime = line;
                pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(outputFile,
                        line.replace("#","")),true)),true);
                while(true){
                    line=br.readLine();
                    if(line.startsWith("!!!END!!!"))
                    {
                        System.out.println("Copied"+ ime);
                        break;
                    }
                    pw.println(line);
                }
            }
          }finally {
                br.close();
                pw.flush();
                pw.close();
        }
    }

    public void run(){
        try {
            work();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
