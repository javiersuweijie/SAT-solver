def ANDgenerate(a,b):
    return "("+a+"&"+b+")"
#print ANDgenerate("A0","B0")

def NANDgenerate(a,b,c):
    return "~("+a+"&"+b+"&"+c+")"
#print NANDgenerate("A0","B0","C0")

def PGgenerate(n):
  A=[]
  B=[]
  C=[]
  C.append("Cin")
  for i in range(0,n):
    c = "A"+"%i"%i
    d = "B"+"%i"%i
    A.append(c)
    B.append(d)
    C.append(NANDgenerate(ANDgenerate(A[i],B[i]),ANDgenerate(A[i],C[i]),ANDgenerate(B[i],C[i])))
  return (A,B,C)

A,B,C = PGgenerate(4)
print C[4]
    
