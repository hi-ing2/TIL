
#list
array = [1,2,3,4,5,6,7,'eight','nine', True, False]
print(array)
print(array[0:2])
print(array[3:])
print(array[-1])

numbers = list(range(0,10))
print(numbers)

#dictionary
dust = {'영등포구' : 50,'강남구' : 40}
print(dust['영등포구'])

dust_two = dict(abc=60, cdf=40)
print(dust_two)
print(dust_two['abc'])

#random
import random

loser = ['주현', '희수', '형인', '대현']
phone_book = {'주현' : '010', '희수' : '011', '형인' : '019', '대현' : '017'}

co = random.choice(loser)

print(co)
print(phone_book[co])
