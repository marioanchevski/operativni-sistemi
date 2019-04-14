package lab1;
import java.nio.charset.StandardCharsets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;




public class HW01_4 {
	private static String izvor = "C:\\Users\\mario\\Documents\\OSTRY\\test2.txt";
	private static String dest = "C:\\Users\\mario\\Documents\\OSTRY\\test2.tsv";
	
	public static void average(String path) throws IOException {
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))))) {
		 
		String line;
		String[] delovi=null;
		String[] predmeti = null;
		double[] vkupniOcenkiPoPredmet = null;
		int brStudenti = 0;
		int brPredmeti = 0;
		while((line=br.readLine())!=null) {
		if(delovi==null) {	delovi=line.split(",");
			brPredmeti = delovi.length-1;
			vkupniOcenkiPoPredmet = new double[brPredmeti];
			predmeti = new String[brPredmeti];
         
            for (int i = 1; i < delovi.length; ++i)
                predmeti[i - 1] = delovi[i];
		}
		else{
            delovi=line.split(",");
            double average = 0d;
            
            for (int i = 1; i < delovi.length; ++i) {
            	vkupniOcenkiPoPredmet[i - 1] += Double.parseDouble(delovi[i]);
                average += Double.parseDouble(delovi[i]);
            }
            if (brPredmeti > 0)
                average /= brPredmeti;
            else
                average = 0d;
            System.out.println(String.format("Student: %s, average: %.2f", delovi[0], average));
            ++brStudenti;
		}	
		 if (vkupniOcenkiPoPredmet != null && brStudenti > 0) {
		        System.out.println("\n===== Subjects =====");
		        for (int i = 0; i < vkupniOcenkiPoPredmet.length; ++i)
		            System.out.println(String.format("Subject: %s, average: %.2f", predmeti[i],
		            		vkupniOcenkiPoPredmet[i] / brStudenti));



	
	}
		}} }
	public static void convert(String izvor,String dest) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(izvor))));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dest))));	
				){
			String line;
			String first = br.readLine();
			String[] lines = first.split(",");
			for(int i=0;i<lines.length;i++) {
				if(i==lines.length-1) {
					bw.write(lines[i]+"\r\n");
				}
				else {
					bw.write(lines[i]+"\t");
				}
			}
			while((line = br.readLine())!=null) {
				String [] linee = line.split(",");
				for(int i = 0;i<linee.length;i++) {
					if(i==linee.length-1) {
						bw.write(lines[i]+"\r\n");
					}
					else {
						bw.write(lines[i]+"\t");
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		average(izvor);
		convert(izvor,dest);
		
		
		
	}

}
