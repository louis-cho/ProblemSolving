#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int V, E, u, v, w, answer = 0;
vector<pair<int, pair<int, int>>> graph;
int parent[10001];

int find_parent(int x)
{
    if (parent[x] == x)
        return x;
    return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y)
{
    x = find_parent(x);
    y = find_parent(y);

    if (x != y)
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

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> V >> E;
    for (int i = 1; i <= V; i++)
    {
        parent[i] = i;
    }

    for (int i = 0; i < E; i++)
    {
        cin >> u >> v >> w;
        graph.push_back({w, {u, v}});
    }

    sort(graph.begin(), graph.end());

    for (int i = 0; i < E; i++) // 모든 간선에 대해 수행해보기
    {
        if (find_parent(graph[i].second.first) != find_parent(graph[i].second.second)) // u vertex와 v vertex가 다른 부모에 속해있다면
        {
            union_parent(graph[i].second.first, graph[i].second.second); // u vertex와 v vertex 합집합 후
            answer += graph[i].first;                                    // 가중치 합산
        }
    }

    cout << answer << "\n";
}