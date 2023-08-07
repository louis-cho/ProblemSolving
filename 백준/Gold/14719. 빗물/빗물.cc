#include <iostream>
#include <algorithm>
using namespace std;

int H, W;
int area[502];
int dp[502][502];
int sum = 0;

int main(void)
{
    cin >> H >> W;
    for (int i = 1; i <= W; i++)
    {
        cin >> area[i];
        dp[i][i] = area[i];
    }

    for (int i = 1; i <= W; i++)
    {
        for (int j = i; j <= W; j++)
        {
            dp[i][j] = max({dp[i][j - 1], area[j]});
        }
    }

    for (int i = 2; i <= W - 1; i++)
    {
        if (min(dp[1][i - 1], dp[i + 1][W]) > area[i])
            sum += min(dp[1][i - 1], dp[i + 1][W]) - area[i];
    }

    cout << sum;

    return 0;
}