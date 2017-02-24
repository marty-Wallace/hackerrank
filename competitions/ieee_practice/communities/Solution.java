import java.util.*;
public class Solution{

    private int V;    //number of Vertices
    private int preCount; // preorder number counter
    private int[] low; // low number of v 
    private boolean[] visited; // to check if v is visited 
    private List<Integer>[] graph; // to store given graph

    private List<List<Integer>> sccComp; // to store all scc
    private Stack<Integer> stack;

 

    /** function to get all strongly connected components **/
    public List<List<Integer>> getSCComponents(List<Integer>[] graph){
        V = graph.length;
        this.graph = graph;
        low = new int[V];
        visited = new boolean[V];
        stack = new Stack<Integer>();
        sccComp = new ArrayList<>();

        for (int v = 0; v < V; v++)
              if (!visited[v])
                dfs(v);

        return sccComp;
    }

    /** function dfs **/
    public void dfs(int v){
        low[v] = preCount++;
        visited[v] = true;
        stack.push(v);
        int min = low[v];
        for (int w : graph[v]){
            if (!visited[w])
                dfs(w);
            if (low[w] < min) 
                min = low[w];
        }
        if (min < low[v]){ 
            low[v] = min; 
            return; 
        }        
        List<Integer> component = new ArrayList<Integer>();
        int w;
        do{
            w = stack.pop();
            component.add(w);
            low[w] = V;                
        } while (w != v);
        sccComp.add(component);        
    }    

    /** main **/

    public static void main(String[] args){    
        Scanner input = new Scanner(System.in);
        
        int V = input.nextInt(); // number of vertices 
        int toPrint = input.nextInt();
        input.nextLine();
        List<Integer>[] g = new List[V]; //make graph  
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();        

        List<String> s = new ArrayList<String>();
        Map<String, Integer> m = new HashMap<String, Integer>();
        String line = input.nextLine();
        int counter = 0;
        while(!line.equals("END")){
            String[] split = line.split(" ");
            String s1 = split[0];
            String s2 = split[1];
            if(!m.containsKey(s1)){
                m.put(s1, counter++);
            }
            if(!m.containsKey(s2)){
                m.put(s2, counter++);
            }
            s.add(line);
            line = input.nextLine();
        }
        int E = s.size();
        for (int i = 0; i < E; i++){
            String [] split = s.get(i).split(" ");
            int x = m.get(split[0]);
            int y = m.get(split[1]);
            g[x].add(y);
        }

        Solution t = new Solution();        
        /** print all strongly connected components **/
        List<List<Integer>> scComponents = t.getSCComponents(g);
        int[] x = new int [scComponents.size()];
        for(int i = 0; i < scComponents.size(); i++){
            x[i] = scComponents.get(i).size();
        }
        Arrays.sort(x);
        for(int i = 0; i < toPrint; i++){
            if(i < x.length){
                System.out.println(x[x.length -1 - i]);
            }else{
                System.out.println("Does not apply!");
            }
        }
    }    

}
