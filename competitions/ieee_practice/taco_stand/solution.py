# Enter your code here. Read input from STDIN. Print output to STDOUT
import math
for _ in range(int(raw_input())):
    line = raw_input().split(" ")
    shells = int(line[0])
    meat = int(line[1])
    rice = int(line[2])
    beans = int(line[3])
    x = min(meat, rice, beans)
    y = max(min(meat,rice), min(max(meat,rice),beans))
    z = max(meat, rice, beans)
    if z > x+y:
        print min(shells, x+y)
    else:
        print min(shells, int(math.floor((x+y+z)/2)))
