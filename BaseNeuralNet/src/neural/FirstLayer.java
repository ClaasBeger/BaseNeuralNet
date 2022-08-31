package neural;

public class FirstLayer extends Layer {

	public FirstLayer(int NeuronCount, String nam, Activation activate, Layer pre) {
		
		super(NeuronCount, nam, activate, pre);
		
		this.isFirst = true;
		
	}

}
