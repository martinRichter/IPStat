public class ThreadRunner {
	
	/**
	 * Helper method that pauses the thread for 5 seconds
	 */
	private static void pause5(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ie) {
			System.out.println("Sleep error");
		}
	}
	
	public static void main(String[] args) {
		
		T1 t1 = new T1();										
		pause5();
		
		T2 t2 = new T2();
		pause5();
		
		t2.pause();
		System.out.println("Thread 2 paused");
		pause5();
		
		t2.unPause();
		System.out.println("Thread 2 resumed");
		pause5();
		
		t1.kill();
		System.out.println("Killed thread 1");
		pause5();
		
		t2.kill();
		System.out.println("Killed thread 2");
	}

}
