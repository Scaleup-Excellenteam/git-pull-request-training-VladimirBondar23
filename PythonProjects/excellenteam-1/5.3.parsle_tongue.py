import re
import os

CHUNK_SIZE = 1024

def parsle_tongue(file_path):
    # Define a regex pattern to match secret messages
    message_pattern = re.compile(r'[a-z]{5,}!')

    def read_in_chunks(file):
        """Generator to read file in chunks."""

        while True:
            chunk = file.read(CHUNK_SIZE)
            if not chunk:
                break
            yield chunk

    with open(file_path, 'rb') as file:
        for chunk in read_in_chunks(file):
            # Decode the chunk as binary to string if possible (ignore non-ASCII chars)
            try:
                text = chunk.decode('ascii', errors='ignore')
                # Find and yield all secret messages in the chunk
                for match in message_pattern.findall(text):
                    yield match
            except UnicodeDecodeError:
                continue


if __name__ == '__main__':
    relative_path = "Notebooks/content/week05/resources/logo.jpg"
    full_path = os.path.abspath(relative_path)
    for message in parsle_tongue(full_path):
        print("Found secret message:", message)
