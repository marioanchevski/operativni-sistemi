package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

public class HW01_3 {
	private static String izvor = "C:\\Users\\mario\\Documents\\OSTRY\\izvor1.txt";
	private static String dest = "C:\\Users\\mario\\Documents\\OSTRY\\dest1.txt";
	
	public static void dosmth(File file,File file2) throws FileNotFoundException, IOException {
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest)));
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(izvor)));
				){
			String line;
			
			while((line=br.readLine())!=null) {
				StringBuilder sb = new StringBuilder(line);
				bw.write(sb.reverse().toString());
				bw.newLine();
			}
			
		}
		
	}
	
	public static void writeToSource(File file) throws IOException {
		if(file.exists() && file.isFile()) {
			try(Writer wr = new FileWriter(file);){
				wr.write("Hello Friend\r\n");
				wr.write("very expensive\r\n");
				wr.write("its not that hard 123");
			}
		}
	}
	
	public static void createSource() throws IOException {
		File file = new File(izvor);
		if(!file.exists()) {
			if(file.createNewFile()) {
				System.out.println(file.getName() + " created!");
				writeToSource(file);
			}
		}
	}
	
	public static void createDest() throws IOException{
		File file = new File(dest);
		if(!file.exists()) {
			if(file.createNewFile()) {
				System.out.println(file.getName() + " created!");
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
			
		createSource();
		createDest();
		dosmth(new File(izvor),new File(dest));
	}

}
