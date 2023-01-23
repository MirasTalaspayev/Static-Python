class MyClass:
    def __init__(self, a):
        self.a = a
    
    # Binary operators
    def __add__(self, other):
        return self.a + other.a
    
    # Comparison operators
    def __gt__(self, other):
        if(self.a>other.a):
            return True
        else:
            return False

    # Assignment operators
    def __iadd__(self, other):
        self.a = self.a + other.a
        return self.a

    # Unary operators
    def __neg__(self):
        self.a = self.a * (-1)
        return self.a

    # ... and other operators

    # Notes
    def __radd__(other: MyClass): # other + self
        return