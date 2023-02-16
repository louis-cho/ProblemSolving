// https://www.acmicpc.net/problem/1085
#include<iostream>
using namespace std;

int w, h, x, y, answer;

int main(void) {
    cin >> x >> y >> w >> h;
    if(x <= w/2) {
        answer = x;
    }
    else {
        answer = w - x;
    }

    if(y <= h/2) {
        if(answer > y) {
            answer = y;
        }
    }
    else {
        if(answer > (h - y)) {
            answer = h - y;
        }
    }

    cout << answer;
}