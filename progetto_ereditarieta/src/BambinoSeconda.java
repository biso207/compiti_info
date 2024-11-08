public class BambinoSeconda extends Bambino{

    // csotruttore
    BambinoSeconda(int[] nums) {
        super(nums);
    }

    public void somma() {
        int accumulatore = 0;
        for (int item : nums) {
            accumulatore += item;
        }

        System.out.println("Il bambino di seconda dice che la somma è " + accumulatore);
    }
}
