// https://www.acmicpc.net/problem/1436
/**
 * 666이 연속적으로 들어간 숫자 구하기
 * 10000 이하의 수에 대해 계산하는 것이므로
 * 직접 모두 해보자.
*/

#include<iostream>
#include<string>
using namespace std;

int N;
string s;

int main(void) {
    cin >> N;

    int start = 666;

    while(true) {
        s = to_string(start);
        if(s.find("666") != string::npos) {
            N--;
            if(N == 0)
                break;
        }

        start++;
    }

    cout << start;
}