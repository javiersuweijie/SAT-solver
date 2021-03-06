package sat;
//import com.sun.org.apache.bcel.internal.generic.LUSHR;
import immutable.EmptyImList;
import immutable.ImList;
import sat.env.Bool;
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;

/**
 * A simple DPLL SAT solver. See http://en.wikipedia.org/wiki/DPLL_algorithm
 */

public class SATSolver {
    /**
     * Solve the problem using a simple version of DPLL with backtracking and
     * unit propagation. The returned environment binds literals of class
     * bool.Variable rather than the special literals used in clausification of
     * class clausal.Literal, so that clients can more readily use it.
     * 
     * @return an environment for which the problem evaluates to Bool.TRUE, or
     *         null if no such environment exists.
     */
    public static Environment solve(Formula formula) {
    	System.out.println(formula.getClauses().toString());
      return solve(formula.getClauses(), new Environment());
    }

    /**
     * Takes a partial assignment of variables to values, and recursively
     * searches for a complete satisfying assignment.
     * 
     * @param clauses
     *            formula in conjunctive normal form
     * @param env
     *            assignment of some or all variables in clauses to true or
     *            false values.
     * @return an environment for which all the clauses evaluate to Bool.TRUE,
     *         or null if no such environment exists.
     */
    private static Environment solve(ImList<Clause> clauses, Environment env) {
      if (clauses == null) return env;
      if (env == null) return null;
      if (hasEmptyClause(clauses)) return null;
      else if(isConsistent(clauses, env)) return env;
      else {
    	//Unit propagation
        Literal l = getUnitClause(clauses);
        while(l != null){
          env = l.setTrue(env);
          ImList<Clause> reducedClauses = new EmptyImList<Clause>();
          for(Clause c: clauses){
            Clause reduced = c.reduce(l);
            if(reduced == null) continue;
            reducedClauses = reducedClauses.add(reduced);
          }
          clauses = reducedClauses;
          l = getUnitClause(reducedClauses);
        }
        Literal s = getLiteralFromSmallestClause(clauses);
        
        ImList <Clause> leftClauses = clauses;
        ImList <Clause> rightClauses = clauses;
        
        if(s != null){
          env = s.setTrue(env);
          leftClauses = substitute(leftClauses,s);
        }
        Environment left = solve(leftClauses, env);
        if(left !=null) return left;
        else {
          if(s != null){
            env = s.getNegation().setTrue(env);
            rightClauses = substitute(rightClauses, s.getNegation());
          }
          Environment right = solve(rightClauses,env);
          return right;
        }
      }
    }

    /**
     * given a clause list and literal, produce a new list resulting from
     * setting that literal to true
     * 
     * @param clauses
     *            , a list of clauses
     * @param l
     *            , a literal to set to true
     * @return a new list of clauses resulting from setting l to true
     */
    private static ImList<Clause> substitute(ImList<Clause> clauses, Literal l) {
      return clauses.add(new Clause(l));
    }
    
    /** checks if the clause is consistent
     * 
     * @param clauses
     * 		a list of clauses
     * @param e
     * 		environment
     * @return 
     * 		a boolean
     */

    private static boolean isConsistent(ImList<Clause> clauses, Environment e){
      boolean consistent = true;
      for(Clause c: clauses){
        boolean clauseTrue = false;
        for (Literal l: c){
          if (l.eval(e) == Bool.TRUE) {
            clauseTrue = true;
            break;
          }
        }
        if (clauseTrue == false ) {
          consistent = false;
          break;
        }
      }
      return consistent;
    }

    /** checks if a list of clauses have empty ones inside
     * 
     * @param clauses
     * @return
     * 		boolean
     */
    private static boolean hasEmptyClause(ImList<Clause> clauses) {
      boolean hasEmpty = false;
      for(Clause c: clauses){
        if (c.isEmpty()){
          hasEmpty = true;
          break;
        }
      }
      return hasEmpty;
    }
    /** returns a the clause with a unit variable
     * 
     * @param clauses
     * @return literal
     */

    private static Literal getUnitClause(ImList<Clause> clauses) {
      for(Clause c: clauses) if( c.isUnit()) return c.chooseLiteral();
      return null;
    }

     private static Literal getLiteralFromSmallestClause(ImList<Clause> clauses){
       int smallest = Integer.MAX_VALUE ;
       Literal l = null;
       for (Clause c: clauses) {
         if (c.size() < smallest && !c.isEmpty()) {
           l = c.chooseLiteral();
           smallest = c.size();
         }
       }
       return l;
     }
}
