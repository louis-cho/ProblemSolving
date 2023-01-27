#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

#define MAX 501

int dp[MAX][MAX] = {0};

int solution(vector<vector<int>> triangle) 
{
    int answer = 0;
    int depth = triangle.size();
    dp[0][0] = triangle[0][0];


    for (int i = 1; i < depth; ++i)
    {
        dp[i][0] = dp[i - 1][0] + triangle[i][0];

        for (int j = 1; j < i; ++j)
        {
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
        }


        dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
    }

    for (int i = 0; i < depth; ++i) {
        if(answer < dp[depth-1][i])
            answer = dp[depth-1][i];
    }

    return answer;
}

int main(void)
{
    vector<vector<int>> triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
    cout << solution(triangle);
}