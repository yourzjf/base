package net.yourzjf.corejava.lang;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import net.yourzjf.corejava.myUtils.DiskSpaceWithUnit;

public class TProcess {

	private boolean interrupt;

	public static void main(String[] args) {
		new TProcess().run();
	}

	private void run() {
		Runtime r = Runtime.getRuntime();
		System.out.println(new DiskSpaceWithUnit(r.totalMemory()).humanReadableVal(3));
		System.out.println(new DiskSpaceWithUnit(r.freeMemory()).humanReadableVal(3));
		System.out.println(new DiskSpaceWithUnit(r.maxMemory()).humanReadableVal(3));
		try {
			String path = "/Users/zhangjianfeng/DevTools/";
			String fpath = path + "abc.log";
			File fi = new File(fpath);
			if (!fi.exists() && !fi.isFile()) {
				fi.createNewFile();
			}
			System.out.println(fi.exists());
			System.out.println(fi.isFile());
			new WriteThread(fpath).start();
			// Process process = r.exec(new String[] { "top" });
			Process process = r.exec("tail -10f " + fpath);
			new ReadThread(process.getInputStream()).start();
			new InterruptThread().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class InterruptThread extends Thread {
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			interrupt = true;
			System.exit(0);
		}
	}

	class WriteThread extends Thread {

		private String fileName;

		public WriteThread(String fileName) {
			this.fileName = fileName;
		}

		@Override
		public void run() {
			Properties props = System.getProperties();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while (!interrupt) {
				for (Entry<Object, Object> s : props.entrySet()) {
					writeToFile(fileName, sdf.format(new Date()) + " -> " + s.toString());
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private void writeToFile(String fileName, String line) {
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName, true))) {
				os.write(line.getBytes());
				os.write("\n".getBytes());
				os.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ReadThread extends Thread {

		private InputStream is;

		public ReadThread(InputStream is) {
			this.is = is;
		}

		@Override
		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			try {
				while (!interrupt && (line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
