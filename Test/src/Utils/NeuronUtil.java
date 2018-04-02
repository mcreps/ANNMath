package Utils;

public class NeuronUtil {

	
	/**
	 * 
	 * @param inputs
	 * @param changes
	 * @return
	 */
	public static double[] getInputChanges(double[] inputs, double[] changes) {
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
	 * @param deltaOutputSum
	 * @param outputWeights
	 */
	public static void updatedWeights(double[] changes, double[][] outputWeights) {
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
	public static double[] getWeightChanges(double[] hiddenLayerFofX, double rateOfChange) {
		double wtChanges[] = new double[hiddenLayerFofX.length];	
		for (int x = 0; x < hiddenLayerFofX.length; x++) {
			wtChanges[x] = rateOfChange / hiddenLayerFofX[x];
		}
		return wtChanges;
	}
	
	
	/**
	 * 
	 * @param diretiveOfHiddenLaySums
	 * @param rateChanges
	 * @return
	 */
	public static double[] computeChanges(double[] diretiveOfHiddenLaySums, double[][] rateChanges) {
		double product[] = new double[diretiveOfHiddenLaySums.length];
		
		for (int i =0;i<diretiveOfHiddenLaySums.length;i++) {
			product[i] = diretiveOfHiddenLaySums[i] * rateChanges[0][i];
		}		
		return product;
	}
	
}
