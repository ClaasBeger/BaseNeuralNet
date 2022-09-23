package neural;

public class OutputLayer extends Layer {

	public OutputLayer(int NeuronCount, String nam, Activation activate, Layer pre) {
		super(NeuronCount, nam, activate, pre);
		this.isLast = true;
	}
	
	

}
