package ActivationFunction;

public class Sigmoid implements ActivationFunction{

	private static final double SLOPE = 1.00;	
	
	/**
	 * 
	 */
	public Sigmoid() {
		
	}
	
	/**
	 * 
	 * @param arry
	 * @return
	 */
	public double[] getSumOfDiretives(double[] arry) {
		double d[] = new double[arry.length];
		for(int i=0;i<arry.length;i++) {
			d[i] = getDerivative(arry[i]);
		}
		return d;
	}
	
	/**
	 * Sigmoid function
	 * @param arry
	 * @return
	 */
	public double[] computeSumofSigmoid(double[] arry) {
		double sum[] = new double[arry.length];
		for (int i=0;i<arry.length;i++) {
			sum[i] = getOutput(arry[i]);
		}
		return sum;
	}
	
	@Override
	public double getOutput(double input) {
		// conditional logic helps to avoid NaN
		if (input > 100) {
			return 1.0;
		} else if (input < -100) {
			return 0.0;
		}
		return (1 / (1 + Math.pow(Math.E, (-1 * input))));
	}

	@Override
	public double getDerivative(double output) {
		// +0.1 is fix for flat spot see http://www.heatonresearch.com/wiki/Flat_Spot
		return getOutput(output) * (1 - getOutput(output));
	}

}
