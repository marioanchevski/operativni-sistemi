package lab2;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class FileScanner extends Thread{

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = 0L;
    private Semaphore lock = new Semaphore(1);

    public FileScanner (String fileToScan) throws InterruptedException {
        this.fileToScan=fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object
        lock.acquire();
        counter++;
        lock.release();
    }

    public static void printInfo(File file)  {

        /*
        * TODO: Print the info for the @argument File file, according to the requirement of the task
        * */
    	if(file.isDirectory()) {
    		System.out.println("dir "+ file.getAbsolutePath()+" "+file.length());
    	}else if(file.isFile()) {
    		System.out.println("file "+ file.getAbsolutePath()+" "+file.length());
    	}

    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file = new File(fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File [] files = null;
        files = file.listFiles();
        ArrayList<Thread>  lista = new ArrayList<>();

        for (File f : files) {

            /*
            * TODO If the File f is not a directory, print its info using the function printInfo(f)
            * */
        	if(!f.isDirectory()) {
        		printInfo(f);
        	}

            /*
            * TODO If the File f is a directory, create a thread from type FileScanner and start it.
            * */
        	else if(f.isDirectory()){
            	try {
            		printInfo(f);
					FileScanner thread = new FileScanner(f.getAbsolutePath());
					lista.add(thread);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
        	
        	for(Thread t:lista) {
        		t.start();
        	}
        	
            //TODO: wait for all the FileScanner-s to finish
        	for(Thread t:lista) {
        		try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }

    }

    public static void main (String [] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\189075\\Desktop\\lab";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner =  new FileScanner(FILE_TO_SCAN);

        //TODO Start the thread from type FileScanner
        fileScanner.start();

        //TODO wait for the fileScanner to finish
        fileScanner.join();

        //TODO print a message that displays the number of thread that were created
        System.out.println(getCounter());

    }
}

