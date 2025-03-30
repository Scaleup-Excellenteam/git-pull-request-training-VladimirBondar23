def cup_of_join(*lists, sep = '-'):
    result = []

    for list in lists:  #append lists including the separator in the beginning
        result.append(sep)
        result += list

    return result[1:] if len(result) !=0 else None  #return not including the first separator or None if it's empty



if __name__ == '__main__':
    print(cup_of_join([1, 2], [8], [9, 5, 6]))