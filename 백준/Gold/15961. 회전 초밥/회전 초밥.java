import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, d, k, c, left, right, answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] list = new int[N + k + 1];
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < k; i++) {
			list[i + N] = list[i];
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(c, 1); // k개 먹어서 c를 공짜로 먹는 경우
		for (; right < k; right++) {
			map.put(list[right], map.getOrDefault(list[right], 0) + 1); // 먹을 수 있는 초밥 종류 최대수 저장
		}

		for (; left < N; left++, right++) // 좌우측 한 칸씩 땡기며 슬라이딩 윈도우
		{
			if (map.getOrDefault(list[left], 0) > 1)
				map.put(list[left], map.get(list[left]) - 1); // list[left]에 있는 초밥을 가지고 있다면 하나 줄이기
			else
				map.remove(list[left]); // 더 이상 list[left]의 초밥이 남지 않은 경우

			map.put(list[right], map.getOrDefault(list[right], 0) + 1); // right에서 가져온 새로운 초밥 번호

			answer = Math.max(answer, map.size()); // 선택한 종류의 개수
		}

		System.out.println(answer);
	}
}
