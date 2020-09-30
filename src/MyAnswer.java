
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
			}

		} finally {
			reader.close();
			inputStream.close();
		}

		FileOutputStream file = new FileOutputStream(args[1]);
		file.write(String.valueOf(max).getBytes());
		file.close();
//		long end = System.nanoTime();
//		System.out.println("Final result: " + max + " in: " + (end-start));
	}

	public List<Set<String>> findCountryGroup(List<Set<String>> groups, String[] temps) {
		return groups.parallelStream().filter(item -> item.contains(temps[0]) || item.contains(temps[1])).collect(Collectors.toList());
	}

	public int addToMatchedCountryGroup(List<Set<String>> groups, String[] temps, int max) {
		List<Integer> removeIndex = new ArrayList<>();
		Set<String> firstGroup = null;
		String firstValue = temps[0];
		String secondValue = temps[1];
		for(int i=0; i< groups.size(); i++) {
			Set<String> currentGroup = groups.get(i);
			if(currentGroup.contains(firstValue)) {
				firstGroup = updateCountryGroup(removeIndex, firstGroup, secondValue, i, currentGroup);
			} else if(currentGroup.contains(secondValue)) {
				firstGroup = updateCountryGroup(removeIndex, firstGroup, firstValue, i, currentGroup);
			}
		}

		if(firstGroup == null) {
			Set<String> tempGroups = new HashSet<>();
			tempGroups.add(firstValue);
			tempGroups.add(secondValue);
			groups.add(tempGroups);
		} else {
			if(firstGroup.size() > max) {
				max = firstGroup.size();
			}
			//Remove duplicate group
			for(int i=removeIndex.size()-1; i>=0; i--) {
				groups.remove(removeIndex.get(i));
			}
		}

		return max;
	}

	private Set<String> updateCountryGroup(List<Integer> removeIndex, Set<String> firstGroup, String newCountry, int i,
										   Set<String> currentGroup) {
		if(firstGroup == null) {
			currentGroup.add(newCountry);
			firstGroup = currentGroup;
		} else {
			firstGroup.addAll(currentGroup);
			removeIndex.add(i);
		}
		return firstGroup;
	}

}
