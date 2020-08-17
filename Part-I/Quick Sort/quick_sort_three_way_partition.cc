#include <bits/stdc++.h>

using namespace std;

// Need random shuffle before calling quick sort
void quick_sort(vector<int> &a, int lo, int hi) {
    if (lo >= hi) return;
    int v = a[lo];
    int lt = lo, i = lo + 1, gt = hi;
    while (i <= gt) {
        if (a[i] < v) swap(a[i++], a[lt++]);
        else if (a[i] > v) swap(a[i], a[gt--]);
        else ++i;
    } // Now a[lo..lt-1] < a[lt..gt] < a[gt+1..hi]
    quick_sort(a, lo, lt - 1);
    quick_sort(a, gt + 1, hi);
}

int main() {
    vector<int> a = {1, 3, -1, 4, 1000, 5, 20, 30};
    random_shuffle(a.begin(), a.end());
    quick_sort(a, 0, a.size() - 1);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
