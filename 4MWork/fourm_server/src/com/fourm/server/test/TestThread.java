package com.fourm.server.test;

public class TestThread {

	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new MyThread(500));
		t.start();
		
		Thread t2 = new Thread(new MyThread(1000));
		t2.start();
		t.join();
		System.out.println("t1");
		t2.join();
		System.out.println("t2");
		System.out.print("end...");
	}

}

class MyThread implements Runnable{
	long ii;
	MyThread(long i) {
		this.ii=i;
	}
	public void run() {
		int i=10;
		while(i>0) {
			System.out.println(Thread.currentThread().getId() + ":" + i);
			try {
				Thread.sleep(ii);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i--;
		}
	}
}
