import com.sun.security.jgss.GSSUtil;

public class BambinoTerza extends BambinoSeconda {
    BambinoTerza(int[] nums) {
        super(nums);
    }

    public void calcoli() {
        int molt = 1;
        for (int item : nums) {
            molt *= item;
        }

        int media = molt / nums.length;

        System.out.println("Il bambino di terza dice che la moltiplicazione è " + molt + " e la media è " + media);
    }
}
