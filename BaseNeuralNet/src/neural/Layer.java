package neural;

public class Layer {

	public Unit[] neurons;

	public final String name;

	public boolean isFirst;

	public boolean isLast;

	public Layer(int NeuronCount, String nam, Activation activate, Layer pre) {

		Neuron[] createdNeurons = new Neuron[NeuronCount];

		this.isFirst = false;

		this.isLast = false;

		for (int k = 0; k < NeuronCount; k++) {
			createdNeurons[k] = new Neuron(this, activate, pre, null);
		}

		this.neurons = createdNeurons;
		this.name = nam;
	}

	public Layer(Unit[] units, String nam) {
		this.neurons = units;
		this.name = nam;
	}

	public boolean fullyConnectTo(Layer prev) {
		for (int curr = 0; curr < neurons.length; curr++) {
			if (!((Neuron) neurons[curr]).connectTo(prev)) {
				return false;
			}
		}
		return true;
	}

	public boolean computeLayer() {
		for (int i = 0; i < this.neurons.length; i++) {
			if (!((Neuron) neurons[i]).compute()) {
				return false;
			}
		}
		return true;
	}

	public boolean computeBackProp(double actual) {
		for (int i = 0; i < this.neurons.length; i++) {
			try {
				((Neuron) neurons[i]).computeError(actual);
				((Neuron) neurons[i]).backpropagate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean computeBackProp() {
		for (int i = 0; i < this.neurons.length; i++) {
			try {
				((Neuron) neurons[i]).backpropagate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
