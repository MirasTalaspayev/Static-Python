class Stack():
    __size : int
    __capacity : int

    __entities : list[int]

    def __init__(self, capacity : int):
        if capacity < 4:
            capacity = 4
        self.__capacity = capacity
        self.__size = 0

        self.__entities = []
    
    def push(self, item : int):
        if self.__size == self.__capacity:
            __capacity = 2 * __capacity
        self.__entities.append(item)
        self.__size += 1
    
    def pop(self):
        if self.__size < 1:
            print("stack is empty")
            return
        self.__size -= 1
        return self.__entities.pop()

    def peek(self):
        if self.__size < 1:
            print("stack is empty")
            return
        return self.__entities[self.__size - 1]
        
    def getSize(self):
        return self.__size


# testing
stack : Stack = Stack(3)

stack.push(2)
stack.pop()
stack.push(3)
stack.push(3)

while stack.getSize() > 0:
    print(stack.pop())