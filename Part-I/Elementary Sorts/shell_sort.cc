#include <bits/stdc++.h>

using namespace std;

void shell_sort(vector<int> &a) {
    int n = a.size();
    int h = 1;
    while (h < n / 3) h = 3 * h + 1;
    while (h >= 1) {
        for (int i = h; i < n; ++i) {
            for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                swap(a[j], a[j - h]);
            }
        }
        h /= 3;
    }
}

int main() {
    vector<int> a = {3, 4, 5, 1, 0, -1, 1000, 4, 8, 9, 3, 3, -100};
    shell_sort(a);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
