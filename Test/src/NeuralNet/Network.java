package NeuralNet;

import java.util.Arrays;

import ActivationFunction.ActivationFunction;

public class Network {

	private int inputNeuronCount;
	private int hiddenNeuronCount;
	private int outputNeuronCount;	
	private ActivationFunction activationFunction = null;
	
	double inputs[][] = new double[0][0];		
	double outputs[] = new double[0];
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

	public double[][] getInputs() {
		return inputs;
	}

	public void setInputs(double[][] inputs) {
		this.inputs = inputs;
	}

	public double[] getOutputs() {
		return outputs;
	}

	public void setOutputs(double[] outputs) {
		this.outputs = outputs;
	}

	public double[][] getInputTotHiddenWeights() {
		return inputTotHiddenWeights;
	}

	public void setInputTotHiddenWeights(double[][] inputTotHiddenWeights) {
		this.inputTotHiddenWeights = inputTotHiddenWeights;
	}

	public double[][] getHiddenToOuterWeight() {
		return hiddenToOuterWeight;
	}

	public void setHiddenToOuterWeight(double[][] hiddenToOuterWeight) {
		this.hiddenToOuterWeight = hiddenToOuterWeight;
	}

	public double[][] getLasthiddenToOuterWeight() {
		return lasthiddenToOuterWeight;
	}


	public void setLasthiddenToOuterWeight(double[][] lasthiddenToOuterWeight) {
		this.lasthiddenToOuterWeight = lasthiddenToOuterWeight;
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
		builder.append(", inputs=");
		builder.append(Arrays.toString(inputs));
		builder.append(", outputs=");
		builder.append(Arrays.toString(outputs));
		builder.append(", inputTotHiddenWeights=");
		builder.append(Arrays.toString(inputTotHiddenWeights));
		builder.append(", hiddenToOuterWeight=");
		builder.append(Arrays.toString(hiddenToOuterWeight));
		builder.append(", lasthiddenToOuterWeight=");
		builder.append(Arrays.toString(lasthiddenToOuterWeight));
		builder.append("]");
		return builder.toString();
	}
	
}
