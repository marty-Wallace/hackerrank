#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

int max(int a, int b){
    return a > b ? a : b;
}

int main() {
    ios_base::sync_with_stdio (false);
    int t;
    cin >> t;
    while(t-->0){
        int w, k;
        cin >> w >> k;
        int tbucket[26] {0};
        for(int i = 0; i < w; i++){
            string line;
            cin >> line;
            int lbucket[26] {0};
            for(char& c : line) {
                lbucket[(int) c - 'a']++;
            }
            for(int i = 0; i < 26; i++){
                tbucket[i] = max(tbucket[i], lbucket[i]);
            }
        }
        for(int i = 0; i < k; i++){
            string line;
            cin >> line;
            int kbucket[26] {0};
            for(char& c : line) {
                kbucket[(int) c - 'a']++;
            }
            int errCount = 0;
            int perfect = 1;
            for(int i = 0; i < 26; i++){
                if(tbucket[i] > kbucket[i]){
                    errCount += tbucket[i] - kbucket[i];
                }else if(tbucket[i] < kbucket[i]){
                    perfect = 0;
                }
            }
            string per = "No";
            if(perfect){
                per = "Yes";
            }
            if(errCount > 0){
                cout << "No " << errCount << endl;
            }else{
                cout << "Yes " << per << endl;
            }
            
        }
    }
    return 0;
}

