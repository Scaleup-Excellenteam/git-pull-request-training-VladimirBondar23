


def sqr_gen(gen):
    for i in gen:
        yield i**2

def plus1_gen(gen):
    for i in gen:
        yield i+1

def mult2_gen(gen):
    for i in gen:
        yield i*2

def dup_gen(gen):
    for i in gen:
        for j in range(2):
            yield i

def integers_from(n):
    x = n
    while True:
        yield x
        x+=1

def pipeline(*gens):
    def generator(g):
        result = g
        for gen in gens:
            result = gen(result)
        return result
    return generator
