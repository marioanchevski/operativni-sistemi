package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Writer;

public class HW01_2 {
	private static String izvor = "C:\\Users\\mario\\Documents\\OSTRY\\izvor1.txt";
	private static String dest = "C:\\Users\\mario\\Documents\\OSTRY\\dest1.txt";
	
	public static void dosmth(File file,File file2) throws FileNotFoundException, IOException {
		try(RandomAccessFile raf = new RandomAccessFile(file,"rw");
				RandomAccessFile raf2 = new RandomAccessFile(file2,"rw");	){
			
			long length = raf.length();
			raf.seek(length);
			while(true) {	
				int c = raf.read();
				if(c!=-1) raf2.write(c);
				if(c==10) raf2.writeUTF("\r\n");
				length--;
				if(length<0)
					break;
				else
					raf.seek(length);
			}
		}
	}
	
	public static void writeToSource(File file) throws IOException {
		if(file.exists() && file.isFile()) {
			try(Writer wr = new FileWriter(file);){
				wr.write("Hello Friend\r\n");
				wr.write("My string\r\n");
				wr.write("its not that hard 123");
			}
		}
	}
	
	public static void delete() {
		File in = new File(izvor);
		File out = new File(dest);
		if(in.delete()) {
			System.out.println("Created !");
		}
		out.delete();
	}
	
	public static void createSource() throws IOException {
		File file = new File(izvor);
		if(!file.exists()) {
			if(file.createNewFile()) {
				System.out.println(file.getName() + " created!");
				writeToSource(file);
			}
		}else {
			delete();
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
