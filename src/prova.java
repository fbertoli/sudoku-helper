import java.util.ArrayList;

public class prova {
    public static void main(String[] args) {
        ArrayList<Boolean> p = new ArrayList();
        p.add(false);
        p.add(true);
        p.add(false);
        p.add(true);
        int count = (int) p.stream().filter(x -> x).count();
        System.out.println(count);
    }
}
