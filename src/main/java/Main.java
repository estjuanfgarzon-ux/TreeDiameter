import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adj;

    static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static Pair bfs(int start, int n) {

        boolean[] visited = new boolean[n + 1];

        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(start, 0));

        visited[start] = true;

        Pair last = new Pair(start, 0);

        while (!q.isEmpty()) {

            Pair cur = q.poll();

            last = cur;

            for (int next : adj[cur.node]) {

                if (!visited[next]) {

                    visited[next] = true;

                    q.add(new Pair(next, cur.dist + 1));
                }
            }
        }

        return last;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();

        adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {

            int a = fs.nextInt();
            int b = fs.nextInt();

            adj[a].add(b);
            adj[b].add(a);
        }

        Pair first = bfs(1, n);

        Pair second = bfs(first.node, n);

        System.out.println(second.dist);
    }

    static class FastScanner {

        private final InputStream in;

        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {

            if (ptr >= len) {

                len = in.read(buffer);

                ptr = 0;

                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}