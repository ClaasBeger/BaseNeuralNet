package neural;

import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;

public class Neuron extends Unit{
	
	//TODO: Create a suitable logger and transition current prints to it (including importance specification)
	
	Activation act;

	Unit[] prev;
	
	Layer prevLayer;

	double[] weights;

	double threshweight;
	
	double error;
	
	Layer current;

	public Neuron(Layer curr, Activation activate, Layer previous, String initialization) {
		
		this.current = curr;
		
		

		this.act = activate;

		if (previous != null) {
			this.prev = previous.neurons;
			this.weights = new double[previous.neurons.length];
			this.prevLayer = previous;
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
		double netInput = 0;
		for (int inp = 0; inp < prev.length; inp++) {
			if (prev[inp].output == null) {
				throw new NullPointerException("The output of neuron number " + inp + " is not yet initialized.");
			}
			netInput += prev[inp].output * weights[inp];
		}
		this.netInput = netInput;
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

			if(!this.current.isLast) {
				System.out.println("WARNING: Sigmoid activation should not be used outside of the output layer because of gradient saturation!");
			}
			
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
	
	public double computeError(double actual) throws Exception {

		// TODO: Add gradient descent functionality

		if (this.current.isLast) {
			this.error = this.output - actual;
			
		}
		else {
			
			// TODO: Customize exception type
			
			throw new Exception("Error should only be computed on output unit and then propagated further, not computed on hidden units");
		}
		
		return this.error;
	}

	public boolean backpropagate() {
		for (int i = 0; i < prev.length; i++) {
			this.prev[i].backInput = this.weights[i] * this.error;
		}

		return false;
	}
	
	public double gradient(double x) {
		if(this.act == Activation.SIGMOID) {
			return (1/(1+Math.exp(-x)))*(1-(1/(1+Math.exp(-x))));
		}
		if(this.act == Activation.RELU) {
			return (x>0) ? 1 : 0;
		}
		if(this.act == Activation.LEAKY_RELU) {
			return (x>0) ? 1 : 0.01;
		}
		if(this.act == Activation.HYPERBOLIC_TANGENT) {
			return 1/Math.pow(Math.cosh(x), 2);
		}
		System.out.println("Activation Function not covered, setting Gradient to zero.");
		return 0;
	}

}
