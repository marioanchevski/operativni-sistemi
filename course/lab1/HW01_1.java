package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class HW01_1 {
	private static int totalFiles = 0;
	private static int totalLength = 0;
	
	public static void list(String path) {
		File f = new File(path);
		File[] files = f.listFiles();
		
		for(File file:files) {
			if(file.isFile() && file.getName().endsWith(".txt")) {
				totalFiles++;
				totalLength += file.length();
				System.out.println(file.getName());
			}
			if(file.isDirectory()) {
				list(file.getAbsolutePath());
			}
			
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String arg = null;
		arg = br.readLine();
		list(arg);
		System.out.println("Prosecnata golemina iznesuva: "+(float)totalLength/totalFiles);
		
	}
}
