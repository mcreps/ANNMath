import ActivationFunction.Sigmoid;
import Utils.DisplayUtil;
import Utils.NeuronUtil;

public class Runnable {


	private static final int INPUT_NEURON_COUNT = 2;
	private static final int HIDDEN_NEURON_COUNT = 3;
	private static final int OUTPUT_NEURON_COUNT = 1;

	
	private static double[][] getWeightChanges(double[][] arry, double rateOfChange) {
		double wtChanges[][] = new double[arry.length][HIDDEN_NEURON_COUNT];	
		for(int i=0; i<arry.length;i++) {
			for (int x = 0; x < arry[i].length; x++) {
				wtChanges[i][x] = rateOfChange / arry[i][x];
			}
		}
		return wtChanges;
	}
		
	
	public static double[] computeNodeValue(double[][] weights, double inputs[]) {
		double sum[] = new double[HIDDEN_NEURON_COUNT];		
		for(int i=0;i<HIDDEN_NEURON_COUNT;i++) {
			for (int x=0;x<INPUT_NEURON_COUNT;x++) {
				sum[i] += weights[x][i];
			}	
		}
		return sum;
	}

	
	/**
	 * 
	 * @param inputTotHiddenWeights
	 * @param changes
	 * @return
	 */
	private static double[][] updateInputToHiddenWeights(double[][] inputTotHiddenWeights, double[] changes) {
		int k=0;
		for (int i=0;i<inputTotHiddenWeights.length;i++) {		
			for (int j = 0; j < HIDDEN_NEURON_COUNT;j++) {
				inputTotHiddenWeights[i][j] += changes[k];
				k++;
			}			
		}
		return inputTotHiddenWeights;
	}
	
	public static void main(String[] args) {
		
		double inputs[] = {1,1};		
		double outputs[] = {0.0};
		double inputTotHiddenWeights[][] = { {.8, .4, .3}, {.2,.9,.5} };
		double hiddenToOuterWeight[][] = {{.3, .5, .9}};
		double lasthiddenToOuterWeight[][] = {{.3, .5, .9}};
		
		Sigmoid sigmoid = new Sigmoid();
		
		double hiddenLayerSums[] = computeNodeValue(inputTotHiddenWeights, inputs);
		double hiddenLayerFofX[]  = sigmoid.computeSumofSigmoid(hiddenLayerSums);
		System.out.println("HiddenLayer Sum: " + DisplayUtil.displayDouble(hiddenLayerSums));
		System.out.println("HiddenLayer f(x): " + DisplayUtil.displayDouble(hiddenLayerFofX));

		double outputSum = 0.0;//computeNodeValue(weights, hiddenLayerFofX);
		for(int x=0; x<hiddenLayerFofX.length; x++) {
			outputSum = outputSum + hiddenLayerFofX[x] * hiddenToOuterWeight[0][x];
		}
		double actual = sigmoid.getOutput(outputSum);
		System.out.printf("outputSum: %.6f, actual: %.6f, target: %.6f\n", outputSum, actual, outputs[OUTPUT_NEURON_COUNT-1]);
		
		/* Target - calculated */
		double ouputSumMarginOfError = outputs[OUTPUT_NEURON_COUNT-1] - actual;
		System.out.printf("Output Sum Margn of Error: %.6f\n", ouputSumMarginOfError);
		
		/*
		 * Delta output sum = S'(sum) * (output sum margin of error) 
		 * Also known as the rate of change
		 * 
		 */
		double deltaOutputSum = sigmoid.getDerivative(outputSum) * ouputSumMarginOfError;
		System.out.printf("Delta Output Sum: %.6f\n", deltaOutputSum);
		
		/*
		 * Delta output sum = S'(sum) * (output sum margin of error)
		 * Delta output sum = S'(1.235) * (-0.77)
		 * Delta output sum = -0.13439890643886018
		 * https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
		 */
		double changes[] = NeuronUtil.getWeightChanges(hiddenLayerFofX, deltaOutputSum);
		System.out.println("Weight Changes: " + DisplayUtil.displayDouble(changes));
		
		System.out.print("Old Weights: " + DisplayUtil.displayDouble(hiddenToOuterWeight));
		NeuronUtil.updatedWeights(changes, hiddenToOuterWeight);
		System.out.println(", New Weights: " + DisplayUtil.displayDouble(hiddenToOuterWeight));		
		
		/*
		 * Delta hidden sum = delta output sum / hidden-to-outer weights * S'(hidden sum)
		 * Delta hidden sum = -0.1344 / [0.3, 0.5, 0.9] * S'([1, 1.3, 0.8])
         * Delta hidden sum = [-0.448, -0.2688, -0.1493] * [0.1966, 0.1683, 0.2139]
         * Delta hidden sum = [-0.088, -0.0452, -0.0319]
		 */
		double rateChanges[][] = getWeightChanges(lasthiddenToOuterWeight, deltaOutputSum);
		System.out.println("Hidden-To-Outer Weight Changes: " + DisplayUtil.displayDouble(rateChanges));		
		System.out.print("f(x) Weight: " + DisplayUtil.displayDouble(hiddenLayerSums));
		
		double diretiveOfHiddenLaySums[] = sigmoid.getSumOfDiretives(hiddenLayerSums);		
		System.out.println(", Diretives of Input-To-Hidden Sum: " + DisplayUtil.displayDouble(diretiveOfHiddenLaySums));
		
		changes = NeuronUtil.computeChanges(diretiveOfHiddenLaySums, rateChanges);
		System.out.println("Weight Changes: " + DisplayUtil.displayDouble(changes));
		
		/*
		 * input 1 = 1
         * input 2 = 1

		 * Delta weights = delta hidden sum / input data
		 * Delta weights = [-0.088, -0.0452, -0.0319] / [1, 1]
		 * Delta weights = [-0.088, -0.0452, -0.0319, -0.088, -0.0452, -0.0319]
		 */
		changes = NeuronUtil.getInputChanges(inputs, changes);
		System.out.println("Input Weight Changes: " + DisplayUtil.displayDouble(changes));
		
		System.out.println("Old Input Weight Changes: " + DisplayUtil.displayDouble(inputTotHiddenWeights));
		inputTotHiddenWeights = updateInputToHiddenWeights(inputTotHiddenWeights, changes);
		System.out.println("New Input Weight Changes: " + DisplayUtil.displayDouble(inputTotHiddenWeights));
	}
}
