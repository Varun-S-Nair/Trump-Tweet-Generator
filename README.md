# Trump-Tweet-Generator
Uses RNNs (and a Markov Model) and a dataset of Trump tweets to write its own novel Trump tweets

There are two ways to get output from this repository:

1. Markov Model (Recommended)

Prerequisite: 

Follow the instructions (JUST STEP 0) to download an appropriate java environment for your OS at one of the following links:
MAC: https://lift.cs.princeton.edu/java/mac/
Windows: https://lift.cs.princeton.edu/java/windows/
Linux: https://lift.cs.princeton.edu/java/linux/

Navigate to the src directory (Trump-Tweet-Generator/javabackend/MarkovModel/src) and run the following commands: 

javac-algs4 TextGenerator.java

java-introcs TextGenerator x y < donald-tweets-new-clean.txt

Change x to change the number of kgrams that the Markov Model uses (the more kgrams, the more likely the tweets will
be the exact same as Trump's, the less kgrams, the less accurate the tweets will be).

Change y to change the amount of characters that the model will output.

Example: java-introcs TextGenerator 15 1000 < donald-tweets-new-clean.txt -> Outputs 1000 characters of a 
15 kgram Markov Model

2. RNN (Experimental)

Navigate to the RNNBackend directory and run all the cells of the Trump_RNN.ipynb file.

The text output will be on the 26th cell and the last cell.

If this error pops ups (probably on the 7th cell): AttributeError: 'Tensor' object has no attribute 'numpy'

Uncomment the following line of code in the first cell: tf.enable_eager_execution()

Hope you enjoy!
