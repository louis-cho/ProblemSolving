import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int people[] = new int[11];
	static boolean graph[][] = new boolean[11][11];
	static Set<Integer> GroupA = new HashSet<>();
	static int answer = Integer.MAX_VALUE;
	static int num;

	static String[] toks;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		people = new int[N+1];
		toks = br.readLine().split(" ");

		// 인원 정보 대입
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(toks[i-1]);
		}

		// 연결 정보 대입
		for (int i = 1; i <= N; i++) {
			toks = br.readLine().split(" ");
			num = Integer.parseInt(toks[0]);
			for (int j = 1; j <= num; j++) {
				int vertex = Integer.parseInt(toks[j]);
				graph[vertex][i] = graph[i][vertex] = true;
			}
		}
		
		select(1);
		
		if(answer != Integer.MAX_VALUE)
			System.out.println(answer);
		else
			System.out.println(-1);
	}
	
	static int check() {
		if(GroupA.size() == N)
			return -1;

		int numOfA = 0, numOfB = 0;
		for(int i=1; i<=N; i++) {
			if(GroupA.contains(i)) {
				if(!isConnected(i, GroupA.size(), true)) {
					return -1;
				} else {
					numOfA += people[i];
				}
			} else {
				if(!isConnected(i, N - GroupA.size(), false)){
					return -1;
				} else {
					numOfB += people[i];
				}
			}
		}
		
		return Math.abs(numOfA - numOfB);
	}
	
	static boolean isConnected(int idx, int size, boolean isA) {
		boolean[] visited = new boolean[N+1];
		visited[idx] = true;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(idx);
		
		int cnt = 1;
		while(!q.isEmpty())
		{
			int elem = q.poll();
			
			for(int i=1; i<=N; i++) {
				if(graph[elem][i] && !visited[i]) {
					visited[i] = true;
					
					if(isA) {
						if(GroupA.contains(i)) {
							q.add(i);
							cnt++;
						}
					} else {
						if(!GroupA.contains(i)) {
							q.add(i);
							cnt++;
						}
					}
				}
			}
		}
		
		return cnt == size;
	}
	
	static void select(int idx) {
		if(idx > N) {
			int ret = check();
			if(ret != -1)
				answer = Math.min(answer, ret);
			return;
		}
		GroupA.add(idx);
		select(idx+1);
		GroupA.remove(idx);
		select(idx+1);
	}
}
