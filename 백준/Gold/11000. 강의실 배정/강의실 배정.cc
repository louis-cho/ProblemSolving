#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int N, s, e;
vector<pair<int, int>> list;
priority_queue<int, vector<int>, greater<int>> pq;

int main(void)
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> s >> e;
        list.push_back({s, e});
    }

    sort(list.begin(), list.end());

    pq.push(list[0].second);    // 첫번째 수업 종료 시각 삽입
    for (int i = 1; i < N; i++) // 이후 수업에 대해
    {
        pq.push(list[i].second);       // 종료 시각 추가
        if (pq.top() <= list[i].first) // 가장 먼저 끝나는 녀석이 이후 수업 시작보다 빨리 끝난다면?
        {
            pq.pop(); // 강의실 방 빼기
        }
    }

    cout << pq.size(); // 필요한 강의실 갯수
    return 0;
}