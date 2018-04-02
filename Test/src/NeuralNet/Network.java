package NeuralNet;

import ActivationFunction.ActivationFunction;

public class Network {

	private int inputNeuronCount;
	private int hiddenNeuronCount;
	private int outputNeuronCount;	
	private ActivationFunction activationFunction = null;
	
	double inputs[] = {1,1};		
	double outputs[] = {0.0};
	double inputTotHiddenWeights[][] = new double[0][0];
	double hiddenToOuterWeight[][] = new double[0][0];
	double lasthiddenToOuterWeight[][] = new double[0][0];
	
	public Network() {
		
	}
	
	public Network(int inputNeuronCount, int hiddenNeuronCount, int outputNeuronCount, ActivationFunction activationFunction) {
		this.inputNeuronCount = inputNeuronCount;
		this.hiddenNeuronCount = hiddenNeuronCount;
		this.outputNeuronCount = outputNeuronCount;
		this.activationFunction = activationFunction;
		
		double inputTotHiddenWeights[][] = new double[inputNeuronCount][hiddenNeuronCount];
		double hiddenToOuterWeight[][] = new double[outputNeuronCount][hiddenNeuronCount];
		double lasthiddenToOuterWeight[][] = new double[outputNeuronCount][hiddenNeuronCount];		
	}

	public int getInputNeuronCount() {
		return inputNeuronCount;
	}

	public void setInputNeuronCount(int inputNeuronCount) {
		this.inputNeuronCount = inputNeuronCount;
	}

	public int getHiddenNeuronCount() {
		return hiddenNeuronCount;
	}

	public void setHiddenNeuronCount(int hiddenNeuronCount) {
		this.hiddenNeuronCount = hiddenNeuronCount;
	}

	public int getOutputNeuronCount() {
		return outputNeuronCount;
	}

	public void setOutputNeuronCount(int outputNeuronCount) {
		this.outputNeuronCount = outputNeuronCount;
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Network [inputNeuronCount=");
		builder.append(inputNeuronCount);
		builder.append(", hiddenNeuronCount=");
		builder.append(hiddenNeuronCount);
		builder.append(", outputNeuronCount=");
		builder.append(outputNeuronCount);
		builder.append(", activationFunction=");
		builder.append(activationFunction);
		builder.append("]");
		return builder.toString();
	}
}
