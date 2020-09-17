package org.qpro;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataGenerator2 {

	public static void main(String[] args) throws IOException{
		int N = 8000;
		
		int half = N / 2;
		FileOutputStream file = new FileOutputStream("tc4_input.txt");
		
		int count = 0;
		
		ArrayList<String> content = new ArrayList<String>();
		// connected
		for(int i = 1; i <= (half - 1); i++){
			for(int j =(i+1); j <= half; j++){
				String wr;
				if(Math.random() >= 0.5){
					wr = i + " " + j;
				} else {
					wr = j + " " + i;
				}
				content.add(wr);
//				file.write(wr.getBytes());
//				file.write("\n".getBytes());
				count++;
			}
		}
		
		// partial connected
		int step = 100;
		for(int i = half + 1; i <= N; i = i + step){
			// 5001 -> 5100; 5101 -> 5200; ..
			int max = i + (step -1);
			for(int j = i; j < max; j++){
				for(int k = j+1; k <= max; k++ ){
					String wr;
					if(Math.random() >= 0.5){
						wr = j + " " + k;
					} else {
						wr = k + " " + j;
					}
					content.add(wr);
//					file.write(wr.getBytes());
//					file.write("\n".getBytes());
					count++;
				}
			}
		}
		
		
		
		file.write((N+"").getBytes());
		file.write("\n".getBytes());
		file.write((count+"").getBytes());
		file.write("\n".getBytes());
		
		for(String c: content){
			file.write(c.getBytes());
			file.write("\n".getBytes());	
		}
		file.close();
	}
}