package neural;

public class Neuron {
	
	Activation act;
	
	Neuron[] prev;
	
	
	
	public Neuron (Activation activate, Layer previous) {
		this.act = activate;
		
		if(previous != null) {
			this.prev = previous.neurons;
		}
	}
	
	public boolean connectTo(Layer pre) {
		this.prev = pre.neurons;
		return true;
	}
	
	public int computeNetInput() {
		return 0;
	}
	
	

}
