import string





def long_cat_is_long(txt):
    text = txt.lower().split()  #split the text and convert to lowercase
    text = list(map(lambda word: ''.join(char for char in word if char.isalpha()), text)) #remove non-alphabetic symbols
    word_count = {word : text.count(word) for word in text} #count occurences for each word

    return word_count
if __name__ == '__main__':
    text = """
    You see, wire telegraph is a kind of a very, very long cat.
    You pull his tail in New York and his head is meowing in Los Angeles.
    Do you understand this?
    And radio operates exactly the same way: you send signals here, they receive them there.
    The only difference is that there is no cat.
    """

    print(long_cat_is_long(text))