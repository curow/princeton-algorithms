#include <bits/stdc++.h>

using namespace std;

void knuth_shuffle(vector<int> &a) {
    int n = a.size();
    for (int i = 0; i < n; ++i) {
        int j = rand() % (n - i) + i;
        swap(a[i], a[j]);
    }
}

int main() {
    srand((unsigned) time(NULL));
    vector<int> a = {1, 2, 3, 4, 5, 6, 7};
    int n = 100;
    while (n--) {
        knuth_shuffle(a);
        for (int x : a) cout << x << " ";
        cout << endl;
    }
    return 0;
}
