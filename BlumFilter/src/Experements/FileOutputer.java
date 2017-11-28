package Experements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class FileOutputer {

	public static void GenCSVFile(int[] arr, String filename) {
		File csvFile = new File(filename);
		FileOutputStream fos;
		if(!csvFile.exists())
			try {
				csvFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			fos = new FileOutputStream(csvFile);
			for (int i = 0; i < arr.length; i++) {
				String toWrite = (i + ";" + arr[i]+"\n");
				fos.write(toWrite.getBytes());
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
