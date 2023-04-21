# LIST
lists_concatanation = [] + []

list_repetition_1 = [1, 2] * 2 # [1,2,1,2]
list_repetition_2 = 5 * []

list_equals = [] == [] # all comparisons go element-wise
list_not_equal = [] != []
list_gt = [] > []
list_gt_equal = [] >= []
list_less = [] < []
list_less_equal = [] <= []

# SET
set_union = {} | {}
set_intersection = {} & {}
set_difference = {} - {}
set_XOR = {} ^ {}

set_equals = {} == {} # checks if have the same elements
set_not_equal = {} != {} 
set_gt = {} > {} # if left expression has all values of right expression and one more -> True
set_gt_equal = {} >= {} 
set_less = {} < {}
set_less_equal = {} <= {}

# DICT
dict_equals = {} == {}
dict_not_equal = {} != {}

# Tuple
tuple_concatanation = () + ()
tuple_repetition_1 = () * 5
tuple_repetition_2 = 5 * ()

tuple_equals = [] == [] # all comparisons go element-wise
tuple_not_equal = [] != []
tuple_gt = [] > []
tuple_gt_equal = [] >= []
tuple_less = [] < []
tuple_less_equal = [] <= []

# All Collections
membership_1 = 2 in [1, 2, 3] # could be any collection
membership_2 = 2 in {1: 10, 2: 20, 3: 30} # checks for key
