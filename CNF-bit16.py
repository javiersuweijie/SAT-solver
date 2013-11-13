def XORgenerate(a,b):
  return "("+a+"v"+b+")^("+"~"+a+"v"+"~"+b+")"

#print xorGenerate("A0","B0")

def ANDgenerate(a,b):
  return "("+a+"^"+b+")"

P0 = XORgenerate("A0","B0")
G0 = ANDgenerate("A0","B0")
def PGgenerate(n):
  P=[]
  G=[]
  for i in range(0,n):
    A = "A"+"%i"%i
    B = "B"+"%i"%i
    P.append(XORgenerate(A,B))
    G.append(ANDgenerate(A,B))
  return (P,G)

(P,G) = PGgenerate(17)
#print P
#print G

def PraGaGenerate():
  Pra = []
  Ga = []
  for i in range(0,15):
    if i%2 == 1:
      print i
      Pra.append(ANDgenerate(P[i-1],P[i]))
      Ga.append(ANDgenerate(G[i-1],P[i])+"v"+G[i])
    else:
      Pra.append('nothing')
      Ga.append('nothing')
  return Pra,Ga

Pra,Ga = PraGaGenerate()
#print Pra[0]
#print Ga[3]


