
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

	public static int N, M, R, top,bottom,left, right;
	public static int arr[][];
	public static LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {1,0,-1,0};
	
	public static void printArr() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean isIn(int y, int x, int d) {
		// 하
		if(d == 0) {
			return y <= bottom;
		}
		// 우
		if(d == 1) {
			return x <= right;
		}
		// 상
		if(d==2) {
			return y >= top;
		}
		// 좌 
		if(d==3) {
			return x >= left;
		}
		return false;
	}

	public static void update(int step) {
		top = step;
		bottom = N - step + 1;
		left = step;
		right = M - step + 1;

		int ny = step, nx = step;
		for(int d=0; d<4; d++)
		{
			while(true)
			{
				ny += dy[d];
				nx += dx[d];
				
				if(isIn(ny,nx,d)) {
					list.add(arr[ny][nx]);
				}
				else {
					ny -= dy[d];
					nx -= dx[d];
					break;
				}
			}
		}
		
		list.add(0, list.remove(list.size()-1));
		
		
		ny = step; 
		nx = step;
		for(int d=0; d<4; d++)
		{
			while(true)
			{
				ny += dy[d];
				nx += dx[d];
				
				if(isIn(ny,nx,d)) {
					arr[ny][nx] = list.remove(0);
				}
				else {
					ny -= dy[d];
					nx -= dx[d];
					break;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] toks = br.readLine().split(" ");
		N = Integer.parseInt(toks[0]);
		M = Integer.parseInt(toks[1]);
		R = Integer.parseInt(toks[2]);

		arr = new int[N + 1][];
		arr[0] = new int[M + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = new int[M + 1];
		}
		
		for (int i = 1; i <= N; i++) {
			toks = br.readLine().split(" ");

			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(toks[j - 1]);
			}
		}

		int step = Math.min(N / 2, M / 2);
		for(int r=0; r<R; r++)
		{
			for(int s=1; s<=step; s++)
			{
				update(s);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				bw.append(String.valueOf(arr[i][j])).append(" ");
			}
			bw.append("\n");
		}
		bw.flush();
	}
}