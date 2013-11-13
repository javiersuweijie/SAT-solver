def XORgenerate(a,b):
  return "("+a+"|"+b+")&("+"~"+a+"|"+"~"+b+")"

def ANDgenerate(a,b):
  return "("+a+"&"+b+")"

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

def PraGaGenerate():
  Pra = []
  Ga = []
  for i in range(0,16):
    if i%2 == 1:
      Pra.append(ANDgenerate(P[i-1],P[i]))
      Ga.append(ANDgenerate(G[i-1],P[i])+"|"+G[i])
    else:
      Pra.append('nothing')
      Ga.append('nothing')
  return Pra,Ga

Pra,Ga = PraGaGenerate()

def PrbGbGenerate():
  Prb=[]
  Gb=[]
  for i in range(0,16):
    if i%4 == 3:
      Prb.append(ANDgenerate(Pra[i-2],Pra[i]))
      Gb.append(ANDgenerate(Ga[i-2],Pra[i])+"|"+Ga[i])
    else:
      Prb.append("nothing")
      Gb.append("nothing")
  return Prb,Gb

Prb,Gb = PrbGbGenerate()

def PrcGcGenerate():
  Prc=[]
  Gc=[]
  for i in range(0,16):
    if i%8==7:
      Prc.append(ANDgenerate(Prb[i-4],Prb[i]))
      Gc.append(ANDgenerate(Gb[i-4],Prb[i])+"|"+Gb[i])
    else:
      Prc.append("nothing")
      Gc.append("nothing")
  return Prc,Gc

Prc,Gc = PrcGcGenerate()

def PrdGdGenerate():
  Prd=[]
  Gd=[]
  for i in range(0,16):
    if i==15:
      Prd.append(ANDgenerate(Prc[i-8],Prc[i]))
      Gd.append(ANDgenerate(Gc[i-8],Prc[i])+"|"+Gc[i])
    else:
      Prd.append("nothing")
      Gd.append("nothing")
  return Prd,Gd

Prd,Gd = PrdGdGenerate()

def GeGenerate():
  return "(Cin)"+"|"+ANDgenerate(Prb[3],Gb[3])

#S4 = XORgenerate(GeGenerate(),XORgenerate("A4","B4"))
#print S4
S4 = "((Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|(A4|B4)&(~A4|~B4))&(~(Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|~(A4|B4)&(~A4|~B4))"

ripple = "~(((A3&B3)|(A3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))))|(B3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))))))"

#"((Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|(A4|B4)&(~A4|~B4))&(~(Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|~(A4|B4)&(~A4|~B4))"

NOTS4ANDRIPPLE = "(~(((Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|(A4|B4)&(~A4|~B4))&(~(Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|~(A4|B4)&(~A4|~B4)))&(((A3&B3)|(A3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))))|(B3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin))))))))))"

S4ANDNOTRIPPLE = "(((Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|(A4|B4)&(~A4|~B4))&(~(Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|~(A4|B4)&(~A4|~B4))&~(((A3&B3)|(A3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))))|(B3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin))))))))))" 

#combined = XORgenerate(S4,ripple)

combined=ANDgenerate(S4,ripple)
#print combined


from sympy.logic.boolalg import to_cnf
from sympy import *

A0 = symbols('A0')
A1 = symbols('A1')
A2 = symbols('A2')
A3 = symbols('A3')
A4 = symbols('A4')

B0 = symbols('B0')
B1 = symbols('B1')
B2 = symbols('B2')
B3 = symbols('B3')
B4 = symbols('B4')

Cin = symbols('Cin') 

print to_cnf(~(((Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|(A4|B4)&(~A4|~B4))&(~(Cin)|((((A0|B0)&(~A0|~B0)&(A1|B1)&(~A1|~B1))&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))&(((A0&B0)&(A1|B1)&(~A1|~B1))|(A1&B1)&((A2|B2)&(~A2|~B2)&(A3|B3)&(~A3|~B3)))|((A2&B2)&(A3|B3)&(~A3|~B3))|(A3&B3))|~(A4|B4)&(~A4|~B4)))&(((A3&B3)|(A3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))))|(B3&((A2&B2)|(A2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin)))))|(B2&((A1&B1)|(A1&((A0&B0)|(A0&Cin)|(B0&Cin)))|(B1&((A0&B0)|(A0&Cin)|(B0&Cin))))))))))
