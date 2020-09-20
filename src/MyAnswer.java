
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/*
Lan's solution
 */
public class MyAnswer implements Answer {

	public void exec(String[] args) throws Exception{
		Set<String> results = new HashSet<>();
		FileInputStream inputStream = new FileInputStream(new File(args[0]));
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

        FileOutputStream file = new FileOutputStream(args[1]);
        file.write(String.valueOf(results.size()).getBytes());
        file.close();
	}
}
