def group_by(func, iterable):
    set_of_results = set(map(func, iterable))   #making a set of results (to avoid duplicates)

    # iterate over all results in a set and apply a filter to the original iterable
    # to find all elements which satisfy the result
    return {result: list(filter(lambda element: func(element) == result, iterable)) for result in set_of_results}



if __name__ == '__main__':
    print(group_by(len, ["hi", "bye", "yo", "try"]))