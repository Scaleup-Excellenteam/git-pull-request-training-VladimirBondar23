def piece_of_cake(prices, optionals = None, **kwargs):
    total_sum = 0

    for key, value in prices.items():

        #calculate how many grams needed by dividing by 100

        grams_needed = kwargs.get(key,0)/100    #default value of 0 if key not provided in kwargs
        total_sum += value * grams_needed


    return int(total_sum)



if __name__ == '__main__':
    print(piece_of_cake({'chocolate': 18, 'milk': 8}, optionals=['milk'], chocolate=300))