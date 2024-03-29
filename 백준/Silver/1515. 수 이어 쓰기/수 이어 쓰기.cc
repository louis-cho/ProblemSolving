#include<iostream>
#include <cmath>
#include<string>
using namespace std;

#define DIGIT_LIMIT 5
string str;
int copiedNumber, currentNumber = 1, digitSize = 1, digits[DIGIT_LIMIT], digitIdx;

int main(void) {

	cin >> str;

	int strIdx = 0, strLen = str.size(), divid;
	while (strIdx < strLen)
	{
		copiedNumber = currentNumber;
		char ch = str.at(strIdx);

		divid = pow(10, DIGIT_LIMIT-1);
		digitSize = -1;
		// 각 자릿수 설정
		for (int i = 0; i < DIGIT_LIMIT; i++)
		{
			digits[i] = copiedNumber / (divid);
			if (digitSize < 0 && digits[i] > 0)
				digitSize = DIGIT_LIMIT - i;

			copiedNumber -= digits[i] * divid;
			divid /= 10;
		}

		// 일치하는 문자 개수만큼 건너뛰기
		for (int i = DIGIT_LIMIT -digitSize; i < DIGIT_LIMIT; i++)
		{
			if (ch == digits[i] + '0') {
				strIdx++;
				if (strIdx == strLen)
					break;
				ch = str.at(strIdx);
			}
		}

		currentNumber++;
	}

	cout << currentNumber-1;
}