import java.io.*;
import java.util.*;

/**
 * Duc's class
 */
public class MyTest implements Answer {

    @Override
    public void exec(String[] args) throws Exception {

        int result = new YourAnswer().answerTo(new File(args[0]));
        FileOutputStream file = new FileOutputStream(args[1]);
        file.write(String.valueOf(result).getBytes());
        file.close();
    }

    private static class YourAnswer {
        private static final String SPACE = " ";

        public int answerTo(File inf) throws Exception {
            try (BufferedReader br = new BufferedReader(new FileReader(inf))) {
                int n = Integer.parseInt(br.readLine());
                int l = Integer.parseInt(br.readLine());
                Cluster[] clusters = new Cluster[n + 1];
                int[] clusterSize = new int[n/2];
                Cluster[][] propagator = new Cluster[n/2][];
                int cname = 0;
                for (int i = 0; i < l; i++) {
                    String[] pieces = br.readLine().split(SPACE);
                    int left = Integer.parseInt(pieces[0]);
                    int right = Integer.parseInt(pieces[1]);
                    Cluster leftCluster = clusters[left];
                    Cluster rightCluster = clusters[right];
                    if (leftCluster == null) {
                        if (rightCluster == null) {
                            Cluster c = new Cluster(cname++);
                            propagator[c.alias] = new Cluster[1];
                            propagator[c.alias][0] = c;
                            clusters[left] = clusters[right] = c;
                            clusterSize[clusters[left].alias] = 2;
                        } else {
                            clusters[left] = clusters[right];
                            clusterSize[clusters[left].alias]++;
                        }
                    } else {
                        if (rightCluster == null) {
                            clusters[right] = clusters[left];
                            clusterSize[clusters[left].alias]++;
                        } else {
                            if (leftCluster.alias != rightCluster.alias) {
                                Cluster dad = leftCluster;
                                Cluster son = rightCluster;
                                if (propagator[dad.alias].length < propagator[son.alias].length) {
                                    dad = rightCluster;
                                    son = leftCluster;
                                }
                                clusterSize[dad.alias] += clusterSize[son.alias];
                                clusterSize[son.alias] = 0;
                                Cluster[] dads = propagator[dad.alias];
                                Cluster[] sons = propagator[son.alias];
                                int sonalias = son.alias;
                                for (Cluster s: sons) {
                                    s.alias = dad.alias;
                                }
                                Cluster[] newDads = new Cluster[dads.length + sons.length];
                                System.arraycopy(dads, 0, newDads, 0, dads.length);
                                System.arraycopy(sons, 0, newDads, dads.length, sons.length);
                                propagator[dad.alias] = newDads;
                                propagator[sonalias] = null;
                            }
                        }
                    }
                }
                int maxSize = clusterSize[0];
                for (int i = 1; i < cname; i++) {
                    int size = clusterSize[i];
                    if (size > maxSize) maxSize = size;
                }
                return maxSize;
            }
        }

        private static class Cluster {
            int alias;
            int ori;
            Cluster(int n){
                alias = n;
                ori = n;
            }
        }
    }
}