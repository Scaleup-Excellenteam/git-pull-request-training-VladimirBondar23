def surprise(func):
    """Decorator that replaces the function's behavior with printing 'surprise!'."""
    def wrapper(*args, **kwargs):
        print("surprise!")
    return wrapper

# Example usage:
if __name__ == "__main__":
    @surprise
    def example_function():
        print("This is the original function.")

    # Calling the function will now print "surprise!" instead of the original function's print
    example_function()