for _ in range(int(input())):
    t = input().split()
    dogs = int(t[0])
    walkers = int(t[1])
    sizes = list()
    relative = list()
    for d in range(dogs):
        sizes.append(int(input()))
    sizes.sort()
    for d in range(1, dogs):
        relative.append(sizes[d] - sizes[d-1])
    relative.sort()
    print(sum(relative[:dogs-walkers]))
