import matplotlib.pyplot as plt

f = open("GaussRandNumbersTxt.txt", "r")
simpleMap = []
iterator = 0

for line in f:
    simpleMap.append(float(line.replace(',', '.')))
    iterator = iterator + 1

fig, ax = plt.subplots()
ax.hist(simpleMap, 50, density=True, alpha=0.911)
plt.show()
    
f.close()
