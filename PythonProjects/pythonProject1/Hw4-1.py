
def square(n):
    n_copy = n
    start = ''
    ending = ''

    while n!=0:
        quantity = 1 + (n-1) * 4
        print( start + quantity * '*' + ending)
        if n!=1:
            print(start + '*' + ((quantity-2) * ' ') + '*' + ending)
            start += '* '
            ending += ' *'
        n-=1
    n = 1
    while n!=n_copy:
        n+=1
        start = start[:-2]
        ending = ending[:-2]
        quantity = 1 + (n-1) * 4
        if n!=1:
            print(start + '*' + ((quantity - 2) * ' ') + '*' + ending)
        print(start + quantity * '*' + ending)
