// Demos01.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <vector>

template <typename E>
class Stack
{
private:
	E* elements;
	int top;
	int capacity;

public:
	Stack(int size = 10) : capacity(size), top(-1) {
		elements = new E[capacity];
	}
	~Stack() {
		delete[] elements;
	}
	void push(E element) {
		if (top >= capacity - 1) {
			std::cout << "Overflow" << std::endl;
			return;
		}
		top++;
		elements[top] = element;
	}
	E pop() {
		if (isEmpty()) {
			return E();
		}

		return elements[top--];
	}

	bool isEmpty() {
		return top == -1;
	}

};

static void TestVector() {
	std::vector<int> v1 = { 1,2,3,4,5,6 };
	v1.push_back(7);
	v1.push_back(8);
	std::cout << "First: " << v1.front() << std::endl;
	std::cout << "Last: " << v1.back() << std::endl;
	std::vector<int>::iterator itr =  v1.begin();
	int val = *itr;

	for (auto it = v1.begin(); it!= v1.end(); it++)
	{
		std::cout << *it << std::endl;
	}
	
}

template <typename T>
T GenericAdd(T a, T b) {
	return a + b;
}


static void TestStack() {


	Stack<std::string> stack2;
	stack2.push("XXX");
	stack2.push("YYY");
	stack2.push("ZZZ");
	std::string resString = stack2.pop();
}

int main()
{
	int res = GenericAdd<int>(5, 3);
	int res2 = GenericAdd(10, 20);
	float res3 = GenericAdd(3.5f, 4.2f);
	
	TestVector();
}

