package sat;

import static org.junit.Assert.*;

import org.junit.Test;

import sat.env.*;
import sat.formula.*;

import java.io.*;
import java.util.regex.Pattern;

public class SATSolverTest {
	Literal a = PosLiteral.make("a");
	Literal b = PosLiteral.make("b");
	Literal c = PosLiteral.make("c");
	Literal d = PosLiteral.make("d");
	Literal e = PosLiteral.make("e");
	Literal f = PosLiteral.make("f");
	Literal g = PosLiteral.make("g");
	Literal h = PosLiteral.make("h");
	Literal i = PosLiteral.make("i");
	Literal j = PosLiteral.make("j");
	Literal k = PosLiteral.make("k");
	Literal l = PosLiteral.make("l");
	Literal m = PosLiteral.make("m");
	Literal n = PosLiteral.make("n");
	Literal o = PosLiteral.make("o");
	Literal p = PosLiteral.make("p");
	Literal q = PosLiteral.make("q");
	Literal r = PosLiteral.make("r");
	Literal s = PosLiteral.make("s");
	Literal t = PosLiteral.make("t");
	Literal u = PosLiteral.make("u");
	Literal v = PosLiteral.make("v");
	Literal w = PosLiteral.make("w");
	Literal x = PosLiteral.make("x");
	Literal y = PosLiteral.make("y");
	Literal z = PosLiteral.make("z");

    
	Literal na = a.getNegation();
	Literal nb = b.getNegation();
	Literal nc = c.getNegation();
	Literal nd = d.getNegation();
	Literal ne = e.getNegation();
	Literal nf = f.getNegation();
	Literal ng = g.getNegation();
	Literal nh = h.getNegation();
	Literal ni = i.getNegation();
	Literal nj = j.getNegation();
	Literal nk = k.getNegation();
	Literal nl = l.getNegation();
	Literal nm = m.getNegation();
	Literal nn = n.getNegation();
	Literal no = o.getNegation();
	Literal np = p.getNegation();
	Literal nq = q.getNegation();
	Literal nr = r.getNegation();
	Literal ns = s.getNegation();
	Literal nt = t.getNegation();
	Literal nu = u.getNegation();
	Literal nv = v.getNegation();
	Literal nw = w.getNegation();
	Literal nx = x.getNegation();
	Literal ny = y.getNegation();
	Literal nz = z.getNegation();
	
