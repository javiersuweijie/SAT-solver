package sat;

public class Equation {

  public static void main(String args[]) {
    String[] eqs = {
        "v1 = j + l",
        "l = -v2 . v3",
        "j = -v3 . v2",
        "v2 =  u + v",
        "u = Gb1 . -p2",
        "v = -Gb1 . p2",
        "G0 = A0 . B0",
        "G1 = A1 . B1",
        "Ga1 = n + G1",
        "n = G0 . p1",
        "n = G0 . p1",
        "p2 = x + y",
        "x = A2 .  -B2",
        "y = -A2 . B2",
        "p1 = c + d",
        "c = A1 . -B1",
        "d = -A1 . B1",
        "p0 = e + f",
        "e = A0 .  B0",
        "f = -A0 . B0",
        "Pra1 = p0 . p1",
        "Gb1 = k + Ga1",
        "k = Cin . Pra1"
    };

    String k = "";

    for(String s: eqs) {
      String[] a = s.split("[\\s]+");
      if(a[3].equals(".")) k += and(a[0], a[2],a[4]);
      else k += or(a[0], a[2],a[4]);
    }

    System.out.println(k);

  }
  public static String and(String c,String a, String b) {
    String f = minus(c)+" "+a+" 0\n";
    String g = minus(c)+" "+b+" 0\n";
    String h = minus(a) + " " + minus(b) + " " + c+" 0\n";

    String ret = f+g+h;
    return ret;
  }

  public static String or(String c,String a, String b) {
    // c = a + b
    // a -> c -a c 0
    // b -> c -b b 0
    // c -> a + b -c a b 0
    String f = minus(a)+" "+c+" 0\n";
    String g = minus(b)+" "+b+" 0\n";
    String h = minus(c) + " " + a + " " + b+" 0\n";

    String ret = f+g+h;
    return ret;
  }
  private static String minus(String a) {
    if(a.charAt(0) == '-') return a.substring(1);
    return '-' + a;
  }
}
