def communicating_vessels(*iterables):
    zipped = list(zip(*iterables))      #zip the lists

    # extract elements from tuples and assemble a list
    return [tup[i] for tup in zipped for i in range(len(tup))]


def interleave_generator(*iterables):
    # Here iterating over zip directly
    for tup in zip(*iterables):
        for item in tup:
            yield item

if __name__ == '__main__':
    print(communicating_vessels('abc', [1, 2, 3], ('!', '@', '#')))
    gen_version = interleave_generator('abc', [1, 2, 3], ('!', '@', '#'))
    for element in gen_version:
        print(element)