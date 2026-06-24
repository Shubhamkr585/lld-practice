import java.util.*;
import java.io.*;

class CodeChef
{
    // Class representing each server
    static class Server {
        int time;
        int id;
        Server(int time, int id) {
            this.time = time;
            this.id = id;
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String firstLine = br.readLine();
        if (firstLine == null) return;
        st = new StringTokenizer(firstLine);

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Queue<Server> pq = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time)
                return a.id - b.id;
            return a.time - b.time;
        });
        for (int i = 0; i < n; i++) {
            pq.offer(new Server(0, i));
        }
        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            String line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);

            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Queue<Server> freeServers = new PriorityQueue<>((a, b) -> a.id - b.id);
            while (!pq.isEmpty() && pq.peek().time <= t) {
                freeServers.offer(pq.poll());
            }
            if (freeServers.size() < k) {
                sb.append("-1\n");
                pq.addAll(freeServers);
                continue;
            }
            int ans = 0;
            int allocated = 0;
            while (!freeServers.isEmpty()) {
                Server top = freeServers.poll();
                if (allocated < k) {
                    ans += top.id + 1;
                    top.time = t + d;
                    allocated++;
                }
                pq.offer(top);
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
