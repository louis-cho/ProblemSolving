// https://www.acmicpc.net/problem/1181
#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int N;
vector<string> wordList;

bool compare(const string& a, const string& b) {
    if(a.size() != b.size())
        return a.size() < b.size();
    else {
        int len = a.size();
        for (int i = 0; i < len; ++i) {
            if(a.at(i) != b.at(i))
                return a.at(i) < b.at(i);
        }
    }
}

int main(void)
{
    string word;
    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> word;
        if(find(wordList.begin(), wordList.end(), word) == wordList.end()) {
            wordList.push_back(word);
        }
    }

    sort(wordList.begin(), wordList.end(), compare);

    for (int i = 0; i < wordList.size(); ++i) {
        cout << wordList[i] << "\n";
    }

    return 0;
}