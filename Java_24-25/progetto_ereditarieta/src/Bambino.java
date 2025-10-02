public class Bambino {
    int[] nums;

    // costruttore
    Bambino(int[] nums) {
        this.nums = nums;
    }

    public void repeat() {
        for (int i :nums) {
            System.out.println("Il bambino di prima dice " + i);
        }
    }
}
