# Weighted Random messages
## Statement:
We have a set of N (1 ≤ N ≤ 100) different versions of advertising messages that we would like to randomly select and send out to the public. Each version has a weight w[i] (0 ≤ w[i] ≤ 109) of being selected. 

Implement a function to take an array w[] as input and return a message version i (0 ≤ i ≤ N-1) that satisfies the weighted random condition.

## How does the weighted random algorithm work?
    * Add up all the weights for all the items
    * Pick a random number from 1 to sum of weights
    * Iterate over the items, subtracting their weight from your random number until you get the item where the random number is less than that item's weight.
## How to test?
### 1. Checking the inputed value ###
We apply the test techniques such as boundary test and partition test.
 * Positive Cases of the inputed versions' value
    * Input the weight array by 1 version.
    * Input the weight array by 100 versions
    * Input the weight array by 50 version.
 * Positive Cases of the inputed weight value
    * Input the weight value is 1.
    * Input the weight value is 1000000000.
    * Input the weight value is 500000000.
  * Negative Cases of the inputed versions' value
    * Input the weight array by 0 version.
    * Input the weight array by 101 versions
    * Input the weight array by 150 version.
 * Negative Cases of the inputed weight value
    * Input the weight value is -1.
    * Input the weight value is 1000000001.
    * Input the weight value is 1000000050.
        
### 2. Checking the Algorithm works properly ###
 * Input a array of weights with many cases of the number of selected message.
 Ex:  
   1. Weight array: new int[]{50, 30, 60}
      -  Selected version number: 1400 messages, 
      -  Selected version number: 280 messages, 
   2. Weight array: ew int[]{30, 10, 20, 60}
      -  Selected version number: 360 messages, 
 * Count the random number of each version following the specified weight.
 * The randomness is relative so the result of randomness have to comparation with the deviation, and I set the deviation is +-10% for each selected version.
    
