def analizeFile(filename, n=None):
    strategy_counts = {}
    line_count = 0

    with open(filename, 'r') as file:
        for line in file:
            if n is not None and line_count >= n:
                break

            row = line.strip().split(",")
            if len(row) >= 5:
                strategy_field = row[4].strip()
                if strategy_field.startswith("Strategy"):
                    strategy_number = int(strategy_field.replace("Strategy", ""))
                    if strategy_number in strategy_counts:
                        strategy_counts[strategy_number] += 1
                    else:
                        strategy_counts[strategy_number] = 1
            line_count += 1

    result = []
    for strategy, count in sorted(strategy_counts.items()):
        result.append((strategy, count))

    minimal_bits = count_minimal_bits(result)

    return minimal_bits[0], minimal_bits[1],result


def count_minimal_bits(result):
    minimal_bits = 1000
    best_binary = []

    for i in range(1,16):
        valid = True
        for number, _ in result:
            if i & number == 0:
                valid = False
                break
        if not valid:
            continue
        else:
            if i.bit_count() < minimal_bits:
                minimal_bits = i.bit_count()
            best_binary.append(i)

    best_binary = list(filter(lambda num: num.bit_count() == minimal_bits, best_binary))
    return minimal_bits, best_binary

