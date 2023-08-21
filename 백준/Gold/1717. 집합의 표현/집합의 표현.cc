#include <iostream>
using namespace std;

int n, m, a, b, cmd; // n: vertex 갯수, m: 간선 갯수, a: 입력 정점1, b: 입력 정점2, cmd: 연산 구분
int parent[1000001]; // parent[x]: x 정점이 속한 최상위 부모 (x,y 합집합 시 작은 수의 인덱스를 사용한다)

/**
 * 최상위 부모를 찾기 위한 함수
 * 주어진 x에 대해 parent 배열에서 찾아놓은 부모를 재귀적으로 찾아가며 검사함
 *
 * 최악의 시간복잡도 : O(N) 한쪽으로만 치우쳐져 있는 트리. 링크드 리스트 탐색과 같음
 * 평균 시간복잡도 : O(NlogN) 균형 잡혀 있는 트리의 경우 트리의 높이만큼 탐색
 */
int find_parent(int x)
{
    if (parent[x] == x) // 이미 최상위 노드가 자기 자신으로 설정된 경우
    {
        return x; // 자기 자신이 루트기 때문에 바로 반환
    }
    else // 부모 노드가 자기 자신이 아닌 경우
    {
        return parent[x] = find_parent(parent[x]); // 상위의 부모 노드가 있나 확인하여 업데이트
    }
}

void union_parent(int x, int y)
{
    x = find_parent(x);
    y = find_parent(y);

    if (x == y)
    {
        return;
    }
    else
    {
        if (x < y)
        {
            parent[y] = x;
        }
        else
        {
            parent[x] = y;
        }
    }
}

int main(void)
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        parent[i] = i;
    }
    for (int i = 0; i < m; i++)
    {
        cin >> cmd >> a >> b;
        if (cmd == 0)
        {
            if (a != b)
                union_parent(a, b);
        }
        else
        {
            if (a == b || find_parent(a) == find_parent(b))
            {
                cout << "YES\n";
            }
            else
            {
                cout << "NO\n";
            }
        }
    }
}