import java.util.Arrays;
import java.util.stream.IntStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class WeightedRandomTest {

    @Test
    public void verifyInvalidVersionNumber(){
        /*Verify function works properly by Boundary test*/
        System.out.println("Input the weight array by 0 version");
        int[] testData = new int[0];
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);

        System.out.println("Input the weight array by 101 versions");
        testData = new int[101];
        for(int i=0; i<101; i++)
        {
            testData[i] = 1;
        }
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);

        /*Verify function works properly by Equivalence partition test*/
        System.out.println("Input the weight array by 150 versions");
        testData = new int[150];
        for(int i=0; i<150; i++)
        {
            testData[i] = 1;
        }
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);
    }

    @Test
    public void verifyValidVersionNumber(){
        /*Verify function works properly by Boundary test*/
        System.out.println("Input the weight array by 1 version");
        int[] testData = new int[1];
        testData[0] = 1;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);

        System.out.println("Input the weight array by 100 versions");
        testData = new int[100];
        for(int i=0; i<100; i++)
        {
            testData[i] = 1;
        }
        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);

        /*Verify function works properly by Equivalence partition test*/
        System.out.println("Input the weight array by 50 versions");
        testData = new int[50];
        for(int i=0; i<50; i++)
        {
            testData[i] = 1;
        }
        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);
    }

    @Test
    public void verifyInvalidWeight(){
        /*Verify function works properly by Boundary test*/
        System.out.println("Input the weight value is -1");
        int[] testData = new int[1];
        testData[0]=-1;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);

        System.out.println("Input the weight value is 1000000001");
        testData = new int[1];
        testData[0] = 1000000001;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);

        /*Verify function works properly by Equivalence partition test*/
        System.out.println("Input the weight value is -10");
        testData = new int[1];
        testData[0]=-10;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);
        //Input weight value is 1000000050
        testData = new int[1];
        testData[0]=1000000050;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) == -1);
    }

    @Test
    public void verifyValidWeight(){
        /*Verify function works properly by Boundary test*/
        System.out.println("Input the weight value is 1");
        int[] testData = new int[1];
        testData[0] = 1;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);

        System.out.println("Input the weight value is 1000000000");
        testData = new int[1];
        testData[0] = 1000000000;

        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);

        /*Verify function works properly by Equivalence partition test*/
        System.out.println("Input the weight value is 500000000");
        testData = new int[1];
        testData[0] = 500000000;
        Assert.assertTrue(WeightedRandom.nextIndex(testData) > -1);
    }

    @DataProvider(name="SetOfWeightProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { new int[]{50, 30, 60}, 1400 },
                        { new int[]{50, 30, 60}, 280 },
                        { new int[]{30, 10, 20, 60}, 360 },
                };

    }

    @Test(dataProvider="SetOfWeightProvider")
    public void verifyWeightedRandomAlgorithm(int[] weights, int msgNumber) {
        int[] testWeights = weights;

        //initial default values
        int[] randCount = new int[weights.length];
        for(int j=0; j<testWeights.length; j++)
        {
            randCount[j] = 0;
        }

        int nSelectedMessage = msgNumber;

        int randVersion;
        for (int i = 0; i < nSelectedMessage; i++) {
            randVersion = WeightedRandom.nextIndex(testWeights);
            randCount[randVersion] = randCount[randVersion] + 1;
        }

        System.out.println("=================================================================");
        //Sum of the selected version is equal to nSelectedMessage
        System.out.println("Sum of the selected version in array is " + IntStream.of(randCount).sum() + ".");
        Assert.assertTrue(IntStream.of(randCount).sum() == nSelectedMessage);

        //Randomness is relative so we verify result with acceptable deviation 10%
        int sumOfWeight = IntStream.of(testWeights).sum();
        System.out.println(Arrays.toString(randCount));
        for (int i = 0; i < testWeights.length; i++) {
            double probBefore = (double)testWeights[i]/sumOfWeight;
            double probAfter = (double)randCount[i]/nSelectedMessage;
            boolean verifyResult = probAfter>(probBefore - 0.1) && probAfter<(probBefore + 0.1);
            if(verifyResult)
            {
                System.out.println("The selected version[" + i + "] is " + randCount[i] + " times, and this is acceptable by 10% deviation.");
            }
            Assert.assertTrue(verifyResult);
        }
    }
}
