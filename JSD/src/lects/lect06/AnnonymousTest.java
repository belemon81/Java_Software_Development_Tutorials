package lects.lect06;

public class AnnonymousTest {
    public static void main(String[] args) {
        System.out.println(new Object() {
            public int sum(int a, int b) {
                return a + b;
            }
        }.sum(15, 12));
    }
}
