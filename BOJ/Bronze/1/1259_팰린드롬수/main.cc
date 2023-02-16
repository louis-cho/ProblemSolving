#include<iostream>
using namespace std;

string s;

int main(void)
{
    while(true) {
        cin >> s;
        if(s == "0") {
            break;
        }

        int start = 0, end = s.size() - 1;
        for (int i = 0; i <= end / 2; ++i) {
            if(s[start] == s[end]) {
                start++;
                end--;
            }
            else {
                break;
            }
        }

        if(start >= end) {
            cout << "yes\n";
        }
        else {
            cout << "no\n";
        }
    }
}