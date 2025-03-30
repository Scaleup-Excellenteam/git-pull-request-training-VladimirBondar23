from datetime import datetime as dt, timedelta
import random


def no_vinnigrete():
    start_date = input("Enter the start date: ")
    end_date = input("Enter the end date: ")
    try:

    # Convert strings to datetime objects
        start = dt.strptime(start_date, '%Y-%m-%d')
        end = dt.strptime(end_date, '%Y-%m-%d')

    # Generate a random date
        delta = end - start
        random_days = random.randint(0, delta.days)
        random_date = start + timedelta(days=random_days)

        return random_date.strftime("%Y-%m-%d")
    except ValueError as e:
        print("Please enter a valid date ", e)






if __name__ == '__main__':
    print(no_vinnigrete())