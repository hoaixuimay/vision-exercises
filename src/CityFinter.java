
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ngan's solution
 */
public class CityFinter implements Answer{

	@Override
	public void exec(String[] args) throws IOException {
		int result = findCrowdedCities(args[0]);
		FileOutputStream file = new FileOutputStream(args[1]);
		file.write(String.valueOf(result).getBytes());
		file.close();
	}

	public int findCrowdedCities(String inputPath) {
		int nOfCrowdedCity = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputPath));
			reader.readLine();
			reader.readLine();
			String street = "";
			List<Set<Integer>> cityGroups = new ArrayList<>();
			while ((street = reader.readLine()) != null) {
				group(street, cityGroups);
			}
			nOfCrowdedCity = findCrowded(cityGroups);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nOfCrowdedCity;
	}

	private int findCrowded (List<Set<Integer>> cityGroups) {
		int maxLengh = cityGroups.get(0).size();
		for (int i = 1; i < cityGroups.size(); i++) {
			if (maxLengh < cityGroups.get(i).size()) {
				maxLengh = cityGroups.get(i).size();
			}
		}
		System.out.println("Max lengh:= " + maxLengh);
		return maxLengh;
	}

	private void group(String str, List<Set<Integer>> cityGroups) {
		String[] strs = str.split(" ");
		int first = Integer.parseInt(strs[0]);
		int second = Integer.parseInt(strs[1]);
		int size = cityGroups.size();

		int firstNumMatchedAt = size;
		int secondNumMatchedAt = size;
		for (int i = 0; i < cityGroups.size(); i++) {
			if (cityGroups.get(i).contains(first)) {
				firstNumMatchedAt = i;
			}
			if (cityGroups.get(i).contains(second)) {
				secondNumMatchedAt = i;
			}
			if (firstNumMatchedAt != size && secondNumMatchedAt != size) {
				break;
			}
		}

		if (cityGroups.size() == 0 || (firstNumMatchedAt == size && secondNumMatchedAt == size)) {
			Set<Integer> cityGroup = new HashSet<Integer>();
			cityGroup.add(first);
			cityGroup.add(second);
			cityGroups.add(cityGroup);
			return;
		}
		if (firstNumMatchedAt == secondNumMatchedAt) {
			return;
		}
		if (firstNumMatchedAt < size && secondNumMatchedAt == size) {
			cityGroups.get(firstNumMatchedAt).add(second);
		} else if (firstNumMatchedAt == size && secondNumMatchedAt < size) {
			cityGroups.get(secondNumMatchedAt).add(first);
		} else {
			cityGroups.get(firstNumMatchedAt).addAll(cityGroups.get(secondNumMatchedAt));
			cityGroups.remove(secondNumMatchedAt);
		}
	}

}
