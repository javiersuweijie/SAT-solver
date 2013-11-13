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
  for i in range(0,16):
    if i%2 == 1:
      Pra.append(ANDgenerate(P[i-1],P[i]))
      Ga.append(ANDgenerate(G[i-1],P[i])+"v"+G[i])
    else:
      Pra.append('nothing')
      Ga.append('nothing')
  return Pra,Ga

Pra,Ga = PraGaGenerate()
#print Pra[0]
#print Ga[3]

def PrbGbGenerate():
  Prb=[]
  Gb=[]
  for i in range(0,16):
    if i%4 == 3:
      Prb.append(ANDgenerate(Pra[i-2],Pra[i]))
      Gb.append(ANDgenerate(Ga[i-2],Pra[i])+"v"+Ga[i])
    else:
      Prb.append("nothing")
      Gb.append("nothing")
  return Prb,Gb

Prb,Gb = PrbGbGenerate()
#print Prb[15]
#print Gb[15]

def PrcGcGenerate():
  Prc=[]
  Gc=[]
  for i in range(0,16):
    if i%8==7:
      Prc.append(ANDgenerate(Prb[i-4],Prb[i]))
      Gc.append(ANDgenerate(Gb[i-4],Prb[i])+"v"+Gb[i])
    else:
      Prc.append("nothing")
      Gc.append("nothing")
  return Prc,Gc

Prc,Gc = PrcGcGenerate()
#print Gc[15]

def PrdGdGenerate():
  Prd=[]
  Gd=[]
  for i in range(0,16):
    if i==15:
      Prd.append(ANDgenerate(Prc[i-8],Prc[i]))
      Gd.append(ANDgenerate(Gc[i-8],Prc[i])+"v"+Gc[i])
    else:
      Prd.append("nothing")
      Gd.append("nothing")
  return Prd,Gd

Prd,Gd = PrdGdGenerate()

def GeGenerate():
  return "(Cin)"+"v"+ANDgenerate(Prb[15],Gd[15])

S16 = XORgenerate(GeGenerate(),XORgenerate("A16","B16"))

from sympy.logic.boolalg import to_cnf
from sympy import *


A0 = symbols('A0')
A1 = symbols('A1')
A2 = symbols('A2')
A3 = symbols('A3')
A4 = symbols('A4')
A5 = symbols('A5')
A6 = symbols('A6')
A7 = symbols('A7')
A8 = symbols('A8')
A9 = symbols('A9')
A10 = symbols('A10')
A11 = symbols('A11')
A12 = symbols('A12')
A13 = symbols('A13')
A14 = symbols('A14')
A15 = symbols('A15')
A16 = symbols('A16')

B0 = symbols('B0')
B1 = symbols('B1')
B2 = symbols('B2')
B3 = symbols('B3')
B4 = symbols('B4')
B5 = symbols('B5')
B6 = symbols('B6')
B7 = symbols('B7')
B8 = symbols('B8')
B9 = symbols('B9')
B10 = symbols('B10')
B11 = symbols('B11')
B12 = symbols('B12')
B13 = symbols('B13')
B14 = symbols('B14')
B15 = symbols('B15')
B16 = symbols('B16')

Cin = symbols('Cin')


print to_cnf(((Cin)|((((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))&(((((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3)&(((A4|B4)&(~A4|~B4)&(A5|B5)&(~A5|~B5))&((A6|B6)&(~A6|~B6)&(A7|B7)&(~A7|~B7))))|(((A4&B4)&(A5|B5)&(~A5|~B5))|(A5&B5)&((A6|B6)&(~A6|~B6)&(A7|B7)&(~A7|~B7)))|((A6&B6)&(A7|B7)&(~A7|~B7))|(A7&B7)&((((A8|B8)&(~A8|~B8)&(A9|B9)&(~A9|~B9))&((A10|B10)&(~A10|~B10)&(A11|B11)&(~A11|~B11)))&(((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))))|((((A8&B8)&(A9|B9)&(~A9|~B9))|(A9&B9)&((A10|B10)&(~A10|~B10)&(A11|B11)&(~A11|~B11)))|((A10&B10)&(A11|B11)&(~A11|~B11))|(A11&B11)&(((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15))))|(((A12&B12)&(A13|B13)&(~A13|~B13))|(A13&B13)&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))|((A14&B14)&(A15|B15)&(~A15|~B15))|(A15&B15))|(A16|B16)&(~A16|~B16))&(~(Cin)|((((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))&(((((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3)&(((A4|B4)&(~A4|~B4)&(A5|B5)&(~A5|~B5))&((A6|B6)&(~A6|~B6)&(A7|B7)&(~A7|~B7))))|(((A4&B4)&(A5|B5)&(~A5|~B5))|(A5&B5)&((A6|B6)&(~A6|~B6)&(A7|B7)&(~A7|~B7)))|((A6&B6)&(A7|B7)&(~A7|~B7))|(A7&B7)&((((A8|B8)&(~A8|~B8)&(A9|B9)&(~A9|~B9))&((A10|B10)&(~A10|~B10)&(A11|B11)&(~A11|~B11)))&(((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))))|((((A8&B8)&(A9|B9)&(~A9|~B9))|(A9&B9)&((A10|B10)&(~A10|~B10)&(A11|B11)&(~A11|~B11)))|((A10&B10)&(A11|B11)&(~A11|~B11))|(A11&B11)&(((A12|B12)&(~A12|~B12)&(A13|B13)&(~A13|~B13))&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15))))|(((A12&B12)&(A13|B13)&(~A13|~B13))|(A13&B13)&((A14|B14)&(~A14|~B14)&(A15|B15)&(~A15|~B15)))|((A14&B14)&(A15|B15)&(~A15|~B15))|(A15&B15))|~(A16|B16)&(~A16|~B16)))