	public static String content() throws IOException {
		String filename = "sampleCNF/test.cnf";
		String makefm = "";
		String[] alphalist = {"", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k"}; 
		FileReader fileReader;        
        try {
                fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("File not found");
        }
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		while ((line = br.readLine()) != null) {
			String clause="makeCl(";
			//Iterate through each character in line
			for(int k=0; k<line.length();k++){
				//Ignore line completely if first char is 'c' or 'p'
				if (line.charAt(0)=='c'||line.charAt(0)=='p'||line.charAt(0)=='\n'){break;}
				else{
					//We want to ignore all 0s, thus loop starts from i=1
					//Iterate through alphalist
					if (line.charAt(k)=='-'){
						continue;
					}
					//Iterate through alphalist
					for (int i=1; i<12; i++){
						String reg = "("+i+")";
						//System.out.println(reg1);
						String iStr = "\""+i+"\"";
						//System.out.println(iStr);
						//System.out.println(alphalist[i]);
						if (Pattern.matches(reg, "2")){
							System.out.println("3");
//							if (line.charAt(k-1) == '-'){
//								clause += "-"+alphalist[i];
//							}
							clause += alphalist[i];
							clause += ",";
						}
					}
				}
			}
			clause += "), ";
			makefm += clause;
			System.out.println(makefm);
			//map 1 to 11 -> a to k
			//map -1 to -11 -> -a to -k
			//store a to k in another string
			//makeCl(numbers before 0)
		}
		//makefm remove last two elements
		br.close();
		fileReader.close();
//		System.out.println(makefm);
		return makefm;
//		Environment env = SATSolver.solve(makeFm(makeCl()));
//		System.out.println(env);
//		return env;
	}
	
	public static void main(String[] args) throws IOException {
		SATSolverTest.content();
		//Environment e = SATSolver.solve(makeFm(makeCl(a,b))	);
	}
	// TODO: add the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability
    
	// TODO: put your test cases for SATSolver.solve here
	 
    @Test
    public void testSATSolver1(){
    	// To test that ~a is mapped to false
    	// (~a)
    	Environment env = SATSolver.solve(makeFm(makeCl(na)));
    	assertEquals( Bool.FALSE, env.get(na.getVariable()));
    }
    
    @Test
    public void testSATSolver2(){
    	// Satisfiable
    	// Single clause
    	// (a+b)
    	Environment env = SATSolver.solve(makeFm(makeCl(a,b))	);
    	assertTrue( "to be satisfiable, one of the literals should be set to true",
    			Bool.TRUE == env.get(a.getVariable())  
    			|| Bool.TRUE == env.get(b.getVariable())	);
    }
  
    @Test
    public void testSATSolver3(){
    	// Satisfiable
    	// Two clauses
    	// (a+b)(a+c)
    	Formula form = makeFm( makeCl(a,b) , makeCl(a,c));
    	Environment env = SATSolver.solve(form);
    	assertTrue( "to be satisfiable, either a OR (b&c) have to be true",
    			Bool.TRUE == env.get(a.getVariable())  
    			|| Bool.TRUE == env.get(b.getVariable()) && Bool.TRUE == env.get(c.getVariable()));
    }
    
    @Test
    public void testSATSolver4(){
    	// Satisfiable
    	// (a)(a+b)(~a+c)(~c+d)
    	Formula form = makeFm( makeCl(a) , makeCl(a,b), makeCl(na,c), makeCl(nc,d));
    	Environment env = SATSolver.solve(form);
    	assertTrue( "to be satisfiable, a or b or c has to be true",
    			Bool.TRUE == env.get(a.getVariable()) || Bool.TRUE == env.get(b.getVariable()) || Bool.TRUE == env.get(c.getVariable()));
    }
    
    @Test
    public void testSATSolver5(){
    	// Unsatisfiable
    	// (a+b)(a+~b)(~a+~b)(~a+b)
    	// assertEquals( Bool.TRUE, e.get(c.getVariable()));
    	Formula form = makeFm(makeCl(a,b), makeCl(a,nb), makeCl(na,nb), makeCl(na,b));
    	Environment env = SATSolver.solve(form);
    	assertEquals(null, env);
    }
    
    @Test
    public void testSATSolver6(){
    	// Unsatisfiable
    	// (a)(~a)(b+c)
    	// Reduction to empty clause () between (a)(~a)
    	Formula form = makeFm( makeCl(a), makeCl(na));
    	Environment env = SATSolver.solve(form);
    	assertEquals(null, env);
    }
    
    @Test
    public void testSATSolver7(){
    	// Unsatisfiable
    	// Empty clause ()
    	Formula form = makeFm( makeCl());
    	Environment env = SATSolver.solve(form);
    	assertEquals(null, env);
    }
    
    @Test
    public void testSATSolver8(){
    	// Unsatisfiable
    	// 52 clauses
    	// (a+b)(b+c)(c+d)(d+e)(e+f)(f+g)(g+h)(h+i)(i+j)(j+k)(k+l)(l+m)(m+n)(n+o)(o+p)(p+q)(q+r)(r+s)(s+t)(t+u)(u+v)(v+w)(w+x)(x+y)(~y+z)(a+~z)
    	Formula form = makeFm( makeCl(a,b), makeCl(b,c), makeCl(c,d), makeCl(d,e), makeCl(e,f), makeCl(f,g), makeCl(g,h), makeCl(h,i), makeCl(i,j), makeCl(j,k), makeCl(k,l), makeCl(l,m), makeCl(m,n), makeCl(n,o), makeCl(o,p), makeCl(p,q), makeCl(q,r), makeCl(r,s), makeCl(s,t), makeCl(t,u), makeCl(u,v), makeCl(v,w), makeCl(w,x), makeCl(x,y), makeCl(y,z), makeCl(ny,z), makeCl(a,nz));
    	Environment env = SATSolver.solve(form);
    	assertEquals(null, env);
    }

    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }
    
    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }
   
}