"""

Program ini dibuat karena keinginan untuk mengCALLBACK skill python ku yang sudah nganggur 
kurang lebih 6 bulanan lebih.

"""


# harga   =  float(input("Masukkan harga awal: "))
# diskon  =  float(input("Masukkan diskon (dalam %): "))

# harga -= harga * diskon / 100

# print("harga akhir setelah diskon: " + str(harga))
# print(f"harga setelah diskon {harga:,.2f}")
# print(type(diskon)) #  mengetahui type dari sebuah variabel


# STR + STR
print("hello", "world")


# global variable value
x = "awesome"
def myFunc():
    global x
    x = "fantastic"
myFunc()
print("python is " + x)

# Python data types 
x = "sad"
print(type(x)) # str

x = ["apple", "banana", "cherry"]
print(type(x)) # list

x = ("apple", "banana", "cherry")
print(type(x)) # tuple

x = {"name" : "agus" , "age" : 40 , "status" : "undergraduate"}
print(type(x)) #dict

x = True
print(type(x)) # bool

# empty list
x = []
print(x)

# list with initial value
y = [1,2,3,4,5]
print(y)

# List with mixed types
z = [1, "hello", 3.14, True]
print(z)

'''
belajar dikit buat SDA
'''

# algoritma custom untuk mencari nilai terendah
myArray = [12, -2, 34, 534, 10, 3, 0]
minVal = myArray[0]

for i in myArray:
    if i < minVal:
        minVal = i

print("lowest value", minVal)

# algoritma custom untuk mencari nilai tertinggi
myArray = [12, -2, 34, 534, 10, 3, 0]
minVal = myArray[0]

for i in myArray:
    if i > minVal:
        minVal = i

print("highest value", minVal)

# Stack Implementation using Linked Lists
class Node:
    def __init__(self, value):
        self.value = value
        self.next = None

class Stack:
    def __init__(self):
        self.head = None
        self.size = 0

    def push(self, value):
        new_node = Node(value)
        if self.head:
            new_node.next = self.head
        self.head = new_node
        self.size += 1

    def pop(self):
        if self.isEmpty():
            return "stack is empty"
        popped_node = self.head
        self.head = self.head.next
        self.size -= 1
        return popped_node.value
    
    def peek(self):
        if self.isEmpty():
            return "Stack is empty"
        return self.head.value

    def isEmpty(self):
        return self.size == 0

    def stackSize(self):
        return self.size

    def traverseAndPrint(self):
        currentNode = self.head
        while currentNode:
            print(currentNode.value, end=" -> ")
            currentNode = currentNode.next
        print()


myStack = Stack()
myStack.push("a")
myStack.push("b")
myStack.push("c")

print("LinkedList: ", end="")
myStack.traverseAndPrint()
print("Peek: ", myStack.peek())
print("Pop: ", myStack.pop())
print("LinkedList after Pop: ", end="")
myStack.traverseAndPrint()
print("isEmpty: ", myStack.isEmpty())
print("Size: ", myStack.stackSize())
