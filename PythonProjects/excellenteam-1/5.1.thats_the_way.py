import os


def thats_the_way(directory):

    # Check if the directory exists
    if not os.path.exists(directory):
        print(f"Error: Directory '{directory}' does not exist.")
        return []

    # List files that start with "deep"
    return [file for file in os.listdir(directory) if file.startswith('deep')]


if __name__ == '__main__':
    relative_path = "Notebooks/content/week05/images"  # relative path
    full_path = os.path.abspath(relative_path)  # Convert to absolute path
    print(thats_the_way(full_path))