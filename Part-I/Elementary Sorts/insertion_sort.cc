#include <bits/stdc++.h>

using namespace std;

void insertion_sort(vector<int> &a) {
    int n = a.size();
    for (int i = 1; i < n; ++i) {
        for (int j = i; j >= 1 && a[j] < a[j - 1]; --j) {
            swap(a[j], a[j - 1]);
        }
    }
}

int main() {
    vector<int> a = {3, 4, 5, 1, 0, -1};
    insertion_sort(a);
    for (int x : a) cout << x << " ";
    cout << endl;
    return 0;
}
