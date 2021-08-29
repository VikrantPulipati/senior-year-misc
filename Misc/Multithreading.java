import java.util.concurrent.atomic.AtomicInteger;

public class Multithreading {
	static int i = 0;
	static AtomicInteger ai;
	static int otherI = 0;
	
	public static void main (String[] args) {
		ai = new AtomicInteger(0);
		for (int j = 1; j <= 200; j++) {
			new MyThread().start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("i: " + i);
		System.out.println("ai: " + ai);
		System.out.println("oi: " + otherI);
	}
	
	public static synchronized void addToOtherI () {
		otherI++;
	}
	
	static class MyThread extends Thread {
		
		public void run () {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			ai.getAndAdd(1);
			addToOtherI();
		}
	}
}
