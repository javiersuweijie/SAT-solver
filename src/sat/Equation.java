package sat;

/**
 * Translates Equations in normal form to CNF form
 *
 */

public class Equation {

  /**
   * The equations that need to be solved are stored here
   * in the form of a string array.
   *
   * Each equation is parsed into a an output and two input arguments
   * An equation cannot have greater than two input variables.
   *
   * The resulting CNF of the equations is printed out.
   *
   * Note, the grammar of the equation is:
   * The Grammar of an equation is:
   * EQN ::== VARIABLE = VARIABLE [.+] VARIABLE
   * VARIABLE ::== [\d]+
   *
   *
   * @param args unused cmd line arguments
   */
  public static void main(String args[]) {
    String[] eqs = {
        // Kogge Stone Adder Equations:
        "47 = 44 + 49",
        "49 = -45 . 46",
        "44 = -46 . 45",
        "45 = 22 + 48",
        "22 = 25 . -26",
        "48 = -25 . 26",
        "40 = 1 . 4",
        "39 = 2 . 5",
        "34 = 42 + 39",
        "42 = 40 . 31",
        "26 = 29 + 30",
        "29 = 3 .  -6",
        "30 = -3 . 6",
        "31 = 35 + 36",
        "35 = 2 . -5",
        "36 = -2 . 5",
        "32 = 37 + 38",
        "37 = 1 . -4",
        "38 = -1 . 4",
        "33 = 32 . 31",
        "25 = 43 + 34",
        "43 = 28 . 33",

        // Ripple Carry Adder:
        "46 = 14 + 41",
        "41 = 15 + 50",
        "50 = 16 + 17",
        "18 = -27 . -3",
        "14 = 18 . 6",
        "19 = 27 . -3",
        "15 = 19 . -6",
        "20 = -27 . 3",
        "16 = 20 . -6",
        "21 = 27 . 3",
        "17 = 21 . 6",
        "7 = 8 + 92",
        "92 = 9 + 10",
        "8 = 1 . 4",
        "9 = 1 . 28",
        "10 = 4 . 28",
        "27 = 11 + 51",
        "51 = 12 + 13",
        "11 = 2 . 5",
        "12 = 2 . 7",
        "13 = 5 . 7",
        "43 = 28 . 33"
    };
    String k = "";
    for(String s: eqs) {
      String[] a = s.split("[\\s]+");
      if(a[3].equals(".")) k += and(a[0], a[2],a[4]);
      else k += or(a[0], a[2],a[4]);
    }
    System.out.println(k);
  }

  /**
   * Computes CNF of and operation
   *
   * @param c the output variable
   * @param a the first input variable
   * @param b the second input variable
   * @return the computed CNF as a string
   */
  public static String and(String c,String a, String b) {
    // c = ab
    // -c a 0
    // -c b 0
    // -a -b c 0
    String f = minus(c)+" "+a+" 0\n";
    String g = minus(c)+" "+b+" 0\n";
    String h = minus(a) + " " + minus(b) + " " + c+" 0\n";

    String ret = f+g+h;
    return ret;
  }

  public static String or(String c,String a, String b) {
    // c = a + b
    // a -> c         -a c 0
    // b -> c         -b c 0
    // c -> a + b     -c a b 0
    String f = minus(a)+" "+c+" 0\n";
    String g = minus(b)+" "+c+" 0\n";
    String h = minus(c) + " " + a + " " + b+" 0\n";

    String ret = f+g+h;
    return ret;
  }

  private static String minus(String a) {
    if(a.charAt(0) == '-') return a.substring(1);
    return '-' + a;
  }
}
