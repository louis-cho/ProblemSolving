#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, num, zeroCnt = 0, oneCnt = 0;
vector<int> negative, positive;

int main(void)
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> num;

        if (num < 0)
            negative.push_back(num);
        else if (num == 0)
            zeroCnt++;
        else if (num == 1)
            oneCnt++;
        else
            positive.push_back(num);
    }

    sort(negative.begin(), negative.end(), greater<int>());
    sort(positive.begin(), positive.end(), less<int>());

    int answer = 0;
    int i;

    if (positive.size() % 2 == 0) // positive 갯수가 짝수인 경우
    {
        for (i = positive.size() - 1; i > 0; i -= 2)
        {
            answer += positive[i] * positive[i - 1];
        }
    }
    else // 홀수인 경우
    {
        for (i = positive.size() - 1; i > 0; i -= 2)
        {
            answer += positive[i] * positive[i - 1];
        }
        answer += positive[0];
    }
    if (negative.size() % 2 == 0) // negative 갯수가 짝수인 경우
    {
        for (i = negative.size() - 1; i > 0; i -= 2)
        {
            answer += negative[i] * negative[i - 1];
        }
    }
    else // 홀수인 경우
    {
        for (i = negative.size() - 1; i > 0; i -= 2)
        {
            answer += negative[i] * negative[i - 1];
        }
        answer += (zeroCnt > 0 ? 0 : negative[0]);
    }

    answer += oneCnt;

    cout << answer;
    return 0;
}