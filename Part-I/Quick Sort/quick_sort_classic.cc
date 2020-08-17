#include <bits/stdc++.h>

using namespace std;

void quick_sort(vector<int> &a, int lo, int hi) {
    if (lo >= hi) return;
    int v = a[lo];
    int i = lo, j = hi + 1;
    while (true) {
        while (a[++i] < v) if (i == hi) break;
        while (a[--j] > v) if (j == lo) break;
        if (i >= j) break;
        swap(a[i], a[j]);
    }
    swap(a[lo], a[j]);
    quick_sort(a, lo, j - 1);
    quick_sort(a, j + 1, hi);
}

int main() {
    vector<int> a = {1, 3, -1, 4, 1000, 5, 20, 30};
    random_shuffle(a.begin(), a.end());
    quick_sort(a, 0, a.size() - 1);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
