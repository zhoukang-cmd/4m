package com.fourm.client.test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.fourm.common.ZipUtil;

@SuppressWarnings("unused")
public class TestZip {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		final File f = new File("d:/testztc.dat");
		final FileWriter fw = new FileWriter(f, false);
		new Thread(new Runnable() {
			
			public void run() {
				int i=1;
				while(true) {
					
					try {
						fw.write((i++) + "hello world\n");
						Thread.sleep(1000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}).start();
		final FileReader fr = new FileReader(f);
		new Thread(new Runnable() {
			
			public void run() {
				int line = 0;
				for (int i = 0;; i++) {
					try {
						Thread.sleep(1001);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						FileUtils.copyFile(f, new File("d:/a" + ".txt"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					/*BufferedReader br = new BufferedReader(fr);
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(
								new File("d:/a" + i + ".txt")));
						int tmp = 0;
						while (tmp <= line) {
							br.readLine();
							tmp++;
						}
						while (br.ready()) {
							String s = br.readLine();
							bw.write(s + "\n");
							line++;
							System.out.println(s);
						}
						System.out.println(line);
						bw.flush();
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/

				}
				
				
			}
		}).start();
		Thread.sleep(10000);
		ZipUtil.zip("d:/testztc.dat", "d:/a.zip");
	}

}
