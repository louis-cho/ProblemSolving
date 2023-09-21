#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, num;
vector<int> list;
int answer = 2e9;

int main(void)
{
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        cin >> num;
        list.push_back(num);
    }

    sort(list.begin(), list.end());
    int start = 0;
    int end = 0;

    while (start < N && end < N)
    {
        int diff = list[end] - list[start];
        if (diff >= M)
        {
            answer = min(answer, diff);
            start++;
        }
        else
            end++;
    }
    cout << answer;
    return 0;
}
