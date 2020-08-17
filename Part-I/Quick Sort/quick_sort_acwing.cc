#include <bits/stdc++.h>

using namespace std;

void quick_sort(vector<int> &a, int lo, int hi) {
    if (lo >= hi) return;
    int v = a[lo + rand() % (hi - lo)];
    int i = lo - 1, j = hi + 1;
    while (true) {
        do ++i; while (a[i] < v);
        do --j; while (a[j] > v);
        if (i >= j) break;
        swap(a[i], a[j]);
    }
    quick_sort(a, lo, j);
    quick_sort(a, j + 1, hi);
}

int main() {
    vector<int> a = {3, 2, 2, 3, 3, 2, 2, 1, 1};
    quick_sort(a, 0, a.size() - 1);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
