package sat;

import static org.junit.Assert.*;

import org.junit.Test;

import sat.env.*;
import sat.formula.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

public class SATSolverTest {
	
	public static Literal[] makeLiteralList(int number) {
		int charA = (int)'A';
		Literal[] l = new Literal[number+1];
		for (int i=0; i<number+1;i++) {
			String s = "V"+i;
			l[i] = PosLiteral.make(s);
		}
		return l;
	}
	public static Literal[] makeNegativeLiteralList(Literal[] l) {
		Literal[] nl = new Literal[l.length];
		for (int i=0;i<l.length;i++) {
			nl[i] = l[i].getNegation();
		}
		return nl;
	}
	
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
	
	public static Environment content() throws IOException {
		String filename = "sampleCNF/unsatisfiable.cnf";
		String makefm = "";
		String[] alphalist = {"","a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"}; 
		Literal[] literal_list= null;
		Literal[] negative_literal_list=null;
		Clause[] array_of_clauses = null; 
		FileReader fileReader;  
		StringBuilder sb;
        try {
                fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("File not found");
        }
		BufferedReader br = new BufferedReader(fileReader);
		sb = new StringBuilder();
		String line;
		int clause_count = 0;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()||line.charAt(0)=='c') {
				continue;
			}
			//else if (line.matches("[\\s][\\s]+")) {
			//	line.replaceAll("[\\s][\\s]+", "");
			//}
			else if (line.charAt(0)=='p') {
				int number_of_clauses = Integer.valueOf(line.split(" ")[3]);
				System.out.println("Number of clauses: "+number_of_clauses);
				int number_of_literals = Integer.valueOf(line.split(" ")[2]);
				System.out.println("Number of literals: "+number_of_literals);
				literal_list = makeLiteralList(number_of_literals);
				negative_literal_list = makeNegativeLiteralList(literal_list);
				continue;
			}
			else {
				sb.append(line).append(' ');
		}
		}
		String[] splitZero = sb.toString().split("\\s+0\\s+");
		//System.out.println(Arrays.toString(splitZero));
		array_of_clauses = new Clause[splitZero.length];
		
		//System.out.println(Arrays.toString(splitZero));
		for (String line_clause: splitZero) {
			//Iterate through each character in line_clause
			//Ignore line_clause completely if first char is 'c' or 'p'
			//We want to ignore all 0s, thus loop starts from i=1
			//Iterate through alphalist
			//System.out.println(line_clause);
			String[] sa = line_clause.trim().split("\\s+");
			System.out.println(Arrays.toString(sa));
			int length_of_clause = sa.length;
			Literal[] array_of_lits = new Literal[length_of_clause];
			int lit_count = 0;
			for (String s:sa) {
				if (s.isEmpty()) continue;
 				int i = Integer.valueOf(s);
				//System.out.println(s);
				if (i<0) {
					array_of_lits[lit_count] = negative_literal_list[-i];
					lit_count++;
				}
				else if (i>0) {
					array_of_lits[lit_count] = literal_list[i];
					lit_count++;
				}
			
			}
			System.out.println(Arrays.toString(array_of_lits));
			array_of_clauses[clause_count] = makeCl(array_of_lits);
			clause_count++;
		}
		br.close();
		fileReader.close();
		Formula f = makeFm(array_of_clauses);
		long time = System.currentTimeMillis();
		Environment env = SATSolver.solve(f);
		System.out.println(System.currentTimeMillis() - time);
		return env;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(SATSolverTest.content());
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
    	Formula form = makeFm( makeCl(a,b), makeCl(b,c), makeCl(c,d), makeCl(d,e), makeCl(e,f), makeCl(f,g), makeCl(g,h), makeCl(h,i), makeCl(i,j), makeCl(j,k), makeCl(k,l), makeCl(l,m), makeCl(m,n), makeCl(n,o), makeCl(o,p), makeCl(p,q), makeCl(q,r), makeCl(r,s), makeCl(s,t), makeCl(t,u), makeCl(u,v), makeCl(v,w), makeCl(w,x), makeCl(x,y), makeCl(ny,nz), makeCl(y,nz), makeCl(y,z), makeCl(ny,z), makeCl(a,nz));
    	Environment env = SATSolver.solve(form);
    	assertEquals(null, env);
    }
    
    @Test
    public void testSATSolver9(){
    	// Unsatisfiable
    	// 50 clauses
    	// (a+b)(b+c)(c+d)(d+e)(e+f)(f+g)(g+h)(h+i)(i+j)(j+k)(k+l)(l+m)(m+n)(n+o)(o+p)(p+q)(q+r)(r+s)(s+t)(t+u)(u+v)(v+w)(w+x)(x+y)
    	Formula form = makeFm( makeCl(a,b), makeCl(b,c), makeCl(c,d), makeCl(d,e), makeCl(e,f), makeCl(f,g), makeCl(g,h), makeCl(h,i), makeCl(i,j), makeCl(j,k), makeCl(k,l), makeCl(l,m), makeCl(m,n), makeCl(n,o), makeCl(o,p), makeCl(p,q), makeCl(q,r), makeCl(r,s), makeCl(s,t), makeCl(t,u), makeCl(u,v), makeCl(v,w), makeCl(w,x), makeCl(x,y), makeCl(y,z));
    	Environment env = SATSolver.solve(form);
//    	assertEquals(null, env);
    }

    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
        	if(c != null) f = f.addClause(c);
        }
        return f;
    }
    
    private static Clause makeCl(Literal... e) {
    	
        Clause c = new Clause();
        for (Literal l : e) {
        	if(c == null) break;
        	c = c.add(l);
        }
        return c;
    }
   
}