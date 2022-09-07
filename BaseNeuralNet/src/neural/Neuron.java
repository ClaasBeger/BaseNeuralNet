package neural;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Neuron extends Unit{

	Activation act;

	Unit[] prev;

	double[] weights;

	double threshweight;

	public Neuron(Activation activate, Layer previous, String initialization) {

		this.act = activate;

		if (previous != null) {
			this.prev = previous.neurons;
			this.weights = new double[previous.neurons.length];
		}

		Random r = new Random();

		int curNeurons = 0;

		for (int weigh = 0; weigh < weights.length; weigh++) {

			// Default initialization
		    if (initialization == null || initialization.contentEquals("Kaimin")
								|| initialization.contentEquals("He")) {
							// He/Kaimin Initialization based on ~N(0,2/sqrt(n))
							weights[weigh] = r.nextGaussian() * (2 / Math.sqrt(this.weights.length));
						}
		    else if (initialization.contentEquals("Xavier")) {
				// Weight initialization ~U(-1/sqrt(n), 1/sqrt(n))
				weights[weigh] = r.nextDouble() * (2 * (1 / Math.sqrt(this.weights.length)))
						- (1 / Math.sqrt(this.weights.length));
			}

		}
	}
	

	public boolean connectTo(Layer pre) {
		this.prev = (Neuron[]) pre.neurons;
		return true;
	}

	public double computeNetInput() {
		int netInput = 0;
		for (int inp = 0; inp < prev.length; inp++) {
			if (prev[inp].output == null) {
				throw new NullPointerException("The output of neuron number " + inp + " is not yet initialized.");
			}
			netInput += prev[inp].output * weights[inp];
		}
		return netInput + threshweight;
	}

	public double computeOutput(double netInput) {

		if (this.act.equals(Activation.LEAKY_RELU)) {
			if (netInput > 0) {
				return netInput;
			} else {
				return 0.01 * netInput;
			}
		} else if (this.act.equals(Activation.SIGMOID)) {
			// TODO: Print out a warning if this is not the last(/Output) layer
			return (1 / (1 + Math.exp(-netInput)));
		} else if (this.act.equals(Activation.HYPERBOLIC_TANGENT)) {
			return ((Math.exp(netInput) - Math.exp(-netInput)) / (Math.exp(netInput) + Math.exp(-netInput)));
		} else {// (this.act.equals(Activation.RELU) || this.act == null) { default choice
			if (netInput > 0) {
				return netInput;
			}
			return 0;
		}
	}

	public boolean compute() {
		try {
			this.output = this.computeOutput(this.computeNetInput());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
