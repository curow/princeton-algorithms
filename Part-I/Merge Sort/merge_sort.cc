#include <bits/stdc++.h>

using namespace std;

void merge_sort(vector<int> &a, int lo, int hi) {
    if (lo >= hi) return;
    int mid = lo + (hi - lo) / 2;
    merge_sort(a, lo, mid);
    merge_sort(a, mid + 1, hi);
    vector<int> aux(a.begin(), a.end());
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; ++k) {
        if (i > mid) a[k] = aux[j++];
        else if (j > hi) a[k] = aux[i++];
        else if (a[i] <= a[j]) a[k] = aux[i++];
        else a[k] = aux[j++];
    }
}

int main() {
    vector<int> a = {1, 3, -1, 4, 1000, 5, 20, 30};
    merge_sort(a, 0, a.size() - 1);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
