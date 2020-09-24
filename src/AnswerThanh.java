import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Thanh's solution
 */
public class AnswerThanh implements Answer {
	public static String file = "D:\\zzz_work\\cityf\\vision-exercises\\tc4_input.txt";

	public void exec(String[] args) throws FileNotFoundException, IOException, Exception {
		int lineNo = 0;
		List<Set> groups = new ArrayList();

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lineNo < 2) {
					lineNo++;
				} else {
					String[] c = line.split(" ");
					String c1 = c[0];
					String c2 = c[1];
					Set set = null;
					boolean existed = false;
					int firstInstance = -1;
					for (int i = 0; i < groups.size(); i++) {
						set = groups.get(i);
						if (set.contains(c1) || set.contains(c2)) {
							if (!existed) {
								set.add(c1);
								set.add(c2);
								firstInstance = i;
								existed = true;
							} else {
								groups.get(firstInstance).addAll(set);
							}
						}
					}
					if (!existed) {
						Set newSet = new HashSet();
						newSet.add(c1);
						newSet.add(c2);
						groups.add(newSet);
					}
				}
			}
		}

		int max = groups.stream().map(s -> s.size()).max((a, b) -> a - b).orElse(-1);
		FileOutputStream file = new FileOutputStream(args[1]);
		file.write(String.valueOf(max).getBytes());
		file.close();
	}
}
