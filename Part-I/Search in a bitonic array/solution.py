def binary_search(lst, lo, hi, key):
    if lo > hi:
        return -1
    while lo <= hi:
        mid = (lo + hi) / 2
        if lst[mid] > key:
            hi = mid - 1
        else if lst[mid] < key:
            lo = mid + 1
        else:
            return mid
    return -1

def bitonic_search(lst, lo, hi, key):
    if hi - lo + 1 < 3:
        for i in range(lo, hi + 1):
            if lst[i] == key:
                return i
        return -1
    mid = (hi + lo) / 2
    if lst[mid] > lst[mid - 1] and lst[mid] > lst[mid + 1]:
        if lst[mid] < key:
            return -1
        else if lst[mid] > key:
            x = binary_search(lst, lo, mid - 1)
            if x != -1:
                return x
            else:
                return binary_search(lst, mid + 1, hi)

        else:
            return mid
    else if lst[mid] > lst[mid - 1]:
        if lst[mid] < key:
            return bitonic_search(lst, mid + 1, hi);
        else if lst[mid] > key:
            x = binary_search(lst, lo, mid - 1)
            if x != -1:
                return x
            else:
                return bitonic_search(lst, mid + 1, hi)
        else:
            return mid
    else:
        if lst[mid] < key:
            return bitonic_search(lst, lo, mid - 1)
        else if lst[mid] > key:
            x = binary_search(lst, mid + 1, hi)
            if x != -1:
                return x
            else:
                return bitonic_search(lst, lo, mid - 1)
        else:
            return mid
