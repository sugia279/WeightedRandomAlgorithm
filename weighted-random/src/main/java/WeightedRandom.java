import java.util.Random;
import java.util.stream.IntStream;

public class WeightedRandom {
    //Const
    private static final int MIN_VERSION = 1;
    private static final int MAX_VERSION = 100;
    private static final int MIN_WEIGHT = 0;
    private static final int MAX_WEIGHT = 1000000000;

    public WeightedRandom(){}

    /**
     * random a index by weighted random algorithm.
     *
     * @param  weights  array of weight
     * @return          version index
     */
    public static int nextIndex(int[] weights)
    {
        if(isValidInputWeights(weights))
        {
            int sumOfWeight = IntStream.of(weights).sum();
            int randomWeight = randomNumber(MIN_WEIGHT, sumOfWeight);
            for (int index = 0; index < weights.length; index++) {
                if(randomWeight <= weights[index]) {
                    return index;
                }
                randomWeight = randomWeight -  weights[index];
            }
        }
        return -1;
    }

    private static int randomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static boolean isValidInputWeights(int[] iWeights) {
        if (iWeights.length < MIN_VERSION || iWeights.length > MAX_VERSION) {
            System.out.println("The number of version is "+ iWeights.length +" \nIt should be " + MIN_VERSION + "-" + MAX_VERSION);
            return false;
        }
        for(int weight : iWeights) {
            if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                System.out.println("The Weight of version is "+ weight +"\n It should be " + MIN_WEIGHT + "-" + MAX_WEIGHT);
                return false;
            }
        }

        return true;
    }
}
