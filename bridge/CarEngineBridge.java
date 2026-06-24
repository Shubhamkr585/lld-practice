interface T1 {
    int f(String s);
}

interface T2 {
    int f(String s);
}

interface T3 {
    int f(String s);
}

 public class jj implements T1, T2, T3 {

    @Override
    public int f(String s) {
        System.out.println(s);
        return 0;
    }

    public static void main(String[] args) {

        jj p = new jj();

        T1 a = p;
        T2 b = p;
        T3 c = p;
        p.f("shubham");
        a.f("From T1");
        b.f("From T2");
        c.f("From T3");
    }
}