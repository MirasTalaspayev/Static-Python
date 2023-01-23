class Rational:
    __dividend: int
    __divisor: int
    def __init__(self, dividend: int, divisor: int):
        if divisor == 0:
            raise Exception("divisor can not be 0")
        _gcd: int = Rational.gcd(dividend, divisor)
        dividend //= _gcd
        divisor //= _gcd
        self.__dividend = dividend
        self.__divisor = divisor
        pass
    @staticmethod
    def __add__(self, other):
        a_dividend: int = self.get_dividend()
        a_divisor: int = self.get_divisor()
        b_dividend: int = other.get_dividend()
        b_divisor: int = other.get_divisor()
        if a_divisor == b_divisor:
            return Rational(a_dividend + b_dividend, a_divisor)
        _gcd: int = Rational.gcd(a_divisor, b_divisor)
        new_dividend: int = a_dividend * b_divisor // _gcd + b_dividend * a_divisor // _gcd
        new_divisor: int = a_divisor * b_divisor // _gcd
        return Rational(new_dividend, new_divisor)
    @staticmethod
    def __eq__(self, other) -> bool:
        if self.get_dividend() == other.get_dividend() and self.get_divisor() == other.get_divisor():
            return True
        return False        
    @staticmethod
    def gcd(x: int, y: int) -> int:
        if (y == 0): # it divide every number  
            return x  # return x  
        else:  
            return Rational.gcd(y, x % y)  
    def get_dividend(self) -> int:
        return self.__dividend
    def get_divisor(self) -> int:
        return self.__divisor
    def Print(self):
        print(self.__dividend, self.__divisor)
    pass