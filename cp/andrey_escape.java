package cp;

import java.io.*;
import java.util.*;

public class andrey_escape {

    static class Portal implements Comparable<Portal> {
        int l, r, a, b;
        int effL, effR;

        Portal(int l, int r, int a, int b) {
            this.l = l;
            this.r = r;
            this.a = a;
            this.b = b;
            this.effL = l;
            this.effR = b;
        }

        @Override
        public int compareTo(Portal other) {
            return Integer.compare(this.effL, other.effL);
        }
    }

    static class MergedInterval {
        int l, r, maxB;

        MergedInterval(int l, int r, int maxB) {
            this.l = l;
            this.r = r;
            this.maxB = maxB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Portal[] portals = new Portal[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                portals[i] = new Portal(l, r, a, b);
            }
            Arrays.sort(portals);
            List<MergedInterval> merged = new ArrayList<>();
            if (n > 0) {
                int curL = portals[0].effL;
                int curR = portals[0].effR;
                int curMaxB = portals[0].b;

                for (int i = 1; i < n; i++) {
                    if (portals[i].effL <= curR) {
                        curR = Math.max(curR, portals[i].effR);
                        curMaxB = Math.max(curMaxB, portals[i].b);
                    } else {
                        merged.add(new MergedInterval(curL, curR, curMaxB));
                        curL = portals[i].effL;
                        curR = portals[i].effR;
                        curMaxB = portals[i].b;
                    }
                }
                merged.add(new MergedInterval(curL, curR, curMaxB));
            }

            int q = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < q; i++) {
                int x = Integer.parseInt(st.nextToken());
                int ans = x;

                int lo = 0, hi = merged.size() - 1;
                while (lo <= hi) {
                    int mid = (lo + hi) >> 1;
                    MergedInterval mi = merged.get(mid);

                    if (mi.l <= x && x <= mi.r) {
                        ans = Math.max(ans, mi.maxB);
                        break;
                    } else if (mi.l > x) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                sb.append(ans).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}