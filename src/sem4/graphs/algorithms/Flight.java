package sem4.graphs.algorithms;

import java.io.StringReader;
import java.util.*;

/**
 * author: Bikchurin Alexey, 9302;
 *
 * How to use:
 * Flight.Info info = Flight.getFlightsInfo(someString);
 * info.out();
 */
public class Flight {

    private static final long INF = Integer.MAX_VALUE;

    private static final Map<String, Map<String, Long>> vers = new HashMap<>();
    private static final Set<String> cities = new HashSet<>();

    private static final Map<String, Map<String, Long>> ans = new HashMap<>();
    private static final Map<String, Long> d = new HashMap<>();
    private static final Set<String> used = new HashSet<>();

    private Flight() {

    }

    /**
     * Run program without string variable (but with Scanner)
     *
     * @return Info object with information about the shortest path
     */
    public static Info getFlightsInfo(){
        return getFlightsInfo(null);
    }

    /**
     * Run program with string variable
     *
     * @param input contain info about flights
     * @return Info object with information about the shortest path
     */
    public static Info getFlightsInfo(String input) {
        Scanner sc;

        if(input == null)
            sc = new Scanner(System.in);
        else
            sc = new Scanner(new StringReader(input));

        int count = sc.nextInt();

        while(count-- > 0){
            String line = sc.next();

            if(line.equals("")){
                ++count;
                continue;
            }

            String[] info = line.split(";");

            cities.add(info[0]);
            cities.add(info[1]);

            if(!info[2].equals("N/A")){
                vers.putIfAbsent(info[0], new HashMap<>());
                vers.get(info[0]).put(info[1], Long.parseLong(info[2]));
            }

            if(!info[3].equals("N/A")){
                vers.putIfAbsent(info[1], new HashMap<>());
                vers.get(info[1]).put(info[0], Long.parseLong(info[3]));
            }
        }

        for(String cityA: cities){
            dijkstra(cityA);

            for(String cityB: d.keySet()){
                ans.putIfAbsent(cityA, new HashMap<>());
                ans.get(cityA).put(cityB, d.get(cityB));
            }
        }

        return new Info(ans);
    }

    private static void dijkstra(String start){
        used.clear();

        for(String city: cities)
            d.put(city, INF);
        d.put(start, 0L);

        for(String i: cities){
            String v = null;

            for(String j: cities){
                if(!used.contains(j) && (v == null || d.get(j) < d.get(v)))
                    v = j;
            }

            if(d.get(v) >= INF)
                break;
            used.add(v);

            for(String e: vers.get(v).keySet()){
                if(d.get(v) + vers.get(v).get(e) < d.get(e))
                    d.put(e, d.get(v) + vers.get(v).get(e));
            }
        }
    }

    /**
     * Class for tests
     */
    public static class Info {

        private final Map<String, Map<String, Long>> ans;

        public Info(Map<String, Map<String, Long>> ans) {
            this.ans = ans;
        }

        /**
         * Get the shortest path (from start to end)
         *
         * @param start cityA
         * @param end cityB
         * @return length of shortest path (-1 if path doesn't exist)
         */
        public long getD(String start, String end){
            long d = ans.get(start).get(end);

            return (d >= INF)? -1 : d;
        }

        public void out(){
            StringBuilder out = new StringBuilder();

            for(String cityA: ans.keySet()){
                out.append(cityA).append(": ");

                for(String cityB: ans.get(cityA).keySet()){
                    long dv = ans.get(cityA).get(cityB);

                    out.append("(")
                            .append(cityB)
                            .append(" = ")
                            .append((dv >= INF)? "inf" : dv)
                            .append(")   ");
                }

                out.append("\n");
            }

            System.out.println(out.toString());
        }
    }
}
