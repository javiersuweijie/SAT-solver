package sat;

public class Equation {

  public static void main(String args[]) {
    String[] eqs = {
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

//        "v1 = j + l",
//        "l = -v2 . v3",
//        "j = -v3 . v2",
//        "v2 = u + v",
//        "u = Gb1 . -p2",
//        "v = -Gb1 . p2",
//        "G0 = A0 . B0",
//        "G1 = A1 . B1",
//        "Ga1 = n + G1",
//        "n = G0 . p1",
//        "n = G0 . p1",
//        "p2 = x + y",
//        "x = A2 .  -B2",
//        "y = -A2 . B2",
//        "p1 = c + d",
//        "c = A1 . -B1",
//        "d = -A1 . B1",
//        "p0 = e + f",
//        "e = A0 . B0",
//        "f = -A0 . B0",
//        "Pra1 = p0 . p1",
//        "Gb1 = k + Ga1",
//        "k = Cin . Pra1",
//        "v3 = T6 + T10",
//        "T10 = T7 + T11",
//        "T11 = T8 + T9",
//        "Q0 = -C2 . -A2",
//        "T6 = Q0 . B2",
//        "Q1 = C2 . -A2",
//        "T7 = Q1 . -B2",
//        "Q2 = -C2 . A2",
//        "T8 = Q2 . -B2",
//        "Q3 = C2 . A2",
//        "T9 = Q3 . B2"
    };
    String[] oldeqs = {
        "v1 = j + l",
        "l = -v2 . v3",
        "j = -v3 . v2",
        "v2 = u + v",
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
        "e = A0 . B0",
        "f = -A0 . B0",
        "Pra1 = p0 . p1",
        "Gb1 = k + Ga1",
        "k = Cin . Pra1",
        "v3 = T6 + T10",
        "T10 = T7 + T11",
        "T11 = T8 + T9",
        "Q0 = -C2 . -A2",
        "T6 = Q0 . B2",
        "Q1 = C2 . -A2",
        "T7 = Q1 . -B2",
        "Q2 = -C2 . A2",
        "T8 = Q2 . -B2",
        "Q3 = C2 . A2",
        "T9 = Q3 . B2"
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
