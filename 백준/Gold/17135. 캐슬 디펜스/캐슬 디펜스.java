import java.io.*;
import java.util.*;

class Coord {
	int y, x;

	Coord(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public int hashCode() {
		return this.y * 1000 + this.x;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coord) {
			Coord c = (Coord)obj;
			if(c.y == this.y && c.x == this.x)
				return true;
		}
		
		return false;
	}
}

class TraceData {
	int time;
	Coord coord;

	TraceData(int time, Coord coord) {
		this.time = time;
		this.coord = coord;
	}
}

public class Main {

	static int N, M, D;
	static boolean map[][], copiedMap[][];
	static String[] toks;
	static int answer = 0;
	static int[] archList = new int[3];

	static int dx[] = { -1, 0, 1 };
	static int dy[] = { 0, -1, 0 };

	static Queue<TraceData> q = new ArrayDeque<>();
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		toks = br.readLine().split(" ");
		N = Integer.parseInt(toks[0]);
		M = Integer.parseInt(toks[1]);
		D = Integer.parseInt(toks[2]);

		map = new boolean[N][];
		copiedMap = new boolean[N][];
		visited = new boolean[N][];

		for (int i = 0; i < N; i++) {
			toks = br.readLine().split(" ");
			visited[i] = new boolean[M];
			map[i] = new boolean[M];
			copiedMap[i] = new boolean[M];
			
			for (int j = 0; j < M; j++) {
				if (toks[j].equals("1")) {
					map[i][j] = true;
				}
			}
		}
		
		selectArch(0,-1);
		
		System.out.println(answer);
	}

	// 맵 복사하기
	static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copiedMap[i][j] = map[i][j];
			}
		}
	}

	// 적을 한 줄 이동시키기
	static void moveRow(int t) {
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copiedMap[i + 1][j] = copiedMap[i][j];
			}
		}
		
		for(int j=0; j<M; j++) {
			copiedMap[t][j] = false;
		}
	}

	// 궁사의 위치 선택
	static void selectArch(int cnt, int x) {
		if (cnt == 3) {
			answer = Math.max(answer, simulation());
		} else {
			for (int nx = x + 1; nx < M; nx++) {
				archList[cnt] = nx;
				selectArch(cnt + 1, nx);
			}
		}
	}

	// 적 공격 시뮬레이션
	static int simulation() {
		copyMap();
		
		int ret = 0;
		
		for (int t = 0; t < N; t++) {
			Set<Coord> s = getTargetList();
			for(Coord c: s) {
				copiedMap[c.y][c.x] = false; 
			}
			
			ret += s.size();
			
			moveRow(t);
		}
		
		return ret;
	}

	static boolean isIn(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static Set<Coord> getTargetList() {
		Set<Coord> res = new HashSet<>();

		// 3명의 궁수에 대해
		for (int i = 0; i < 3; i++) {
			
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					visited[n][m] = false;
				}
			}
			
			// 궁수의 바로 앞 위치부터 시작
			int x = archList[i];
			int y = N - 1;
			
			q.clear();
			q.add(new TraceData(1, new Coord(y, x)));
			visited[y][x] = true;

			while (!q.isEmpty()) {
				TraceData topElem = q.poll();
				int time = topElem.time;
				int cy = topElem.coord.y;
				int cx = topElem.coord.x;

				if (time <= D && copiedMap[cy][cx]) {
					res.add(topElem.coord);
					break;
				} else {
					if (time > D) {
						break;
					} else {
						for (int d = 0; d < 3; d++) {
							Coord next = new Coord(cy + dy[d], cx + dx[d]);
							if (isIn(next.y, next.x)) {
								visited[next.y][next.x] = true;
								q.add(new TraceData(time + 1, next));
							}
						}
					}
				}
			}
		}
		
		return res;
	}
}
