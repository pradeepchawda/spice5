public int networkDelayTime_FW(int[][] times, int N, int K) {
    double[][] disTo = new double[N][N];
    for (int i = 0; i < N; i++) {
        Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
    }
    for (int i = 0; i < N; i++) {
        disTo[i][i] = 0;
    }
    for (int[] edge: times) {
        disTo[edge[0] - 1][edge[1] - 1] = edge[2];
    }
    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                    disTo[i][j] = disTo[i][k] + disTo[k][j];
            }
        }
    }
    double max = Double.MIN_VALUE;
    for (int i = 0; i < N; i++) {
        if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) return -1;
        max = Math.max(max, disTo[K - 1][i]);
    }
    return (int) max;
}


public int networkDelayTime_BF(int[][] times, int N, int K) {
    double[] disTo = new double[N];
    Arrays.fill(disTo, Double.POSITIVE_INFINITY);
    disTo[K - 1] = 0;
    for (int i = 1; i < N; i++) {
        for (int[] edge : times) {
            int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
            disTo[v] = Math.min(disTo[v], disTo[u] + w);
        }
    }
    double res = Double.MIN_VALUE;
    for (double i: disTo) {
        res = Math.max(i, res);
    }
    return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
}



public int networkDelayTime_Dijkstra(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge: times) {
        graph.putIfAbsent(edge[0], new ArrayList<>());
        graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
    }
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
    boolean[] visited = new boolean[N + 1];
    int[] minDis = new int[N + 1];
    Arrays.fill(minDis, Integer.MAX_VALUE);
    minDis[K] = 0;
    pq.offer(new int[]{0, K});
    int max = 0;
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int currNode = curr[1];
        if (visited[currNode]) continue;
        visited[currNode] = true;
        int currDis = curr[0];
        max = currDis;
        N--;
        if (!graph.containsKey(currNode)) continue;
        for (int[] next : graph.get(currNode)) {
            if (!visited[next[0]] && currDis + next[1] < minDis[next[0]])
                pq.offer(new int[]{currDis + next[1], next[0]});
        }
    }
    return N == 0 ? max : -1;
}
