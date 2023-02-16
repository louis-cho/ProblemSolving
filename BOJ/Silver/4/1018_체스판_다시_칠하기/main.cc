#include<iostream>
#include<algorithm>
using namespace std;

int N, M;
char tmp;
bool map[50][50];
int correctlyPainted[2];
int maxPainted = -1;


int main(void)
{
    cin >> N >> M;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            cin >> tmp;
            if(tmp == 'W') {
                map[i][j] = true;
            }
            else {
                map[i][j] = false;
            }
        }
    }

    for (int k = 0; k <= N - 8; ++k) {
        for (int l = 0; l <= M - 8; ++l) {
            correctlyPainted[0] = 0;
            correctlyPainted[1] = 0;
            for (int i = 0; i < 8; ++i)
            {
                for (int j = 0; j < 8; ++j)
                {
                    if ((i + j) % 2 == 0)
                    {
                        if (map[i+k][j+l] == true)
                        {
                            correctlyPainted[0]++;
                        }
                        else
                        {
                            correctlyPainted[1]++;
                        }
                    }
                    else
                    {
                        if (map[i+k][j+l] == true)
                        {
                            correctlyPainted[1]++;
                        }
                        else
                        {
                            correctlyPainted[0]++;
                        }
                    }
                }
            }

            maxPainted = max({maxPainted, correctlyPainted[0], correctlyPainted[1]});
        }
    }

    cout << 64 - maxPainted;
}