# creating variables
name: str = 'Nick'
age: int = 30
isEngineer: bool = True
height: float = 1.75

l: list[int] = [1, 2, 3]
s: set[int] = {1, 2, 2, 3}
d: dict[str, int] = {'one': 1, 'two': 2, 'three': 3}
t: tuple[float, str, int] = (1.0, 'two', 3)

l_tuple: list[(int, str, bool)] = [(1, "miras", True), (2, "Ben", False)]
s_tuple: set[(int, str, bool)] = {(1, "miras", True), (2, "Ben", False)}
d_tuple: dict[(int, str, bool), int] = {(1, "miras", True): 10, (2, "Ben", False): 20}

l_list: list[list[list[int]]] = [[[1]], [[2, 3], [4]]]
print(l_list)