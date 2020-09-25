
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Lan's solution
 */
public class MyAnswer implements Answer {

//	public void exec(String[] args) throws Exception{
//		Set<String> results = new HashSet<>();
//		FileInputStream inputStream = new FileInputStream(new File(args[0]));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//		try {
//			reader.readLine();
//			reader.readLine();
//			String line;
//
//			Set<String> tempResults = new HashSet<>();
//			while((line = reader.readLine()) != null) {
//				String[] temps = line.split(" ");
//				if(results.size() == 0) {
//					tempResults.add(temps[0]);
//					tempResults.add(temps[1]);
//					results = tempResults;
//				} else {
//					if(!tempResults.contains(temps[0]) && !tempResults.contains(temps[1])) {
//						if(tempResults.size() > results.size()) {
//							results = tempResults;
//						}
//						tempResults = new HashSet<>();
//					}
//					tempResults.add(temps[0]);
//					tempResults.add(temps[1]);
//				}
//			}
//			if(tempResults.size() > results.size()) {
//				results = tempResults;
//			}
//
//		} finally {
//			reader.close();
//			inputStream.close();
//
//		}
//		System.out.println("Final result: " + results.size());
//
//        FileOutputStream file = new FileOutputStream(args[1]);
//        file.write(String.valueOf(results.size()).getBytes());
//        file.close();
//	}

	public void exec(String[] args) throws Exception{
//		long start = System.nanoTime();
		List<Set<String>> groups = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(new File(args[0]));
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		MyAnswer myAnswer2 = new MyAnswer();
		int max = 2;
		try {
			reader.readLine();
			reader.readLine();
			String line;

			while((line = reader.readLine()) != null) {
				String[] temps = line.split(" ");
				max = myAnswer2.addToMatchedCountryGroup(groups, temps, max);
				/*List<Set<String>> matchedCountryGroup = myAnswer2.findCountryGroup(groups, temps);
				if(matchedCountryGroup.size() == 0) {
					Set<String> tempGroups = new HashSet<>();
					tempGroups.add(temps[0]);
					tempGroups.add(temps[1]);
					groups.add(tempGroups);
				} else {
					Set<String> matchGroup = matchedCountryGroup.get(0);
					matchGroup.add(temps[0]);
					matchGroup.add(temps[1]);
					if(matchedCountryGroup.size() > 1) {
						for(int i = 1; i<matchedCountryGroup.size(); i++) {
							Set<String> currentGroup = matchedCountryGroup.get(i);
							matchGroup.addAll(currentGroup);
							groups.remove(currentGroup);
						}
					}
					if(matchGroup.size() > max) {
						max=matchGroup.size();
					}
				}*/
			}

		} finally {
			reader.close();
			inputStream.close();

		}
//		long end = System.nanoTime();
//		System.out.println("Final result: " + max + " in: " + (end-start));
		FileOutputStream file = new FileOutputStream(args[1]);
		file.write(String.valueOf(max).getBytes());
		file.close();
	}

	public List<Set<String>> findCountryGroup(List<Set<String>> groups, String[] temps) {
		return groups.parallelStream().filter(item -> item.contains(temps[0]) || item.contains(temps[1])).collect(Collectors.toList());
	}

	public int addToMatchedCountryGroup(List<Set<String>> groups, String[] temps, int max) {
		List<Integer> removeIndex = new ArrayList<>();
		Set<String> firstGroup = null;
		for(int i=0; i< groups.size(); i++) {
			Set<String> currentGroup = groups.get(i);
			if(currentGroup.contains(temps[0]) || currentGroup.contains(temps[1])) {
				if(firstGroup == null) {
					currentGroup.add(temps[0]);
					currentGroup.add(temps[1]);
					firstGroup = currentGroup;
				} else {
					firstGroup.addAll(currentGroup);
					removeIndex.add(i);
				}
				/*if(firstGroup.size() > max) {
					max = firstGroup.size();
				}*/
			}
		}


		if(firstGroup == null) {
			Set<String> tempGroups = new HashSet<>();
			tempGroups.add(temps[0]);
			tempGroups.add(temps[1]);
			groups.add(tempGroups);
		} else {
			//Remove duplicate group
			if(firstGroup.size() > max) {
				max = firstGroup.size();
			}
			for(int i=removeIndex.size()-1; i>=0; i--) {
				groups.remove(i);
			}
		}

		return max;
	}

}
