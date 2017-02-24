import java.io.*;
import java.util.*;
import java.text.*;
public class Solution {
    public static final double EARTH = 6378.137;
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in); 
        String[] line = input.nextLine().split(",");
        double a1 = Double.parseDouble(line[0]);
        double a2 = Double.parseDouble(line[1]);
        double distance = Double.parseDouble(input.nextLine());
        input.nextLine();
        Map<String, Location> map = new HashMap<String, Location>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        while(input.hasNextLine()) {
            String[] l = input.nextLine().split(",");
            Date d = sdf.parse(l[0]);
            double a = Double.parseDouble(l[1]);
            double o = Double.parseDouble(l[2]);
            String number = l[3];
            Location ll = new Location(a, o, d);
            if(map.containsKey(number)){
                Location l2 = map.get(number);
                if(l2.d.compareTo(ll.d) < 0 ) {
                    map.remove(number);
                    map.put(number, ll);
                }
            }else{
                map.put(number, ll);
            }
        }
        ArrayList<Long> ans = new ArrayList<Long>();
        for(String s : map.keySet()){
            if(h(a1, a2, map.get(s).a, map.get(s).o) < distance){
                ans.add(Long.parseLong(s));
            }
        }
        Collections.sort(ans);
        String comma = "";
        for(long ss : ans) {
            System.out.print(comma + ss);
            comma = ",";
        }
        System.out.println();
    }
    
    public static double h(double a1, double o1, double a2, double o2) {
        return EARTH * 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((Math.toRadians(a2 - a1)) / 2.0)+Double.MIN_VALUE,2) + Math.pow(Math.sin(Math.toRadians(o2 - o1) / 2.0)+Double.MIN_VALUE,2) * Math.cos((Math.toRadians(a1))) * Math.cos((Math.toRadians(a2)))));
    }
    
    static class Location {
        double a, o; 
        Date d;
        public Location(double a, double o, Date d){
            this.a = a; 
            this.o = o;
            this.d = d;
        }
    }
}
