
import java.io.*;
import java.util.*;

public class Solution {
	static int dataLen; 
	static int start, from, to; 
	static boolean[][] graph;
	static boolean[] visit;  
	static Queue<Integer> queue;
	static int size = 101;
	static String[] toks;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	for(int t = 1; t <= 10; t++) {
    		graph = new boolean[size][size];
    		visit = new boolean[size];
    		queue = new ArrayDeque<>();
    		    		
    		toks = br.readLine().split(" ");
    		
    		dataLen = Integer.parseInt(toks[0]);
    		start = Integer.parseInt(toks[1]);
    		
    		toks = br.readLine().split(" ");
    		
    		int i = 0;
    		
    		while(i < toks.length) {
    			from = Integer.parseInt(toks[i++]);
    			to = Integer.parseInt(toks[i++]);
    			
    			graph[from][to] = true;
    		}	
    		
    		sb.append("#").append(t).append(" ").append(bfs(start)).append("\n");
    	}
    	System.out.print(sb.toString());
    }
    
    public static int bfs(int x) {

    	visit[x] = true; 
    	queue.offer(x); 
    	int max = 0; 
    	
    	ArrayList<Integer> list = new ArrayList<>(); 
    	
    	while(!queue.isEmpty()) {
    		max = 0;
    		int qCnt = queue.size();
    		
    		for(int i = 0; i < qCnt; i++) {
				int cur = queue.poll();
				for(int j = 1; j < size; j++) {
					
					if(graph[cur][j] && !visit[j]) {
						visit[j] = true;
						queue.offer(j);
						max = Math.max(max, j);
					}
				}
    		}
    		list.add(max); 
    	}
    	return list.get(list.size()-2);
    }
}