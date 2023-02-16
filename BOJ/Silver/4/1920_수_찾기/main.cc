// https://www.acmicpc.net/problem/1920
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N, M, Q;
vector<int> numList;
vector<int> queryList;
bool binary_search(int start, int end, int value);

int main(void)
{
    cin >> N;
    numList.assign(N, 0);
    for (int i = 0; i < N; ++i) {
        cin >> numList[i];
    }

    sort(numList.begin(), numList.end());

    cin >> M;
    queryList.assign(M, 0);

    for (int i = 0; i < M; ++i) {
        cin >> queryList[i];
    }
    for (int i = 0; i < M; ++i) {

        if (binary_search(numList.begin(), numList.end(), queryList[i]))
        {
            cout << "1\n";
        }
        else
        {
            cout << "0\n";
        }
    }
}

bool binary_search(int start, int end, int value) {
    int mid;
    while(start < end) {
        mid = (start + end) / 2;
        if(numList.at(mid) == value)
            return true;
        
        else {
            if(numList.at(mid) < value) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
    }

    return false;
}