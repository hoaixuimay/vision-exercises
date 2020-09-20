import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class WidestPlace implements Answer {

    @Override
    public void exec(String[] args) throws Exception{
//        long start = System.nanoTime();
//        String tcName = "tc6";
        FileInputStream fileIp = new FileInputStream(args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileIp));
        String line = reader.readLine();
        int totalCitysNo = Integer.parseInt(line);
        Graph graph = new Graph(totalCitysNo);
        line = reader.readLine();
        int totalPaths = Integer.parseInt(line);
        while ((line = reader.readLine()) != null) {
            String[] params = line.split(" ");
            graph.addEdge(Integer.valueOf(params[0]) - 1, Integer.valueOf(params[1]) - 1);
        }
        int connectedCountedTotal = 0;
        int maxConnectivityNo = 0;
        int found = 0;
        for (int i = 0; i < graph.verticesNo; i++) {
            if (graph.bfsVisited[i]) {
                continue;
            }
            int connectCount = graph.bfs(i);
            connectedCountedTotal += connectCount;
            if (connectCount > maxConnectivityNo) {
                maxConnectivityNo = connectCount;
            }
            if (maxConnectivityNo >= (totalCitysNo - connectedCountedTotal)) {
                found = connectCount;
                break;
            }
        }
        FileOutputStream file = new FileOutputStream(args[1]);
        file.write(String.valueOf(found).getBytes());
        file.close();
//        long finish = System.nanoTime();
//        long timeElapsed = finish - start;
//        System.out.printf("Time elapsed: " + timeElapsed);
    }

    private static class Graph {
        private int verticesNo;
        private LinkedList<Integer>[] adjs;
        boolean bfsVisited[];

        public Graph(int verticesNo) {
            this.verticesNo = verticesNo;
            adjs = new LinkedList[verticesNo];
            for (int i = 0; i < verticesNo; i++) {
                adjs[i] = new LinkedList<>();
            }
            this.bfsVisited = new boolean[verticesNo];
        }

        public void addEdge(int u, int v) {
            adjs[u].add(v);
            adjs[v].add(u);
        }

        public int bfs(int from) {
            LinkedList<Integer> queue = new LinkedList<>();
            bfsVisited[from] = true;
            queue.add(from);
            int sFrom = from;
            int connectCount = 0;
            while (queue.size() != 0) {
                sFrom = queue.poll();
                connectCount++;
                Iterator<Integer> iter = adjs[sFrom].iterator();
                while (iter.hasNext()) {
                    int adjOfSFrom = iter.next();
                    if (!bfsVisited[adjOfSFrom]) {
                        bfsVisited[adjOfSFrom] = true;
                        queue.add(adjOfSFrom);
                    }
                }
            }
            return connectCount;
        }
    }
}