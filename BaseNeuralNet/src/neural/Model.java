package neural;

public class Model {
	
	public static void main(String[] args) {
		System.out.println("Test");
		
		Unit[] units = new Unit[3];
		
		for(int i = 0; i<3;i++) {
			units[i] = new InputUnit();
		}
		
		FirstLayer first = new FirstLayer("IN", units);
		Layer second = new Layer(8, "HID_1", Activation.RELU, first);
		Layer third = new Layer(5, "HID_2", Activation.RELU, second);
		Layer out = new Layer(1, "OUT", Activation.RELU, third);
		
		//Setting the input
		((InputUnit) first.neurons[0]).setInput(5.0);
		((InputUnit) first.neurons[1]).setInput(0.0);
		((InputUnit) first.neurons[2]).setInput(3.0);
		
		//Computing the layers
		second.computeLayer();
		third.computeLayer();
		out.computeLayer();
		
		System.out.println(out.neurons[0].output);
	}

}
