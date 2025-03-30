import os
from PIL import Image
import numpy as np
def remember_remember(image_path):
    image = Image.open(image_path)

    # Convert the image to a numpy array
    image_matrix = np.array(image)

    # Initialize an empty string to store the decoded message
    decoded_message = ""

    # Iterate through the columns of the image
    for col in range(image_matrix.shape[1]):
        # Find the row where the pixel is black
        for row in range(image_matrix.shape[0]):
            if image_matrix[row, col] == 1:  # Black pixel (value 1)
                # Convert the row number to a character and add to the message
                decoded_message += chr(row)
                break

    return decoded_message



if __name__ == '__main__':
    relative_path = "Notebooks/content/week06/resources/code.png"  # relative path
    full_path = os.path.abspath(relative_path)  # Convert to absolute path
    print(remember_remember(full_path))