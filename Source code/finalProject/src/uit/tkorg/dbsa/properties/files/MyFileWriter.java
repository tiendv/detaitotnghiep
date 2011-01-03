package uit.tkorg.dbsa.properties.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileWriter {

	public static void writeToFile(String link, String value) {
		
		File file = new File(link);
		try {
			FileOutputStream fileout = new FileOutputStream(file);
			
			if(file.exists()){
				
				fileout.write(value.getBytes());

				fileout.flush();
				fileout.close();
				System.out.println("The data has been written");
			}else{
				 System.out.println("This file is not exist");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
