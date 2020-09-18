package org.qpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
Lan's solution
 */
public class MyAnswer {
	public static void main(String[] args) throws Exception{
		Set<String> results = new HashSet<>();
		FileInputStream inputStream = new FileInputStream(new File("tc4_input.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			reader.readLine();
			reader.readLine();
			String line;
			
			Set<String> tempResults = new HashSet<>();
			while((line = reader.readLine()) != null) {
				String[] temps = line.split(" ");
				if(results.size() == 0) {
					tempResults.add(temps[0]);
					tempResults.add(temps[1]);
					results = tempResults;
				} else {
					if(!tempResults.contains(temps[0]) && !tempResults.contains(temps[1])) {
						if(tempResults.size() > results.size()) {
							results = tempResults;
						}
						tempResults = new HashSet<>();
					} 
					tempResults.add(temps[0]);
					tempResults.add(temps[1]);
				}
			}
			if(tempResults.size() > results.size()) {
				results = tempResults;
			}
			
		} finally {
			reader.close();
			inputStream.close();
			
		}
		System.out.println("Final result: " + results.size());
	}
}
