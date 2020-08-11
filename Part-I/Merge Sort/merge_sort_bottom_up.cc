#include <bits/stdc++.h>

using namespace std;

int main() {
    vector<int> a = {1, 3, -1, 4, 1000, 5, 20, 30};
    const int n = a.size();
    vector<int> aux(n);
    auto merge = [&](int lo, int mid, int hi) -> void {
        if (a[mid] <= a[mid + 1]) return;
        for (int k = lo; k <= hi; ++k) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] <= aux[j]) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    };
    for (int k = 1; k < n; k *= 2) {
        for (int lo = 0; lo + k - 1 < n - 1; lo += 2 * k) {
            int mid = lo + k - 1, hi = min(mid + k, n - 1);
            merge(lo, mid, hi);
        }
    }
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
