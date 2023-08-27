import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R, C, M;
	public static int answer = 0;
	public static int dx[] = {-1, 0, 1, 0}; //상 좌 하 우 순 
	public static int dy[] = {0, -1, 0, 1};
	public static Shark[][] map;

	
	//상어 정보를 저장할 상어 클래스 
	static class Shark {
		int r;	// y 좌표
		int c;	// x 좌표
		int s;	// 속도
		int d;	// 방향
		int z;	// 상어 사이즈

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력 받기 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken()); // 행의 수
		C = Integer.parseInt(st.nextToken()); // 열의 수
		M = Integer.parseInt(st.nextToken()); // 상어의 수

		// 상어 낚시 격자판 만들고, 각 위치에 상어 클래스로 만든 인스턴스 저장 
		map = new Shark[R][C];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()); // y 
			int c = Integer.parseInt(st.nextToken()); // x
			int s = Integer.parseInt(st.nextToken()); // speed 
			int d = Integer.parseInt(st.nextToken()); // direction 
			int z = Integer.parseInt(st.nextToken()); // size
 
			// 입력받은 방향을 dx, dy 인덱스에 맞게 설정
			if(d == 1)
				d = 0;
			else if(d == 4)
				d = 1;
            
			map[r-1][c-1] = new Shark(r-1, c-1, s, d, z); // 격자판에 상어 저장 
		}

		
		for(int x = 0; x < C; x++) {	// 낚시꾼 이동 횟수만큼 이동하며
			for(int y = 0; y < R; y++) {	// 가장 가까운 상어 찾기
				if(map[y][x] != null) { 
					answer += map[y][x].z; // 잡은 상어 정답에 추가 
					map[y][x] = null; // 상어 없애기 
					break;	// 반복문 종료
				}
			}
			
			// 상어 이동시키기
			Queue<Shark> queue = new LinkedList<>(); 
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] != null) { // 현재 map에 있는 상어들 큐에 추가 
						queue.add(new Shark(i, j, map[i][j].s, map[i][j].d, map[i][j].z));
					}
				}
			}

			map = new Shark[R][C]; // 빈 공간에 이동시킨 상어를 집어넣기 위해 재할당

			// 모든 상어 한마리씩 꺼내서 이동 
			while(!queue.isEmpty()) {
				Shark mShark = queue.poll();
                
				// 상어 이동거리만큼 옮기기
				int speed = mShark.s;
				if(mShark.d == 0 || mShark.d == 2) //상 하
					speed %= (R -1) * 2;	// 왕복하는 이동 거리 제거
				else if(mShark.d == 1 || mShark.d == 3) //좌 우
					speed %= (C -1) * 2;	// 왕복하는 이동 거리 제거
				
				for(int s = 0; s < speed; s++) {
					// 현재 r, c에 방향에 맞게 1칸씩 추가하며 위치 이동 
					int ny = mShark.r + dx[mShark.d]; 
					int nx = mShark.c + dy[mShark.d];

					// 이동 결과가 맵 밖인 경우 
					if(ny < 0 || ny >= R || nx < 0 || nx >= C) { 
						mShark.r -= dx[mShark.d]; // 원복 후 
						mShark.c -= dy[mShark.d];
						mShark.d = (mShark.d + 2) % 4; // 방향 반대로 
						continue;
					}

					// 위치 벗어나지 않을때는 새로운 위치로 이동 
					mShark.r = ny; 
					mShark.c = nx;
				}

				// 새로운 위치가 빈 공간인지 이미 상어가 있는지 확인
				if(map[mShark.r][mShark.c] != null) { // 이미 상어가 있다면 두 상어 크기 비교 
					if(map[mShark.r][mShark.c].z < mShark.z) { // 기존 상어보다 현재 상어가 크다면 
						map[mShark.r][mShark.c] = new Shark(mShark.r, mShark.c, mShark.s, mShark.d, mShark.z); // 현재 상어 넣어줌 
					} 
				} else { // 없다면 현재 상어 바로 넣어줌 
					map[mShark.r][mShark.c] = new Shark(mShark.r, mShark.c, mShark.s, mShark.d, mShark.z);
				}
			}
		} 

		System.out.println(answer);
	}
}

