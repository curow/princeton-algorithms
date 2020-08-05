#include <bits/stdc++.h>

using namespace std;

void selection_sort(vector<int> &a) {
    int n = a.size();
    for (int i = 0; i < n; ++i) {
        int k = i;
        for (int j = i + 1; j < n; ++j) {
            if (a[j] < a[k]) {
                k = j;
            }
        }
        swap(a[i], a[k]);
    }
}

int main() {
    vector<int> a = {3, 4, 5, 1, 0, -1};
    selection_sort(a);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
