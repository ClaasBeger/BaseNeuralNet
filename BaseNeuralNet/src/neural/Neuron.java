package neural;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
	
	Activation act;
	
	Neuron[] prev;
	
	double[] weights;
	
	Integer output;
	
	int bias;
	
	
	
	public Neuron (Activation activate, Layer previous) {
		this.act = activate;
		
		if(previous != null) {
			this.prev = previous.neurons;
			this.weights = new double[previous.neurons.length];
		}
		
		Random r = new Random();
		
		int curNeurons = 0;
		
		for(int weigh = 0; weigh<weights.length; weigh++) {
			
			// He/Kaimin Initialization based on Normal distribution with number of inputs as variance
			weights[weigh] = r.nextGaussian()*(2/Math.sqrt(this.weights.length));
		}
	}
	
	public boolean connectTo(Layer pre) {
		this.prev = pre.neurons;
		return true;
	}
	
	public int computeNetInput() {
		int netInput = 0;
		for(int inp=0;inp<prev.length;inp++) {
			if(prev[inp].output == null) {
				throw new NullPointerException("The output of neuron number "+inp+" is not yet initialized.");
			}
			netInput += prev[inp].output*weights[inp];
		}
		return netInput;
	}
	
	

}
