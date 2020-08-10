#include <bits/stdc++.h>

using namespace std;

int main() {
    vector<int> a = {1, 3, -1, 4, 1000, 5, 20, 30};
    vector<int> aux(a.size());
    function<void(int, int)> merge_sort;
    merge_sort = [&](int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        merge_sort(lo, mid);
        merge_sort(mid + 1, hi);
        for (int k = lo; k <= hi; ++k) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] <= aux[j]) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    };
    merge_sort(0, a.size() - 1);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
