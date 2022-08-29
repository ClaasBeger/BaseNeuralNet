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

	public boolean fullyConnectTo(Layer prev) {
		for (int curr = 0; curr < neurons.length; curr++) {
				if (!neurons[curr].connectTo(prev)) {
					return false;
			}
		}
		return true;
	}

}
