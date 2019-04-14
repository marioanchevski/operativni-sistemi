package lab2;

public class ThreadAB implements Runnable {
	
	private String string1;
	private String string2;
	
	/*private boolean azbuka;
	
	public ThreadAB(boolean azbuka) {
		this.azbuka=azbuka;
	}
	
	public void run() {
		
		if(!azbuka) {
			for(int i=0;i<26;i++) {
				System.out.println(i+1);
			}
		}else {
			for(char i='a';i<='z';i++) {
				System.out.println(i);
			}
		}
	}    */
	
	public ThreadAB(String str1,String str2) {
		string1 = str1;
		string2 = str2;
	}

	@Override
	public void run() {
		System.out.println(string1);
		System.out.println(string2);
	}
    
	
    public static void main(String[] args) {
    	
    	Runnable obj = new ThreadAB("1","2");
    	Thread thread2 = new Thread(obj);
    	thread2.start();
    	Thread thread1 = new Thread(new ThreadAB("A","B"));
    	thread1.start();
    	
    }

}

