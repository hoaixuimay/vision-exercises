import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Duy's solution
 */
public class ProblemSolver765 implements Answer {

    public void exec(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            String city = reader.readLine();
            String streetNumbr = reader.readLine();
            String[] streets = new String[Integer.valueOf(streetNumbr)];
            String street = "";
            int counter = 0;
            while ((street = reader.readLine()) != null) {
                streets[counter] = street;
                counter++;
            }
            reader.close();
            int result = ProblemSolver765.execute(streets);
            System.out.println("result: " + result);

            FileOutputStream file = new FileOutputStream(args[1]);
		    file.write(String.valueOf(result).getBytes());
		    file.close();
        }
        catch(Exception e) {
            System.out.println("Something wrong: " + e);
        }
    }

	public static int execute(String[] items){
		List<Cluster> clusters = new ArrayList<>();
		for(int i=0; i<items.length; i++){
			String[] currentItems = items[i].split(" ");
			int foundCluster = 0;
			int[] duplicationCluster = new int[clusters.size()];
			for (int j = 0; j < clusters.size(); j++) {
				Cluster cluster = clusters.get(j);
				HashSet<String> clusterCities = cluster.getConnectableCities();
				if(clusterCities.contains(currentItems[0]) || clusterCities.contains(currentItems[1])){						
					foundCluster++;
					duplicationCluster[j] = 1;
					clusterCities.add(currentItems[0]);
					clusterCities.add(currentItems[1]);
				}
			}
			afterProcesses(clusters, currentItems, foundCluster, duplicationCluster);
		}
		return findBiggestCluster(clusters);
	}

	private static int findBiggestCluster(List<Cluster> clusters) {
		int biggestCluster = 0;
		for (Cluster cluster : clusters) {
			if(biggestCluster < cluster.getConnectableCities().size()){
				biggestCluster = cluster.getConnectableCities().size();
			}
		}
		return biggestCluster;
	}

	private static void afterProcesses(List<Cluster> clusters, String[] currentItems, int foundCluster,
			int[] duplicationCluster) {
		if(foundCluster == 0){
			Cluster newCluster = new Cluster();
			newCluster.getConnectableCities().add(currentItems[0]);
			newCluster.getConnectableCities().add(currentItems[1]);
			clusters.add(newCluster);
		} else if(foundCluster >= 2){
			joinDuplicatedCluster(duplicationCluster, clusters);
		}
	}
	
	private static void joinDuplicatedCluster(int[] containCluster, List<Cluster> clusters) {
		HashSet<String> joinedCities = new HashSet<>();
		for(int i = 0; i < containCluster.length; i++) {
			if(containCluster[i] == 1) {
				joinedCities.addAll(clusters.get(i).getConnectableCities());
				clusters.get(i).getConnectableCities().clear();
			}
		}
		Cluster joinedCluster = new Cluster();
		joinedCluster.getConnectableCities().addAll(joinedCities);
		clusters.add(joinedCluster);
		clusters = clusters.stream().filter(item -> !item.getConnectableCities().isEmpty()).collect(Collectors.toList());
	}
	
	private static class Cluster{
		HashSet<String> connectableCities;
		
		public Cluster(){
			this.connectableCities = new HashSet<>();
		}
		
		public HashSet<String> getConnectableCities(){
			return this.connectableCities;
		}
	}
}
