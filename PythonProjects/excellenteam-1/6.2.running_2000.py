import time

def running_2000(f, *args, **kwargs):
    start_time = time.time()
    f(*args, **kwargs)      #applied a function and counted the difference between end and start time
    end_time = time.time()
    diff = end_time - start_time
    return diff


if __name__ == '__main__':
    print(running_2000("Hi {name}".format, name="Bug"))