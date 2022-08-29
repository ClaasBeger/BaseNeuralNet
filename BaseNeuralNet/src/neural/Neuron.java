package neural;

public class Neuron {
	
	Activation act;
	
	Neuron[] prev;
	
	int[] weights;
	
	Integer output;
	
	int bias;
	
	
	
	public Neuron (Activation activate, Layer previous) {
		this.act = activate;
		
		if(previous != null) {
			this.prev = previous.neurons;
			this.weights = new int[previous.neurons.length];
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
