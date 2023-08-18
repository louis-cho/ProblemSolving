import java.io.*;
import java.util.*;

class Trace
{
	int y,x,time;
	
	Trace(int time, int y, int x) {
		this.time = time;
		this.y = y;
		this.x = x;
	}
}

public class Main {

	static int R,C;
	static char map[][];
	static boolean visited[][];
	static boolean used[];
	
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	
	static String[] toks;
	static String tok;
	static Queue<Trace> q = new ArrayDeque<>();
	static int answer = 0;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		toks = br.readLine().split(" ");
		
		R = Integer.parseInt(toks[0]);
		C = Integer.parseInt(toks[1]);
		
		map = new char[R][];
		visited = new boolean[R][];
		used = new boolean[26];
		
		for(int i=0; i<R; i++)
		{
			map[i] = new char[C];
			visited[i] = new boolean[C];
			
			tok = br.readLine();
			for(int j=0; j<C; j++)
			{
				map[i] = tok.toCharArray();
			}
		}
		
		used[map[0][0]-'A'] = true;
		visited[0][0] = true;
		searchLength(1, 0,0);
		
		System.out.println(answer);
	}
	
	static boolean isIn(int y, int x) {
		return 0 <= y && y < R && 0<=x && x<C;
	}
	
	static void searchLength(int time, int y, int x) {
		if(time > answer) {
			answer = time;
		}
		
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(isIn(ny, nx) && !used[map[ny][nx] - 'A'] && !visited[ny][nx]) {
				used[map[ny][nx]-'A'] = true;
				visited[ny][nx] = true;
				searchLength(time + 1, ny, nx);
				visited[ny][nx] = false;
				used[map[ny][nx]-'A'] = false;
			}
		}
	}
}
