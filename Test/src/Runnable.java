
public class Runnable {

	private static final double SLOPE = 1.00;	
	private static final int INPUT_NEURON_COUNT = 2;
	private static final int HIDDEN_NEURON_COUNT = 3;
	private static final int OUTPUT_NEURON_COUNT = 1;

	/**
	 * 
	 * @param outputWeights
	 * @return
	 */
	private static String displayDouble(double[] outputWeights) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int x=0; x<outputWeights.length;x++) {
			if (x>0) {sb.append(", ");}
			sb.append(String.format("%.6f", outputWeights[x]));
		}			
		sb.append("}");
		return sb.toString();
	}
	
	private static String displayDouble(double[][] outputWeights) {
		StringBuilder sb = new StringBuilder();
		sb.append("{{");
		for(int i=0;i<outputWeights.length;i++) {
			if (i>0) {sb.append(", {");}
			for (int x=0; x<outputWeights[i].length;x++) {
				if (x>0) {sb.append(", ");}
				sb.append(String.format("%.6f", outputWeights[i][x]));
			}	
			sb.append("}");
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 
	 * @param deltaOutputSum
	 * @param outputWeights
	 */
	private static void updatedWeights(double[] changes, double[][] outputWeights) {
		for(int i=0;i<outputWeights.length;i++) {
			double d[] = outputWeights[i];
			for (int x=0; x<d.length;x++) {
				outputWeights[i][x] = outputWeights[i][x] + changes[x];
			}				
		}
	}	
	
	/**
	 * Compute and Updates the change to the output layer's weights
	 * @param hiddenLayerFofX
	 * @param rateOfChange
	 * @return
	 */
	private static double[] getWeightChanges(double[] hiddenLayerFofX, double rateOfChange) {
		double wtChanges[] = new double[hiddenLayerFofX.length];	
		for (int x = 0; x < hiddenLayerFofX.length; x++) {
			wtChanges[x] = rateOfChange / hiddenLayerFofX[x];
		}
		return wtChanges;
	}
	
	private static double[][] getWeightChanges(double[][] arry, double rateOfChange) {
		double wtChanges[][] = new double[arry.length][HIDDEN_NEURON_COUNT];	
		for(int i=0; i<arry.length;i++) {
			for (int x = 0; x < arry[i].length; x++) {
				wtChanges[i][x] = rateOfChange / arry[i][x];
			}
		}
		return wtChanges;
	}
	
	/**
	 * Sigmoid function
	 * @param arry
	 * @return
	 */
	private static double[] computeSumofSigmoid(double[] arry) {
		double sum[] = new double[arry.length];
		for (int i=0;i<arry.length;i++) {
			sum[i] = getOutput(arry[i]);
		}
		return sum;
	}
	
	/**
	 * 
	 * @param arry
	 * @return
	 */
	private static double[] getDiretives(double[] arry) {
		double d[] = new double[arry.length];
		for(int i=0;i<arry.length;i++) {
			d[i] = getDerivative(arry[i]);
		}
		return d;
	}
	
	public static double getOutput(double netInput) {
		// conditional logic helps to avoid NaN
		if (netInput > 100) {
			return 1.0;
		} else if (netInput < -100) {
			return 0.0;
		}

		return (1/( 1 + Math.pow(Math.E,(-1*netInput))));
	}

	public static double getDerivative(double output) {
		// +0.1 is fix for flat spot see http://www.heatonresearch.com/wiki/Flat_Spot
		double derivative = getOutput(output) * (1 - getOutput(output));
		return derivative;
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
	 * @param diretiveOfHiddenLaySums
	 * @param rateChanges
	 * @return
	 */
	private static double[] computeChanges(double[] diretiveOfHiddenLaySums, double[][] rateChanges) {
		double product[] = new double[diretiveOfHiddenLaySums.length];
		
		for (int i =0;i<diretiveOfHiddenLaySums.length;i++) {
			product[i] = diretiveOfHiddenLaySums[i] * rateChanges[0][i];
		}		
		return product;
	}
	
	/**
	 * 
	 * @param inputs
	 * @param changes
	 * @return
	 */
	private static double[] getInputChanges(double[] inputs, double[] changes) {
		double results[] = new double[inputs.length*changes.length];
		int k=0;
		for(int i=0;i<inputs.length;i++) {			
			for (int j=0;j<changes.length;j++) {
				results[k] = inputs[i] *changes[j];
				k++;
			}
		}		
		return results;
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
		
		double hiddenLayerSums[] = computeNodeValue(inputTotHiddenWeights, inputs);
		double hiddenLayerFofX[]  = computeSumofSigmoid(hiddenLayerSums);
		System.out.println("HiddenLayer Sum: " + displayDouble(hiddenLayerSums));
		System.out.println("HiddenLayer f(x): " + displayDouble(hiddenLayerFofX));

		double outputSum = 0.0;//computeNodeValue(weights, hiddenLayerFofX);
		for(int x=0; x<hiddenLayerFofX.length; x++) {
			outputSum = outputSum + hiddenLayerFofX[x] * hiddenToOuterWeight[0][x];
		}
		double actual = getOutput(outputSum);
		System.out.printf("outputSum: %.6f, actual: %.6f, target: %.6f\n", outputSum, actual, outputs[OUTPUT_NEURON_COUNT-1]);
		
		/* Target - calculated */
		double ouputSumMarginOfError = outputs[OUTPUT_NEURON_COUNT-1] - actual;
		System.out.printf("Output Sum Margn of Error: %.6f\n", ouputSumMarginOfError);
		
		/*
		 * Delta output sum = S'(sum) * (output sum margin of error) 
		 * Also known as the rate of change
		 * 
		 */
		double deltaOutputSum = getDerivative(outputSum) * ouputSumMarginOfError;
		System.out.printf("Delta Output Sum: %.6f\n", deltaOutputSum);
		
		/*
		 * Delta output sum = S'(sum) * (output sum margin of error)
		 * Delta output sum = S'(1.235) * (-0.77)
		 * Delta output sum = -0.13439890643886018
		 * https://stevenmiller888.github.io/mind-how-to-build-a-neural-network/
		 */
		double changes[] = getWeightChanges(hiddenLayerFofX, deltaOutputSum);
		System.out.println("Weight Changes: " + displayDouble(changes));
		
		System.out.print("Old Weights: " + displayDouble(hiddenToOuterWeight));
		updatedWeights(changes, hiddenToOuterWeight);
		System.out.println(", New Weights: " + displayDouble(hiddenToOuterWeight));		
		
		/*
		 * Delta hidden sum = delta output sum / hidden-to-outer weights * S'(hidden sum)
		 * Delta hidden sum = -0.1344 / [0.3, 0.5, 0.9] * S'([1, 1.3, 0.8])
         * Delta hidden sum = [-0.448, -0.2688, -0.1493] * [0.1966, 0.1683, 0.2139]
         * Delta hidden sum = [-0.088, -0.0452, -0.0319]
		 */
		double rateChanges[][] = getWeightChanges(lasthiddenToOuterWeight, deltaOutputSum);
		System.out.println("Hidden-To-Outer Weight Changes: " + displayDouble(rateChanges));		
		System.out.print("f(x) Weight: " + displayDouble(hiddenLayerSums));
		
		double diretiveOfHiddenLaySums[] = getDiretives(hiddenLayerSums);		
		System.out.println(", Diretives of Input-To-Hidden Sum: " + displayDouble(diretiveOfHiddenLaySums));
		
		changes = computeChanges(diretiveOfHiddenLaySums, rateChanges);
		System.out.println("Weight Changes: " + displayDouble(changes));
		
		/*
		 * input 1 = 1
         * input 2 = 1

		 * Delta weights = delta hidden sum / input data
		 * Delta weights = [-0.088, -0.0452, -0.0319] / [1, 1]
		 * Delta weights = [-0.088, -0.0452, -0.0319, -0.088, -0.0452, -0.0319]
		 */
		changes = getInputChanges(inputs, changes);
		System.out.println("Input Weight Changes: " + displayDouble(changes));
		
		System.out.println("Old Input Weight Changes: " + displayDouble(inputTotHiddenWeights));
		inputTotHiddenWeights = updateInputToHiddenWeights(inputTotHiddenWeights, changes);
		System.out.println("New Input Weight Changes: " + displayDouble(inputTotHiddenWeights));
	}
}
