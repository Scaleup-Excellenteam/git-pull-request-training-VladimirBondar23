def gen_partitions(n,k):
    if (n == 0 and k == 1) or (n != 0 and k == 0):
        return
    if k == 0:
        yield []
        return
    if k == 1:
        yield [n]
        return
    st = set()
    for x in range(1, n - k + 2):
        for rest in gen_partitions(n - x, k - 1):
            partition = [x] + rest
            if tuple(sorted(partition)) not in st:
                st.add(tuple(sorted(partition)))
                yield partition



