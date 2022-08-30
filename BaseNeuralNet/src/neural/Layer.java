package neural;

public class Layer {

	public Neuron[] neurons;

	public final String name;

	public final boolean isFirst;

	public final boolean isLast;

	public Layer(Neuron[] neur, String nam, boolean first, boolean last) {
		this.neurons = neur;
		this.name = nam;
		this.isFirst = first;
		this.isLast = last;
	}
	
	public Layer(int NeuronCount, String nam, boolean first, boolean last, Activation activate, Layer pre) {
		
		Neuron[] createdNeurons = new Neuron[NeuronCount];
		
		for(int k=0; k<NeuronCount; k++) {
			createdNeurons[k] = new Neuron(activate, pre, null);
		}
		
		this.neurons = createdNeurons;
		this.name = nam;
		this.isFirst = first;
		this.isLast = last;
	}

	public boolean fullyConnectTo(Layer prev) {
		for (int curr = 0; curr < neurons.length; curr++) {
				if (!neurons[curr].connectTo(prev)) {
					return false;
			}
		}
		return true;
	}
	
	public boolean computeLayer() {
	     for(int i = 0; i<this.neurons.length; i++) {
	    	 if(!neurons[i].compute()) {
	    		 return false;
	    	 }
	     }
	     return true;
	}

}
