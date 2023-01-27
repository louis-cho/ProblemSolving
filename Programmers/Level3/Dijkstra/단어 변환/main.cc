#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;
#define MAX 52
bool adj[MAX][MAX];
int dist[MAX];
bool visited[MAX];

int lastIdx = -1;
int N;

void Dijkstra();

int getSmallestNode()
{
    int minIdx = -1;
    int minDist = 1e9;

    for (int i = 1; i<N; ++i)
    {
        if(visited[i])
            continue;
        
        if(dist[i] < minDist)
        {
            minDist = dist[i];
            minIdx = i;
        }
    }

    return minIdx;
}

void makeGraph(string begin, string target, vector<string> words)
{

    int wordLen = begin.size();
    int numOfWords = words.size();

    for (int i = 0; i < numOfWords; ++i)
    {
        for (int j = 0; j < numOfWords; ++j)
        {
            if (i == j)
            {
                continue;
            }

            int cnt = 0;
            for (int n = 0; n < wordLen; ++n)
            {
                if (words[i].at(n) != words[j].at(n))
                {
                    cnt++;
                    if (cnt > 1)
                        break;
                }
            }

            if (cnt == 1)
            {
                adj[i + 1][j + 1] = true;
                adj[j + 1][i + 1] = true;
            }
        }

        int beginCheck = 0;
        int targetCheck = 0;
        for (int n = 0; n < wordLen; ++n)
        {
            if (words[i].at(n) != begin.at(n))
            {
                beginCheck++;
            }
        }

        if (beginCheck == 1)
        {
            adj[0][i + 1] = true;
            adj[i + 1][0] = true;
        }

        if (target == words[i])
        {
            lastIdx = i + 1;
        }
    }
}

int solution(string begin, string target, vector<string> words)
{
    int answer = 0;

    N = words.size() + 1;

    fill(adj[0], adj[N], false);
    makeGraph(begin, target, words);
    fill(dist, dist + MAX, 1e9);
    fill(visited, visited + MAX, false);

    Dijkstra();

    if (lastIdx != -1)
        answer = dist[lastIdx];
    if (answer >= 1e9)
        answer = 0;

    return answer;
}

void Dijkstra() {

    for (int i = 1; i<N; ++i)
    {
        if(adj[0][i] == true)
            dist[i] = adj[0][i];
    }

    dist[0] = 0;
    visited[0] = true;

    for (int i = 0; i <= N; ++i) {
        int newNode = getSmallestNode();
        visited[newNode] = true;

        for (int j = 0; j <= N; ++j) {
            if(visited[j])
                continue;
            if(adj[newNode][j] && dist[j] > dist[newNode] + adj[newNode][j])
                dist[j] = dist[newNode] + adj[newNode][j];
        }
    }
}

int main(void)
{
    string begin = "hit";
    string target = "cog";
    vector<string> words = {"hot", "log", "lot", "dog", "dot", "cog"};

    cout << solution(begin, target, words);
}